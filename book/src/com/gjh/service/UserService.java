package com.gjh.service;

import com.gjh.bean.User;

import java.sql.SQLException;

public interface UserService {
    public void registUser(User user) throws SQLException;
    public User login(User user) throws SQLException;
    public boolean existsUsername(String username) throws SQLException;
}
