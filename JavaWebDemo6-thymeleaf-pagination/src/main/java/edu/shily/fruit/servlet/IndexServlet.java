package edu.shily.fruit.servlet;

import edu.shily.fruit.dao.FruitDAO;
import edu.shily.fruit.dao.impl.FruitImpl;
import edu.shily.fruit.pojo.Fruit;
import edu.shily.myssm.myspringmvc.ViewBaseServlet;
import edu.shily.myssm.util.StringUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = 1;

        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }

        HttpSession session = request.getSession();
        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo);
        session.setAttribute("fruitList",fruitList);

        //总记录数
        int fruitCount = fruitDAO.getFruitCount();
        //总页数
        int pageCount = (fruitCount + 5 - 1)/5;

        session.setAttribute("pageCount",pageCount);


        //此处的视图名称是index
        //那么thymeleaf会将这个逻辑视图名称对应到物理视图名称上去
        //逻辑视图名称：   index
        //物理视图名称：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：/index.html
        super.processTemplate("index",request,response);
    }
}
