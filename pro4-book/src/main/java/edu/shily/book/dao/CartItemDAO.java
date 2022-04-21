package edu.shily.book.dao;

import edu.shily.book.pojo.CartItem;
import edu.shily.book.pojo.User;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface CartItemDAO {
    //新增购物车项
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
}
