package edu.shily.book.dao.impl;

import edu.shily.book.dao.OrderDAO;
import edu.shily.book.myssm.basedao.BaseDAO;
import edu.shily.book.pojo.OrderBean;

/**
 * @author Shily-zhang
 * @Description
 */
public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {

    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0,?,?,?,?,?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
        //思考：此处为什么要接收executeUpdate的返回值，然后设置到OrderBean中的id属性上？
    }
}
