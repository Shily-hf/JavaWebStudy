package edu.shily.book.service;

import edu.shily.book.pojo.Cart;
import edu.shily.book.pojo.CartItem;
import edu.shily.book.pojo.User;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);
    Cart getCart(User user);
    //获取指定用户的所有购物车列表（需要注意的是：这个方法内部查询的时候，会将book的详情信息设置进去）
    List<CartItem> getCartItemList(User user);

}
