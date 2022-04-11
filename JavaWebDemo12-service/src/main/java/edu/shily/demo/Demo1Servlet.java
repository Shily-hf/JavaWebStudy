package edu.shily.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description 演示request保存作用域（demo1和demo2）
 */
@WebServlet("/demo1")
public class Demo1Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向request保存作用域保存数据
        request.setAttribute("uname","lili");
        //客户端重定向
//        response.sendRedirect("demo2");
        //服务器端转发
        request.getRequestDispatcher("demo2").forward(request,response);
    }

}
