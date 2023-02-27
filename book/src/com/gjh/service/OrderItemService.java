package com.gjh.service;

import com.gjh.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemService {
    public List<OrderItem> detail(String orderId) throws SQLException;
}
