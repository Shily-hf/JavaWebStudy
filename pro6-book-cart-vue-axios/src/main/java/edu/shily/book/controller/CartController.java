package edu.shily.book.controller;

import com.google.gson.Gson;
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

    public String editCart(Integer cartItemId,Integer buyCount){

        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));

        return "";
    }

    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(user);

        //调用Cart中的三个属性的get方法，目的是在此处计算这三个属性的值，否则这三个属性为null,
        //导致的结果就是下一步的gson转换时，为null的属性会被忽略
        cart.getTotalMoney();
        cart.getTotalCount();
        cart.getTotalBookCount();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);

        return "json:"+cartJsonStr;
    }
}
