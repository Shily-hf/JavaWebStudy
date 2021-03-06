package edu.shily.book.controller;

import edu.shily.book.pojo.OrderBean;
import edu.shily.book.pojo.User;
import edu.shily.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Shily-zhang
 * @Description
 */
public class OrderController {

    private OrderService orderService;

    //结账
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();

        orderBean.setOrderNo(UUID.randomUUID().toString() + "_" +LocalDateTime.now());
        orderBean.setOrderDate(LocalDateTime.now());

        User user = (User) session.getAttribute("currentUser");
        orderBean.setOrderUser(user);

        orderBean.setOrderMoney(user.getCart().getTotalMoney());

        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);

        return "index";
    }

    //查看订单列表
    public String getOrderList(HttpSession session){
        User user = (User) session.getAttribute("currentUser");

        List<OrderBean> orderList = orderService.getOrderList(user);
        user.setOrderList(orderList);

        session.setAttribute("currentUser",user);

        return "order/order";
    }
}
