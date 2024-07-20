package com.linkedIn.linkedIn_batch.mapper;

import com.linkedIn.linkedIn_batch.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShippedOrderMapper {
    List<Order> selectShippedOrder();
}
