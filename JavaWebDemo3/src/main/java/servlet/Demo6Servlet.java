package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description 演示服务器端内部转发以及客户端重定向
 */
public class Demo6Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo6......");
        //服务器端内部转发
//        req.getRequestDispatcher("demo7").forward(req,resp);
        //客户端重定向
        resp.sendRedirect("demo7");
    }
}
