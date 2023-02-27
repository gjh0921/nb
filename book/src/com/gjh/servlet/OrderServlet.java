package com.gjh.servlet;

import com.gjh.bean.*;
import com.gjh.service.OrderService;
import com.gjh.service.impl.OrderServiceImpl;
import com.gjh.util.Page;
import com.gjh.util.WbUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BookServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session=req.getSession();
        // 先获取Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            req.setAttribute("msg","登陆超时，请重新登陆！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
        Integer userId = user.getId();
        String orderId = orderService.createOrder(cart, userId);
        session.setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
    protected void listOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
       OrderService orderService=new OrderServiceImpl();
        int pageNo = WbUtils.parseInt(req.getParameter("pageNo"), 1);
        int pagesize = WbUtils.parseInt(req.getParameter("pageSize"), 4);
        try {
            Page<Order> page=orderService.page(pageNo,pagesize);
            page.setUrl("OrderServlet?action=listOrder&");
            req.setAttribute("page",page);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        } catch (SQLException | ServletException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
