package com.rory.bookstore.web.controller;

import com.rory.bookstore.domain.Category;
import com.rory.bookstore.service.ICategoryService;
import com.rory.bookstore.service.impl.CategoryServiceImpl;
import com.rory.bookstore.utils.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class CategoryAction {

    private ICategoryService service = BeanFactory.getInstance(CategoryServiceImpl.class);

    public String addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("name");
        String description = request.getParameter("description");
        Category category = new Category(categoryName, description);
        int result = service.addCategory(category);
        if (result != -1) {
            return "redirect:category_showAllCategories.action";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('添加失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/system/category/addCategory.jsp");
            return null;
        }
    }

    public String removeCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int result = service.removeCategory(request.getParameter("id"));
        if (result != -1) {
            return "redirect:category_showAllCategories.action";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('删除失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/category_showAllCategories.action");
            return null;
        }
    }

    public String showAllCategories(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = service.findAll();
        request.setAttribute("categories", categories);
        return "/system/category/showAllCategories.jsp";
    }

    public String showCategoryInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Category category = service.findById(id);
        if (category != null) {
            request.setAttribute("category", category);
            return "/system/category/updateCategory.jsp";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('查询失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/category_showAllCategories.action");
            return null;
        }
    }

    public String updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String categoryName = request.getParameter("name");
        String description = request.getParameter("description");
        Category category = new Category(id, categoryName, description);

        int result = service.updateCategory(category);
        if (result != -1) {
            return "redirect:category_showAllCategories.action";
        } else {
            response.getWriter().write("<script type='text/javascript'>alert('更新失败！')</script>");
            response.setHeader("Refresh", "0;URL=" + request.getContextPath() + "/category_showAllCategories.action");
            return null;
        }
    }
}
