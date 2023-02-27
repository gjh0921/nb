package com.gjh.servlet;

import com.gjh.bean.Book;
import com.gjh.bean.Cart;
import com.gjh.bean.CartItem;
import com.gjh.service.BookService;
import com.gjh.service.impl.BookServiceImpl;
import com.gjh.util.BaseServlet;
import com.gjh.util.WbUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        // 获取请求的参数 商品编号
        int id = WbUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book 得到图书的信息
        Book book = null;
        try {
            book = bookService.queryBookById(id);
            // 把图书信息，转换成为CartItem 商品项
            CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
            // 调用Cart.addItem(CartItem);添加商品项
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            session.setAttribute("lastName",book.getName());
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addItem(cartItem);
            System.out.println(cart);
            System.out.println("请求头 Referer 的值：" + req.getHeader("Referer"));
            // 重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WbUtils.parseInt(req.getParameter("id"), 0);
        HttpSession session=req.getSession();
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart =(Cart) session.getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WbUtils.parseInt(req.getParameter("id"),0);
        int count = WbUtils.parseInt(req.getParameter("count"), 1);
        HttpSession session=req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
