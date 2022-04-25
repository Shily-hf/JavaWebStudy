package edu.shily.book.dao;

import edu.shily.book.pojo.Book;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface BookDAO {
    List<Book> getBookList();

    Book getBook(Integer id);
}
