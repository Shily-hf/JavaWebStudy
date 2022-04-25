package edu.shily.book.dao.impl;

import edu.shily.book.dao.OrderItemDAO;
import edu.shily.book.myssm.basedao.BaseDAO;
import edu.shily.book.pojo.OrderItem;

/**
 * @author Shily-zhang
 * @Description
 */
public class OrderItemImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
