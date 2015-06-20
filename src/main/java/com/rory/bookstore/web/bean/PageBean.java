package com.rory.bookstore.web.bean;

import java.util.List;

/**
 * Created by RoryGao on 15/6/20.
 */
public class PageBean {

    private int pageSize = 3;//每页多少条数据
    private int totalPages;//总页数
    private int currentPage = 1; //当前页

    private int totalRecords;//总记录数
    private int startIndex;//开始位置索引

    private List<?> records;


    public PageBean(int currentPage, int totalRecords) {
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;

        this.startIndex = (currentPage - 1) * pageSize;

        this.totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<?> getRecords() {
        return records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }
}
