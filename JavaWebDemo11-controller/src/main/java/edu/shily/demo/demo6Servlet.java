package edu.shily.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description
 */

@WebServlet("/demo6")
public class demo6Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取application保存作用域保存的数据，key为uname
        ServletContext application = request.getServletContext();
        Object unameObj = application.getAttribute("uname");
        System.out.println("unameObj = " + unameObj);

    }
}
