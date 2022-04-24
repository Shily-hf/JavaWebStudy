package edu.shily.book.service;

import edu.shily.book.pojo.OrderBean;
import edu.shily.book.pojo.User;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface OrderService {
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);
}
