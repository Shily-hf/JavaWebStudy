package edu.shily.book.service;

import edu.shily.book.pojo.User;

/**
 * @author Shily-zhang
 * @Description
 */
public interface UserService {
    User login(String uname, String pwd);
}
