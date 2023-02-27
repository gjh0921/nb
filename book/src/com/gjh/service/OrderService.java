package com.gjh.service;

import com.gjh.bean.Cart;
import com.gjh.bean.Order;
import com.gjh.bean.OrderItem;
import com.gjh.util.Page;

import java.sql.SQLException;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId) throws SQLException;
    public Page<Order> page(Integer pageNo,Integer pageSize) throws SQLException;
}