package edu.shily.book.controller;

import edu.shily.book.pojo.User;
import edu.shily.book.service.UserService;

/**
 * @author Shily-zhang
 * @Description
 */
public class UserController {

    private UserService userService;

    public String login(String uname,String pwd){
        User user = userService.login(uname, pwd);

        System.out.println("user =" + user);
        return "index";
    }
}
