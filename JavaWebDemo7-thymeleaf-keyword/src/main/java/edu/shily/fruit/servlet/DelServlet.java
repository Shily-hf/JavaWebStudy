package edu.shily.fruit.servlet;
import edu.shily.fruit.dao.FruitDAO;
import edu.shily.fruit.dao.impl.FruitImpl;
import edu.shily.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {

    private FruitDAO fruitDao = new FruitImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        fruitDao.delFruit(fid);

        response.sendRedirect("index");
    }
}
