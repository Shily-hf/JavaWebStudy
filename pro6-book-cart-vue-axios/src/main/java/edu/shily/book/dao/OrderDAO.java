package edu.shily.book.dao;

import edu.shily.book.pojo.OrderBean;
import edu.shily.book.pojo.User;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);

    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);

    //获取购物车书本总数
    Integer getOrderTotalBookCount(OrderBean orderBean);
}
