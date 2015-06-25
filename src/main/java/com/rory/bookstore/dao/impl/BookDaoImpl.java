package com.rory.bookstore.dao.impl;

import com.rory.bookstore.dao.IBookDao;
import com.rory.bookstore.domain.Book;
import com.rory.bookstore.domain.Category;
import com.rory.bookstore.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by RoryGao on 15/6/13.
 */
public class BookDaoImpl implements IBookDao {
    @Override
    public int addBook(Book book) throws SQLException {
        final String sql = "insert into tb_book values(?,?,?,?,?,?,?)";
        return DBUtils.executeUpdate(sql,
                book.getId(), book.getName(), book.getAuthor(),
                book.getPrice(), book.getPicturePath(), book.getCategory().getId(), book.getDescription());
    }

    @Override
    public int removeBook(String bookId) throws SQLException {
        final String sql = "delete from tb_book where id = ?";
        return DBUtils.executeUpdate(sql, bookId);
    }

    @Override
    public int updateBook(Book book) throws SQLException {
        final String sql = "update tb_book set " +
                "name = ?, author = ?, price = ?, picture_path = ?, category_id = ?, description = ? where id = ?";
        return DBUtils.executeUpdate(sql,
                book.getName(), book.getAuthor(), book.getPrice(), book.getPicturePath(), book.getCategory().getId(),
                book.getDescription(), book.getId());
    }

    @Override
    public Book findById(String bookId) throws SQLException {
        final String sql = "SELECT b.id, b.name, b.author, b.price, b.picture_path, b.description, " +
                "c.id as categoryId, c.name as categoryName, c.description as categoryDesc " +
                "from tb_book b INNER JOIN tb_category c ON c.id = b.category_id WHERE b.id = ?";
        ResultSet rs = DBUtils.executeQuery(sql, bookId);
        Book book = null;
        if (rs.next()) {
            Category category = new Category();
            category.setId(rs.getString("categoryId"));
            category.setName(rs.getString("categoryName"));
            category.setDescription(rs.getString("categoryDesc"));
            book = new Book(rs.getString("id"), rs.getString("name"), rs.getString("author"),
                    rs.getFloat("price"), rs.getString("picture_path"), category,
                    rs.getString("description"));
        }
        rs.close();
        DBUtils.closeConnection();
        return book;
    }

    @Override
    public List<Book> findPageRecords(int startIndex, int pageSize) throws SQLException {
        final String sql = "SELECT b.id, b.name, b.author, b.price, b.picture_path, b.description," +
                " c.id as categoryId, c.name as categoryName, c.description as categoryDesc " +
                "from tb_book b INNER JOIN tb_category c ON c.id = b.category_id order by b.name limit ?, ? ";
        ResultSet rs = DBUtils.executeQuery(sql, startIndex, pageSize);
        List<Book> books = new LinkedList<>();
        if (rs != null) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getFloat("price"));
                book.setPicturePath(rs.getString("picture_path"));
                book.setDescription(rs.getString("description"));
                Category category = new Category();
                category.setId(rs.getString("categoryId"));
                category.setName(rs.getString("categoryName"));
                category.setDescription(rs.getString("categoryDesc"));
                book.setCategory(category);
                books.add(book);
            }
        }
        rs.close();
        DBUtils.closeConnection();
        return books;
    }

    @Override
    public int findBookCount() throws SQLException {
        final String sql = "select count(*) as totalCount from tb_book";
        ResultSet rs = DBUtils.executeQuery(sql);
        if (rs.next()) {
            return Integer.parseInt(rs.getString("totalCount"));
        }
        return 0;
    }
}
