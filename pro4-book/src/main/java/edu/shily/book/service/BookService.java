package edu.shily.book.service;

import edu.shily.book.pojo.Book;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface BookService {
    List<Book> getBookList();

    Book getBook(Integer id);
}
