package com.gjh.dao;


import com.gjh.bean.Book;
import com.gjh.util.BaseInterface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookDao extends BaseInterface<Book> {
    public Integer queryForPageTotalCount() throws SQLException;
    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException;
    public Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException;
    public List<Book> queryForPageItems(int begin, int pageSize,String name, String author,BigDecimal min, BigDecimal max) throws SQLException;

}
