package com.gjh.servlet;

import com.gjh.bean.Book;
import com.gjh.service.BookService;
import com.gjh.service.impl.BookServiceImpl;
import com.gjh.util.BaseServlet;
import com.gjh.util.Page;
import com.gjh.util.WbUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> bookList = bookService.queryBooks();
            request.setAttribute("bookList", bookList);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Book book = new Book();
        BookService bookService = new BookServiceImpl();
        if (ServletFileUpload.isMultipartContent(request)) {
            //创建FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload 类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                // 解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环这6段数据并处理它们
                for (FileItem fileItem : list) {
                    //判断那些是普通表单项,还是上传的文件类型
                    if (fileItem.isFormField()) {
                        //处理普通表单项
                        //System.out.println(fileItem.getFieldName() +" = " + MyUtils.parseString(fileItem.getString()));
                        if ("name".equals(fileItem.getFieldName())) {
                            book.setName(fileItem.getString("utf-8"));//图书名
                        } else if ("author".equals(fileItem.getFieldName())) {
                            book.setAuthor(fileItem.getString("utf-8"));//图书作者
                        } else if ("price".equals(fileItem.getFieldName())) {
                            book.setPrice(new BigDecimal(fileItem.getString()));//图书价格
                        } else if ("sales".equals(fileItem.getFieldName())) {
                            book.setSales(Integer.valueOf(fileItem.getString()));//图书销量
                        } else if ("stock".equals(fileItem.getFieldName())) {
                            book.setStock(Integer.parseInt(fileItem.getString()));//图书库存
                        }
                    } else {
                        //处理文件类型(文件上传)
                        String filename = fileItem.getName();//获取文件名
                        //文件名 = 123.jpg       suffix = .jpg
                        String suffix = filename.substring(filename.lastIndexOf("."));
                        //通过时间毫秒 + 后缀 = 新文件名
                        String newfilename = System.currentTimeMillis() + suffix;
                        ServletContext application = this.getServletContext();
                        //得到了工程路径
                        String realPath = application.getRealPath("/");

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String format = simpleDateFormat.format(new Date());
                        File file = new File("C:/bookimg/" + format + "/");
                        if (!file.exists()) {//判断要上传的文件目录是否存在
                            file.mkdirs();//创建目录
                        }
                        System.out.println(file.getAbsolutePath());
                        fileItem.write(new File(file, newfilename));//上传图片
                        book.setImgPath("/bookimg/" + format + "/" + newfilename);//图书封面
                    }
                }
                bookService.addBook(book);//将图片信息保存到数据库
                //request.getRequestDispatcher("/BookServlet?action=list").forward(request, response);
                //response.sendRedirect("pages/manager/book_manager.jsp");
                response.sendRedirect("BookServlet?action=page");
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            out.println("不是多段数据..无法上传文件!");
        }
    }

    protected void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageNo = request.getParameter("pageNo");
        BookService bookService = new BookServiceImpl();
        try {
            Book book = bookService.queryBookById(Integer.valueOf(id));
            request.setAttribute("book", book);
            request.setAttribute("pageNo",pageNo);
            request.getRequestDispatcher("pages/manager/book_edit.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String pageNo = request.getParameter("pageNo");
        PrintWriter out = response.getWriter();
        Book book = new Book();
        BookService bookService = new BookServiceImpl();
        if(ServletFileUpload.isMultipartContent(request)){
            //创建FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload 类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                // 解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环这6段数据并处理它们
                for (FileItem fileItem : list) {
                    //判断那些是普通表单项,还是上传的文件类型
                    if(fileItem.isFormField()){
                        //处理普通表单项
                        //System.out.println(fileItem.getFieldName() +" = " + MyUtils.parseString(fileItem.getString()));
                        if("id".equals(fileItem.getFieldName())){
                            book.setId(Integer.valueOf(fileItem.getString()));
                            book=bookService.queryBookById(book.getId());
                        }
                        if("name".equals(fileItem.getFieldName())){
                            book.setName(fileItem.getString("utf-8"));//图书名
                        }else if("author".equals(fileItem.getFieldName())){
                            book.setAuthor(fileItem.getString("utf-8"));//图书作者
                        }else if("price".equals(fileItem.getFieldName())){
                            book.setPrice(new BigDecimal(fileItem.getString()));//图书价格
                        }else if("sales".equals(fileItem.getFieldName())){
                            book.setSales(Integer.valueOf(fileItem.getString()));//图书销量
                        }else if("stock".equals(fileItem.getFieldName())){
                            book.setStock(Integer.parseInt(fileItem.getString()));//图书库存
                        }else if("oldPath".equals(fileItem.getFieldName())){
                            book.setImgPath(fileItem.getString());
                        }
                    }else{
                        //处理文件类型(文件上传)
                        String filename = fileItem.getName();//获取文件名
                        //文件名 = 123.jpg       suffix = .jpg
                        if(!filename.equals("")) {
                            String suffix = filename.substring(filename.lastIndexOf("."));
                            //通过时间毫秒 + 后缀 = 新文件名
                            String newfilename = System.currentTimeMillis() + suffix;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String format = simpleDateFormat.format(new Date());
                            File file = new File("C:/bookimg/" + format + "/");
                            if (!file.exists()) {//判断要上传的文件目录是否存在
                                file.mkdirs();//创建目录
                            }
                            System.out.println(file.getAbsolutePath());
                            fileItem.write(new File(file, newfilename));//上传图片
                            String path="C:/"+book.getImgPath();
                            WbUtils.deleteFile(path);
                            book.setImgPath("/bookimg/" + format + "/" + newfilename);//图书封面

                        }
                    }
                }
                System.out.println("book = " + book);
                bookService.updateBook(book);//将图片信息保存到数据库
                response.sendRedirect("BookServlet?action=page&pageNo="+pageNo);
                //response.sendRedirect("BookServlet?action=page"+"&pageNo=");
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            out.println("不是多段数据..无法上传文件!");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WbUtils.parseInt(request.getParameter("id"), 0);
        String pageNo = request.getParameter("pageNo");
        try {
            Book book = bookService.queryBookById(id);
            String path="C:/"+book.getImgPath();
            WbUtils.deleteFile(path);
            bookService.deleteBookById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("BookServlet?action=page"+"&pageNo="+pageNo);
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WbUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WbUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("BookServlet?action=page&");
        //3 保存Page 对象到Request 域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    protected void searchPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1 获取请求的参数 pageNo 和 pageSize
        String name = req.getParameter("name")==null?"":req.getParameter("name");
        String author = req.getParameter("author")==null?"":req.getParameter("author");
        Integer min = WbUtils.parseInt(req.getParameter("min"),0);
        Integer max = WbUtils.parseInt(req.getParameter("max"),0);
        int pageNo = WbUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WbUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page 对象
        req.setAttribute("name",name);
        req.setAttribute("author",author);
        req.setAttribute("min",req.getParameter("min"));
        req.setAttribute("max",req.getParameter("max"));
        try {
            Page<Book>  page=bookService.page(pageNo,pageSize,name,author,new BigDecimal(min),new BigDecimal(max));
            page.setUrl("BookServlet?action=searchPage&name="+name+"&author="+author+"&min="+min+"&max="+max+"&");
            //3 保存Page 对象到Request 域中
            req.setAttribute("page",page);
            System.out.println(page);
            //4 请求转发到pages/manager/book_manager.jsp 页面
            req.getRequestDispatcher("/home.jsp").forward(req,resp);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
