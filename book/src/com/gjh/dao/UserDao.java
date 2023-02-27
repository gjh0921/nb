package com.gjh.dao;

import com.gjh.bean.User;
import com.gjh.util.BaseInterface;

import java.sql.SQLException;

public interface UserDao extends BaseInterface<User> {
    public User queryUserByUsername(String username) throws SQLException;
    public User queryUserByUsernameAndPassword(String username,String password) throws SQLException;
}
