package com.gjh.dao;

import com.gjh.bean.OrderItem;
import com.gjh.util.BaseInterface;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao extends BaseInterface<OrderItem> {
    public List<OrderItem> detail(String orderId) throws SQLException;
}
