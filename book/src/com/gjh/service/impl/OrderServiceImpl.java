package com.gjh.service.impl;

import com.gjh.bean.*;
import com.gjh.dao.BookDao;
import com.gjh.dao.OrderDao;
import com.gjh.dao.OrderItemDao;
import com.gjh.dao.impl.BookDaoImpl;
import com.gjh.dao.impl.OrderDaoImpl;
import com.gjh.dao.impl.OrderItemDaoImpl;
import com.gjh.service.OrderService;
import com.gjh.util.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        // 创建一个订单对象
        Order order = new Order();
        // 保存订单
        order.setOrderId(orderId);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setPrice(cart.getTotalPrice());
        order.setStatus(0);
        order.setUserId(userId);
        orderDao.save(order);
        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setName(entry.getValue().getName());
            orderItem.setCount(entry.getValue().getCount());
            orderItem.setPrice(entry.getValue().getPrice());
            orderItem.setTotalPrice(entry.getValue().getTotalPrice());
            orderItem.setOrderId(orderId);
            // 保存订单项到数据库
            orderItemDao.save(orderItem);
            Book book = bookDao.findById(entry.getValue().getId());
            book.setSales(book.getSales()+entry.getValue().getCount());
            book.setStock(book.getStock()-entry.getValue().getCount());
            bookDao.updateById(book);

        }
        // 清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public Page<Order> page(Integer pageNo,Integer pageSize) throws SQLException {
        Page<Order> page = new Page<>();
        Integer totalCount = orderDao.pageRecord();//获取总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount+pageSize-1)/pageSize);//设置总页数
        page.setPageNo(pageNo);//设置当前页
        page.setItems(orderDao.page(page.getPageNo()));//设置分页查询结果集
        return page;
    }

}