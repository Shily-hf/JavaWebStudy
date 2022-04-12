package edu.shily.fruit.controllers;

import edu.shily.fruit.pojo.Fruit;
import edu.shily.fruit.service.FruitService;
import edu.shily.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Shily-zhang
 * @Description 用于去处理请求对应的方法
 */

public class FruitController {
    private FruitService fruitService = null;

    //查
    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark) {
        //执行更新
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //资源跳转
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    //改
    private String edit(Integer fid, HttpServletRequest request){
        if (fid != null) {
            Fruit fruit = fruitService.getFruitByFid(fid);
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
            fruitService.delFruit(fid);
            //response.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    //增
    private String add(String fname,Integer price,Integer fcount,String remark){
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
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


        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        int pageCount = fruitService.getPageCount(keyword);

        session.setAttribute("pageCount", pageCount);

        return "index";
    }
}
