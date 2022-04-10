package edu.shily.fruit.controllers;

import edu.shily.fruit.dao.FruitDAO;
import edu.shily.fruit.dao.impl.FruitImpl;
import edu.shily.fruit.pojo.Fruit;
import edu.shily.myssm.myspringmvc.ViewBaseServlet;
import edu.shily.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Shily-zhang
 * @Description 用于去处理请求对应的方法
 */

public class FruitController extends ViewBaseServlet {

    //之前FruitServlet是一个Servlet组件，那么其中的init方法一定会被调用
    //之前的init方法内部会出现一句话：super.init();
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(servletContext);
    }

    private FruitDAO fruitDAO = new FruitImpl();

    //查
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取参数
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String pricrStr = request.getParameter("price");
        int price = Integer.parseInt(pricrStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //资源更新
//        super.processTemplate("index",request,response);
        //request.getRequestDispatcher("index.html").forward(request,response);
        //此处需要重定向，目的是重新给IndexServlet发请求，然后从新获取fruitList,然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("fruit.do");
    }

    //改
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            super.processTemplate("edit", request, response);

        }
    }

    //删
    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        fruitDAO.delFruit(fid);

        response.sendRedirect("fruit.do");
    }

    //增
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);

        fruitDAO.addFruit(fruit);

        response.sendRedirect("fruit.do");
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer pageNo = 1;

        String oper = request.getParameter("oper");
        //如果oper != null 说明通过表单的查询按钮点击过来的
        //如果oper 是空的，说明不是通过表单查询按钮点击过来的
        String keyword = null;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为,keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            //说明此处不是点击表单查询发送过来的请求（比如点击上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该是从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }


        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        //总记录数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1) / 5;

        session.setAttribute("pageCount", pageCount);


        //此处的视图名称是index
        //那么thymeleaf会将这个逻辑视图名称对应到物理视图名称上去
        //逻辑视图名称：   index
        //物理视图名称：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：/index.html
        super.processTemplate("index", request, response);
    }
}
