package com.gjh.util;

import java.sql.SQLException;
import java.util.List;

public interface BaseInterface<T> {
    /*
     * 查询表中所有数据、
     * 添加数据
     * 根据id进行数据修改
     * 根据id删除数据
     * 根据id查询数据
     * 无条件分页查询 页码
     * @return
     */
    public List<T> findAll() throws SQLException;
    public void save(T t) throws SQLException;
    public void updateById(T t) throws SQLException;
    public void deleteById(Integer id) throws SQLException;
    public T findById(Integer id) throws SQLException;
    public List<T> page(Integer pageNumber) throws SQLException;
    public Integer pageRecord() throws SQLException;
}
