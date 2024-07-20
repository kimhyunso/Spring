package com.linkedIn.linkedIn_batch.jobs;

import com.linkedIn.linkedIn_batch.domain.Order;
import com.linkedIn.linkedIn_batch.mapper.OderRowMapper;
import com.linkedIn.linkedIn_batch.mapper.OrderFieldSetMapper;
import com.linkedIn.linkedIn_batch.service.ShippedOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ChunkJob {
    private final SqlSessionFactory sqlSessionFactory;
    private final DataSource dataSource;
    private static String[] tokens = new String[] {"order_id", "first_name", "last_name", "email", "cost", "item_id", "item_name", "ship_date"};

    private static String ORDER_SQL = "select order_id, first_name, last_name, "
            + "email, cost, item_id, item_name, ship_date "
            + "from SHIPPED_ORDER order by order_id";

    @Bean
    public ItemReader<Order> myBatisCursorItemReader() {
        return new com.linkedIn.linkedIn_batch.jobs.reader.MyBatisCursorItemReader();
    }

    @Bean
    public ItemReader<Order> itemReader() {
        FlatFileItemReader<Order> itemReader = new FlatFileItemReader<>();
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new FileSystemResource("D:/Spring/SpringBootWithBatch/data/shipped_orders.csv"));

        DefaultLineMapper<Order> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(tokens);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new OrderFieldSetMapper());

        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    @Bean
    public ItemReader<Order> jbdcItemReader() {
        return new JdbcCursorItemReaderBuilder<Order>()
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
    public ItemReader<Order> myBatisItemReader() {
        return new MyBatisCursorItemReaderBuilder<Order>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("selectShippedOrder")
                .build();
    }



    @Bean
    public ItemReader<Order> jbdcPaingItemReader() throws Exception {
        return new JdbcPagingItemReaderBuilder<Order>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .queryProvider(queryProvider())
                .rowMapper(new OderRowMapper())
                .pageSize(10)
                .build();
    }

    @Bean
    public Step chunkBasedStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("chunkBasedStep", jobRepository)
                .<Order, Order>chunk(10, transactionManager)
                .reader(myBatisCursorItemReader())
                .writer(new ItemWriter<Order>() {
                    @Override
                    public void write(Chunk<? extends Order> chunk) throws Exception {
                        System.out.println(String.format("Received List of Size: %s", chunk.size()));
                        chunk.forEach(System.out::println);
                        // file 만들기 로직
                    }
                }).build();
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("job", jobRepository)
                .start(chunkBasedStep(jobRepository, transactionManager))
                .build();
    }

}
