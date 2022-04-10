package edu.shily.fruit.controllers;

import edu.shily.fruit.dao.FruitDAO;
import edu.shily.fruit.dao.impl.FruitImpl;
import edu.shily.fruit.pojo.Fruit;
import edu.shily.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Shily-zhang
 * @Description 用于去处理请求对应的方法
 */

public class FruitController {
    private FruitDAO fruitDAO = new FruitImpl();

    //查
    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark) {
        //执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //资源跳转
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    //改
    private String edit(Integer fid, HttpServletRequest request){
        if (fid != null) {
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            //super.processTemplate("edit", request, response);
            return "edit";

        }else {
            return "error";
        }
    }

    //删
    private String del(Integer fid){
        if (fid != null){
            fruitDAO.delFruit(fid);
            //response.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    //增
    private String add(String fname,Integer price,Integer fcount,String remark){
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitDAO.addFruit(fruit);
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request){
        HttpSession session = request.getSession();

        if (pageNo == null){
            pageNo = 1;
        }
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {

            pageNo = 1;

            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
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

        int fruitCount = fruitDAO.getFruitCount(keyword);

        int pageCount = (fruitCount + 5 - 1) / 5;

        session.setAttribute("pageCount", pageCount);

        return "index";
    }
}
