package com.gjh.dao;

import com.gjh.bean.Admin;
import com.gjh.util.BaseInterface;

import java.sql.SQLException;

public interface AdminDao extends BaseInterface<Admin> {
    public Admin queryAdminusernameandpassword(Admin admin) throws SQLException;
}
