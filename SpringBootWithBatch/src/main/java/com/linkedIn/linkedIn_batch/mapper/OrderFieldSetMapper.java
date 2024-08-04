package com.linkedIn.linkedIn_batch.mapper;

import com.linkedIn.linkedIn_batch.domain.Orders;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class OrderFieldSetMapper implements FieldSetMapper<Orders> {
    @Override
    public Orders mapFieldSet(FieldSet fieldSet) throws BindException {
        Orders order = new Orders();
        order.setOrderId(fieldSet.readLong("order_id"));
        order.setCost(fieldSet.readBigDecimal("cost"));
        order.setEmail(fieldSet.readString("email"));
        order.setFirstName(fieldSet.readString("first_name"));
        order.setLastName(fieldSet.readString("last_name"));
        order.setItemId(fieldSet.readString("item_id"));
        order.setItemName(fieldSet.readString("item_name"));
        order.setShipDate(fieldSet.readDate("ship_date"));
        return order;
    }

}
