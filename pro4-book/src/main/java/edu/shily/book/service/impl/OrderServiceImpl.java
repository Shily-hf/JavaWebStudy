package edu.shily.book.service.impl;

import edu.shily.book.dao.CartItemDAO;
import edu.shily.book.dao.OrderDAO;
import edu.shily.book.dao.OrderItemDAO;
import edu.shily.book.pojo.CartItem;
import edu.shily.book.pojo.OrderBean;
import edu.shily.book.pojo.OrderItem;
import edu.shily.book.pojo.User;
import edu.shily.book.service.OrderService;

import java.util.Map;

/**
 * @author Shily-zhang
 * @Description
 */
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;

    @Override
    public void addOrderBean(OrderBean orderBean) {
//        1）订单添加一条记录
//        2）订单详情表添加7条记录
//        3）购物车项表中需要删除对应的7条记录
        //第一步
        orderDAO.addOrderBean(orderBean);   //执行完这一步,orderBean中的id是有值的
        //第二步
        //orderBean中的orderItemList是空的，此处我们应该根据用户购物车项去转换成一个个订单项
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }
        //第三步

        for (CartItem cartItem : cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);
        }

    }
}
