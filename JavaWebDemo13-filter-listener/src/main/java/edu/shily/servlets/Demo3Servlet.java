package edu.shily.servlets;

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
@WebServlet("/demo03.do")
public class Demo3Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo3 service......");
        request.getRequestDispatcher("success.html").forward(request,response);
    }

}
