package com.linkedIn.linkedIn_batch.jobs.writer;

import com.linkedIn.linkedIn_batch.domain.Orders;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemPreparedStatementSetter implements ItemPreparedStatementSetter<Orders> {

    private int count = 0;
    @Override
    public void setValues(Orders item, PreparedStatement ps) throws SQLException, IllegalArgumentException {

        if (count++ < 3)
            throw new IllegalArgumentException("에러");

        ps.setLong(1, item.getOrderId());
        ps.setString(2, item.getFirstName());
        ps.setString(3, item.getLastName());
        ps.setString(4, item.getEmail());
        ps.setBigDecimal(5, item.getCost());
        ps.setString(6, item.getItemId());
        ps.setString(7, item.getItemName());
        ps.setDate(8, new Date(item.getShipDate().getTime()));
        System.out.println(count);

    }
}
