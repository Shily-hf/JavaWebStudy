package edu.shily.book.controller;

import edu.shily.book.pojo.Cart;
import edu.shily.book.pojo.User;
import edu.shily.book.service.CartItemService;
import edu.shily.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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

    public String regist(String verifyCode,String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptchaCodeObj==null || !verifyCode.equals(kaptchaCodeObj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>alert('验证码有误！');window.location.href='page.do?operate=page&page=user/regist';</script>");

//            return "user/regist";
            return "";
        }else {
            if (verifyCode.equals(kaptchaCodeObj)){
                userService.regist(new User(uname,pwd,email,0));
                return "user/login";
            }
        }
        return "user/login";
    }
}
