package edu.shily.book.service.impl;

import edu.shily.book.dao.UserDAO;
import edu.shily.book.pojo.User;
import edu.shily.book.service.UserService;

/**
 * @author Shily-zhang
 * @Description
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }
}
