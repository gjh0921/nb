package com.gjh.test;

import com.gjh.bean.Book;
import com.gjh.dao.BookDao;
import com.gjh.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    @Test
    public void queryForPageTotalCount() throws SQLException {
        BookDao bookDao= new BookDaoImpl();
        List<Book> books=bookDao.queryForPageItems(0,5,null,null,new BigDecimal(70),new BigDecimal(1));
        for (Book book : books) {
            System.out.println("book = " + book);
        }
    }

    @Test
    public void queryForPageItems() {
    }
}