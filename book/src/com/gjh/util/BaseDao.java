package com.gjh.util;

import org.apache.commons.dbutils.QueryRunner;

public abstract class BaseDao {
    public QueryRunner queryRunner;
    public int pageSize=4;
    public BaseDao(){
        queryRunner =new QueryRunner(com.gjh.util.MyDataSource.getDataSource());
    }

}
