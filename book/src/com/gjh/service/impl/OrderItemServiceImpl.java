package com.gjh.service.impl;

import com.gjh.bean.OrderItem;
import com.gjh.dao.OrderItemDao;
import com.gjh.dao.impl.OrderItemDaoImpl;
import com.gjh.service.OrderItemService;

import java.sql.SQLException;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    public List<OrderItem> detail(String orderId) throws SQLException{
        return orderItemDao.detail(orderId);
    }
}
