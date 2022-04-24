package edu.shily.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description
 */
@WebServlet("/cookie01")
public class CookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个cookie对象
        Cookie cookie = new Cookie("uname", "jim");

        //将这个Cookie对象保存到浏览器端
        response.addCookie(cookie);

        request.getRequestDispatcher("hello01.html").forward(request,response);

    }
}
