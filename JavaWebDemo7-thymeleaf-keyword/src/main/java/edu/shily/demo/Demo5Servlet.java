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
 * @Description 演示application保存作用域（demo5和demo6）
 */
@WebServlet("/demo5")
public class Demo5Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向application保存作用域保存数据
        //ServletContext：Servlet上下文
        ServletContext application = request.getServletContext();
        application.setAttribute("uname","lili");
        //客户端重定向
        response.sendRedirect("demo6");
        //服务器端转发
//        response.getRequestDispatcher("demo6").forward(request,response);
    }

}
