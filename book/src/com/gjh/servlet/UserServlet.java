package com.gjh.servlet;

import com.gjh.bean.User;
import com.gjh.service.UserService;
import com.gjh.service.impl.UserServiceImpl;
import com.gjh.util.BaseServlet;
import com.gjh.util.CookieUtils;
import com.gjh.util.WbUtils;
import com.google.code.kaptcha.Constants;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> parameterMap= request.getParameterMap();
        User user = new User();
        WbUtils.copyParamToBean(parameterMap,user);
        UserService userService = new UserServiceImpl();
        try {
           User myuser = userService.login(user);
            if (myuser == null) {
                request.setAttribute("msg","用户名或密码不正确！");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("password",user.getPassword());
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            } else {
                Cookie namecookie = new Cookie("username",user.getUsername());
                Cookie passcookie = new Cookie("password",user.getPassword());
                namecookie.setMaxAge(60*60*24*7);
                passcookie.setMaxAge(60*60*24*7);
                response.addCookie(namecookie);
                response.addCookie(passcookie);

                HttpSession session= request.getSession();
                session.setAttribute("user",myuser);//登陆成功后的个人信息保存到session会话作用域中
                request.setAttribute("msg","欢迎回来");
                if(request.getParameter("gjhurl")!=null&&!request.getParameter("gjhurl").equals("")){
                    request.getRequestDispatcher(request.getParameter("gjhurl")).forward(request,response);
                }else {
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        // 获取Session 中的验证码h
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = request.getParameter("code");//验证码
        Map<String,String[]> parameterMap= request.getParameterMap();
        User user = new User();
        WbUtils.copyParamToBean(parameterMap,user);
        request.setAttribute("user",user);
        UserService userService = new UserServiceImpl();
        try {
            if ("1234".equalsIgnoreCase(code)) {
                if (!userService.existsUsername(user.getUsername())){
                    userService.registUser(user);
                    session.setAttribute("user",user);
                    request.setAttribute("msg","注册成功");
                    request.getRequestDispatcher("pages/user/success.jsp").forward(request,response);

                }
                else {
                    request.setAttribute("msg","用户名已存在，请更换");
                    request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg","验证码不正确");
                request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.invalidate();
        Cookie nameCookie = CookieUtils.findCookie("username",request.getCookies());
        Cookie passCookie = CookieUtils.findCookie("password",request.getCookies());
        if(nameCookie!=null){
            nameCookie.setMaxAge(0);
            response.addCookie(nameCookie);
        }
        else if(passCookie!=null){
            passCookie.setMaxAge(0);
            response.addCookie(passCookie);
        }
        response.sendRedirect("index.jsp");
    }
}
