package com.linkedIn.linkedIn_batch.service;

import com.linkedIn.linkedIn_batch.domain.Orders;
import com.linkedIn.linkedIn_batch.mapper.ShippedOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippedOrderService {
    private final ShippedOrderMapper mapper;

    public List<Orders> selectShippedOrder() {
        return mapper.selectShippedOrder();
    }
}
