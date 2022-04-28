package edu.shily.book.controller;

import edu.shily.book.pojo.Book;
import edu.shily.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class BookController {

    private BookService bookService;

    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "index";
    }
}
