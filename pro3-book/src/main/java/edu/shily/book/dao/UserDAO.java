package edu.shily.book.dao;

import edu.shily.book.pojo.User;

/**
 * @author Shily-zhang
 * @Description
 */
public interface UserDAO {
    User getUser(String uname,String pwd);
}
