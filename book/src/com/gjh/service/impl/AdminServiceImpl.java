package com.gjh.service.impl;

import com.gjh.bean.Admin;
import com.gjh.dao.AdminDao;
import com.gjh.dao.impl.AdminDaoImpl;
import com.gjh.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao=new AdminDaoImpl();
    @Override
    public Admin login(Admin admin) throws SQLException {
        return adminDao.queryAdminusernameandpassword(admin);
    }
}
