package edu.shily.book.dao.impl;

import edu.shily.book.dao.CartItemDAO;
import edu.shily.book.myssm.basedao.BaseDAO;
import edu.shily.book.pojo.CartItem;
import edu.shily.book.pojo.User;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public void addCartItem(CartItem cartItem) {
        executeUpdate("insert into t_cart_item values(0,?,?,?)",cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        executeUpdate("update t_cart_item set buyCount = ? where id = ?",cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return executeQuery("select * from t_cart_item where userBean = ? ",user.getId());
    }
}
