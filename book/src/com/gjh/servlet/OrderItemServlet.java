package com.gjh.servlet;

import com.gjh.bean.OrderItem;
import com.gjh.service.OrderItemService;
import com.gjh.service.impl.OrderItemServiceImpl;
import com.gjh.util.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderItemServlet", value = "/OrderItemServlet")
public class OrderItemServlet extends BaseServlet {
    private OrderItemService orderItemService=new OrderItemServiceImpl();
    protected void detailItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String orderId=req.getParameter("orderId");
        System.out.println("orderId = " + orderId);
        List<OrderItem> orderItems = orderItemService.detail(orderId);
        for (OrderItem orderItem : orderItems) {
            System.out.println("orderItem = " + orderItem);
        }
        req.setAttribute("orderItem",orderItems);
        req.getRequestDispatcher("/pages/order/detail.jsp").forward(req,resp);
    }
}
