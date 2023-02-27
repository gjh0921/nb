package com.gjh.service;

import com.gjh.bean.Admin;

import java.sql.SQLException;

public interface AdminService {
    public Admin login(Admin admin) throws SQLException;
}
