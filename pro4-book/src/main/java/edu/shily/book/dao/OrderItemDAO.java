package edu.shily.book.dao;

import edu.shily.book.pojo.OrderItem;

/**
 * @author Shily-zhang
 * @Description
 */
public interface OrderItemDAO {

    //添加订单项
    void addOrderItem(OrderItem orderItem);
}
