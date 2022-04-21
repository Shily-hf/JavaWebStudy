package edu.shily.book.controller;

import edu.shily.book.pojo.Book;
import edu.shily.book.pojo.Cart;
import edu.shily.book.pojo.CartItem;
import edu.shily.book.pojo.User;
import edu.shily.book.service.CartItemService;

import javax.servlet.http.HttpSession;

/**
 * @author Shily-zhang
 * @Description
 */
public class CartController {

    private CartItemService cartItemService;

    //加载当前用户的购物车的信息
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currentUser",user);

        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

         return "redirect:cart.do";
    }
}
