package edu.shily.book.controller;

import edu.shily.book.pojo.Cart;
import edu.shily.book.pojo.User;
import edu.shily.book.service.CartItemService;
import edu.shily.book.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @author Shily-zhang
 * @Description
 */
public class UserController {

    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session){

        User user = userService.login(uname, pwd);

        if (user != null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);


            session.setAttribute("currentUser",user);
            return "redirect:book.do";
        }
        return "user/login";
    }
}
