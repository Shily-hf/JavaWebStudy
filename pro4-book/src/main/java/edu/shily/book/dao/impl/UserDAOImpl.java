package edu.shily.book.dao.impl;

import edu.shily.book.dao.UserDAO;
import edu.shily.book.myssm.basedao.BaseDAO;
import edu.shily.book.pojo.User;

/**
 * @author Shily-zhang
 * @Description
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ?",uname,pwd);
    }
}
