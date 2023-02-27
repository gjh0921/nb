package com.gjh.service.impl;

import com.gjh.bean.User;
import com.gjh.dao.UserDao;
import com.gjh.dao.impl.UserDaoImpl;
import com.gjh.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registUser(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public User login(User user) throws SQLException{
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) throws SQLException{
        User user = userDao.queryUserByUsername(username);
        return user!=null;
    }
}
