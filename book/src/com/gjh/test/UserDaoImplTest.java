package com.gjh.test;

import com.gjh.bean.User;
import com.gjh.dao.UserDao;
import com.gjh.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() throws SQLException {
        User user = userDao.queryUserByUsername("admin");
        System.out.println("user = " + user);
    }
    @Test
    public void queryUserByUsernameAndPassword() throws SQLException {
        User user = userDao.queryUserByUsernameAndPassword("admin","admin");
        System.out.println("user = " + user);
    }

    @Test
    public void save() throws SQLException {
        User user = new User(null,"gjh","123","123@qq.com");
        userDao.save(user);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }
}