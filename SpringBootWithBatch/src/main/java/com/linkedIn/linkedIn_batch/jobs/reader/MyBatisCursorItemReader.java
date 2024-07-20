package com.linkedIn.linkedIn_batch.jobs.reader;

import com.linkedIn.linkedIn_batch.domain.Order;
import com.linkedIn.linkedIn_batch.service.ShippedOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyBatisCursorItemReader implements ItemReader<Order> {
    @Autowired
    private ShippedOrderService service;
    private List<Order> orders;
    private int nextOrderIndex = 0;

    @Override
    public Order read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return nextOrderIndex < 1000 ? service.selectShippedOrder().get(nextOrderIndex++) : null;
    }

}
