package com.linkedIn.linkedIn_batch.jobs.reader;

import com.linkedIn.linkedIn_batch.domain.Orders;
import com.linkedIn.linkedIn_batch.service.ShippedOrderService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyBatisCursorItemReader implements ItemReader<Orders> {
    @Autowired
    private ShippedOrderService service;
    private List<Orders> orders;
    private int nextOrderIndex = 0;

    @Override
    public Orders read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return nextOrderIndex < 1000 ? service.selectShippedOrder().get(nextOrderIndex++) : null;
    }

}
