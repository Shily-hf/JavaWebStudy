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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Integer pageNo = 1;

        String oper = request.getParameter("oper");
        //如果oper != null 说明通过表单的查询按钮点击过来的
        //如果oper 是空的，说明不是通过表单查询按钮点击过来的
        String keyword = null;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为,keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else {
            //说明此处不是点击表单查询发送过来的请求（比如点击上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该是从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }


        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);

        //总记录数
        int fruitCount = fruitDAO.getFruitCount(keyword);
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
