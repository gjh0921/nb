package com.gjh.test;

import com.gjh.bean.Book;
import com.gjh.bean.Order;
import com.gjh.bean.User;
import com.gjh.dao.BookDao;
import com.gjh.dao.OrderDao;
import com.gjh.dao.UserDao;
import com.gjh.dao.impl.BookDaoImpl;
import com.gjh.dao.impl.OrderDaoImpl;
import com.gjh.dao.impl.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class gjhService {
    /*private BookDao bookDao=new BookDaoImpl();
    private OrderDao orderDao= new OrderDaoImpl();
    private UserDao userDao=new UserDaoImpl();
    public void gjhTest(){
        try {
            //打开事务
            Connection conn=null;
            conn.setAutoCommit(false);//设置手动提交
            bookDao.updateById(new Book());
            orderDao.updateById(new Order());
            userDao.save(new User());
            //事务提交
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            //事务回滚
        }
    }*/
}
