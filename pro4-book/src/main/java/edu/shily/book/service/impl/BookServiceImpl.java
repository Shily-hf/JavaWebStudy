package edu.shily.book.service.impl;

import edu.shily.book.dao.BookDAO;
import edu.shily.book.pojo.Book;
import edu.shily.book.service.BookService;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
