package com.gjh.servlet;

import com.gjh.bean.Admin;
import com.gjh.service.AdminService;
import com.gjh.service.impl.AdminServiceImpl;
import com.gjh.util.BaseDao;
import com.gjh.util.BaseServlet;
import com.gjh.util.WbUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends BaseServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = WbUtils.copyParamToBean(request.getParameterMap(), new Admin());
        AdminService adminService =new AdminServiceImpl();
        try {
            Admin myadmin = adminService.login(admin);
            if(myadmin!=null){
                HttpSession session = request.getSession();
                session.setAttribute("admin",myadmin);
                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
