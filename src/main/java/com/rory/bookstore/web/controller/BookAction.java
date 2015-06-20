package com.rory.bookstore.web.controller;

import com.rory.bookstore.domain.Book;
import com.rory.bookstore.domain.Category;
import com.rory.bookstore.service.IBookService;
import com.rory.bookstore.service.ICategoryService;
import com.rory.bookstore.service.impl.BookServiceImpl;
import com.rory.bookstore.service.impl.CategoryServiceImpl;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.FileUploadUtils;
import com.rory.bookstore.utils.StringUtils;
import com.rory.bookstore.web.bean.PageBean;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class BookAction {

    private IBookService bookService = BeanFactory.getInstance(BookServiceImpl.class);
    private ICategoryService categoryService = BeanFactory.getInstance(CategoryServiceImpl.class);

    public String showAllBooks(HttpServletRequest request, HttpServletResponse response) {
        String flag = request.getParameter("flag");
        PageBean pageBean = bookService.findBooks(request.getParameter("pageNum"));
        request.setAttribute("pageBean", pageBean);
        if ("admin".equals(flag)) {
            return "/system/book/showAllBooks.jsp";
        } else {
            return "main.jsp";
        }
    }

    public String showBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Book book = bookService.findById(id);
        List<Category> categories = categoryService.findAll();
        if (book != null) {
            request.setAttribute("book", book);
            request.setAttribute("categories", categories);
            return "/system/book/updateBook.jsp";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('查询失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/book_showAllBooks.action");
            return null;
        }
    }

    public String removeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String picturePath = bookService.findById(request.getParameter("id")).getPicturePath();
        int result = bookService.removeBook(request.getParameter("id"));
        if (result != -1) {
            FileUploadUtils.removeFile(request.getServletContext().getRealPath("/upload") + "/" + picturePath);
            return "redirect:book_showAllBooks.action";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('删除失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/book_showAllBooks.action");
            return null;
        }
    }

    public String getCategories(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        return "/system/book/addBook.jsp";
    }


    public String addBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<FileItem> fileItems = FileUploadUtils.parseRequest(request);
        if (fileItems != null) {
            Book book = new Book();
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    Object value = fileItem.getString("UTF-8");
                    String name = fileItem.getFieldName();
                    if ("categoryId".equals(fileItem.getFieldName())) {
                        name = "category";
                        value = categoryService.findById(fileItem.getString("UTF-8"));
                    } else if ("price".equals(fileItem.getFieldName())) {
                        value = Float.parseFloat(fileItem.getString("UTF-8"));
                    }
                    BeanFactory.setProperty(book, name, value);
                } else {
                    if (!StringUtils.isEmpty(fileItem.getName())) {
                        book.setPicturePath(FileUploadUtils.uploadFile(fileItem,
                                new File(request.getServletContext().getRealPath("/upload"))));
                    }
                }
            }
            bookService.addBook(book);
            return "redirect:book_showAllBooks.action?flag=admin";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('添加失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/book_showAllBooks.action");
            return null;
        }
    }

    public String updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<FileItem> fileItems = FileUploadUtils.parseRequest(request);
        if (fileItems != null) {
            Book book = null;
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    Object value = fileItem.getString("UTF-8");
                    String name = fileItem.getFieldName();
                    if ("id".equals(name)) {
                        book = bookService.findById((String) value);
                    }

                    if ("categoryId".equals(fileItem.getFieldName())) {
                        name = "category";
                        value = categoryService.findById(fileItem.getString("UTF-8"));
                    } else if ("price".equals(fileItem.getFieldName())) {
                        value = Float.parseFloat(fileItem.getString("UTF-8"));
                    }
                    BeanFactory.setProperty(book, name, value);
                } else {
                    if (book != null && !StringUtils.isEmpty(fileItem.getName())) {
                        FileUploadUtils.removeFile(request.getServletContext().getRealPath("/upload") + "/" + book.getPicturePath());
                        book.setPicturePath(FileUploadUtils.uploadFile(fileItem,
                                new File(request.getServletContext().getRealPath("/upload"))));
                    }
                }
            }
            bookService.updateBook(book);
            return "redirect:book_showAllBooks.action";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('更新失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/book_showAllBooks.action");
            return null;
        }
    }

    public String add2Cart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) request.getSession().getAttribute("shopcart");
        if (books == null) {
            books = new LinkedList<>();
        }
        Book book = bookService.findById(id);
        if (book != null) {
            books.add(book);
        }
        request.getSession().setAttribute("shopcart", books);
        request.setAttribute("msg", "添加成功");
        return "result/success.jsp";
    }

    public String removeBookFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) request.getSession().getAttribute("shopcart");
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (id.equals(book.getId())) {
                iterator.remove();
            }
        }
        return "redirect:showcart.jsp";
    }

}
