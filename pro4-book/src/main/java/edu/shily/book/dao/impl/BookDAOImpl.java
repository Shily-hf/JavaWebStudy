package edu.shily.book.dao.impl;

import edu.shily.book.dao.BookDAO;
import edu.shily.book.myssm.basedao.BaseDAO;
import edu.shily.book.pojo.Book;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book where bookStatus=0");
    }

    @Override
    public Book getBook(Integer id) {
        return load("select * from t_book where id = ?" ,id);
    }
}
