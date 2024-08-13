package com.linkedIn.linkedIn_batch.jobs;

import com.linkedIn.linkedIn_batch.domain.Orders;
import com.linkedIn.linkedIn_batch.jobs.writer.OrderItemPreparedStatementSetter;
import com.linkedIn.linkedIn_batch.mapper.OderRowMapper;
import com.linkedIn.linkedIn_batch.mapper.OrderFieldSetMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ChunkJob {
    private static String INSERT_ORDER_SQL = "insert into SHIPPED_ORDER_OUTPUT(order_id, first_name, last_name, email, cost, item_id, item_name, ship_date) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?)";
    private final SqlSessionFactory sqlSessionFactory;
    private final DataSource dataSource;
    private static String[] tokens = new String[] {"order_id", "first_name", "last_name", "email", "cost", "item_id", "item_name", "ship_date"};
    private static String[] names = new String[] {"orderId", "firstName", "lastName", "email", "cost", "itemId", "itemName", "shipDate"};

    private static String ORDER_SQL = "select order_id, first_name, last_name, "
            + "email, cost, item_id, item_name, ship_date "
            + "from SHIPPED_ORDER order by order_id";

    private final CustomSkipPolicy customSkipPolicy;

    @Bean
    public ItemReader<Orders> myBatisCursorItemReader() {
        return new com.linkedIn.linkedIn_batch.jobs.reader.MyBatisCursorItemReader();
    }

    @Bean
    public ItemReader<Orders> itemReader() {
        FlatFileItemReader<Orders> itemReader = new FlatFileItemReader<>();
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new FileSystemResource("D:/Spring/SpringBootWithBatch/data/shipped_orders.csv"));

        DefaultLineMapper<Orders> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(tokens);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new OrderFieldSetMapper());

        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    @Bean
    public ItemReader<Orders> jbdcItemReader() {
        return new JdbcCursorItemReaderBuilder<Orders>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .sql(ORDER_SQL)
                .rowMapper(new OderRowMapper())
                .build();
    }

    @Bean
    public PagingQueryProvider queryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean factory = new SqlPagingQueryProviderFactoryBean();

        factory.setSelectClause("select order_id, first_name, last_name, "
                + "email, cost, item_id, item_name, ship_date");
        factory.setFromClause("from SHIPPED_ORDER");
        factory.setSortKey("order_id");
        factory.setDataSource(dataSource);

        return factory.getObject();
    }

    @Bean
    public ItemReader<Orders> myBatisItemReader() {
        return new MyBatisCursorItemReaderBuilder<Orders>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("selectShippedOrder")
                .build();
    }



    @Bean
    public ItemReader<Orders> jbdcPaingItemReader() throws Exception {
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("order_id", Order.ASCENDING);

        return new JdbcPagingItemReaderBuilder<Orders>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .queryProvider(queryProvider())
                .rowMapper(new OderRowMapper())
                .sortKeys(sortKeys)
                .pageSize(10)
                .build();
    }
    @Bean
    public ItemWriter<Orders> itemWriter() {
        FlatFileItemWriter<Orders> itemWriter = new FlatFileItemWriter<>();
        itemWriter.setResource(new FileSystemResource("/data/shipped_orders_output.csv"));

        DelimitedLineAggregator<Orders> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Orders> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(names);

        aggregator.setFieldExtractor(fieldExtractor);
        itemWriter.setLineAggregator(aggregator);

        return itemWriter;
    }
    @Bean
    public ItemWriter<Orders> jdbcBatchItemWriter() {

        return new JdbcBatchItemWriterBuilder<Orders>()
                .dataSource(dataSource)
                .sql(INSERT_ORDER_SQL)
                .itemPreparedStatementSetter(new OrderItemPreparedStatementSetter())
                .build();
    }

    @Bean
    public Step chunkBasedStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("chunkBasedStep", jobRepository)
                .<Orders, Orders>chunk(10, transactionManager)
                .reader(jbdcPaingItemReader())
                .writer(jdbcBatchItemWriter())
                .faultTolerant()
                // .skip(IllegalArgumentException.class)
                // .skipLimit(3)
                .skipPolicy(skipPolicy())
                .build();
    }

    public SkipPolicy skipPolicy(){
        CustomSkipPolicy skipPolicy = new CustomSkipPolicy();
        skipPolicy.setExceptionClassMap(IllegalArgumentException.class,3);
        skipPolicy.setExceptionClassMap(IllegalAccessException.class,3);
        return skipPolicy;
    }


    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("job", jobRepository)
                .start(chunkBasedStep(jobRepository, transactionManager))
               .build();
    }

}
