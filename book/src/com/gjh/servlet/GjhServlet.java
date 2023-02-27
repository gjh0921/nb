package com.gjh.servlet;

import com.gjh.util.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GjhServlet", value = "/GjhServlet")
public class GjhServlet extends BaseServlet {
    protected void mytest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我是gjh.mytest方法");
    }
}
