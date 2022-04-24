package edu.shily.book.dao.impl;

import edu.shily.book.dao.OrderDAO;
import edu.shily.book.myssm.basedao.BaseDAO;
import edu.shily.book.pojo.OrderBean;
import edu.shily.book.pojo.User;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser = ? ",user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql = "select sum(t3.buyCount) As totalBookCount , t3.orderBean from " +
                "(" + "select t1.id ,t2.buyCount ,t2.orderBean from t_order t1 inner join t_order_item t2 " +
                "on t1.id = t2.orderBean where t1.orderUser = ?" +
                ") t3 where t3.orderBean = ? group by t3.orderBean";
        return ((BigDecimal) executeComplexQuery(sql,orderBean.getOrderUser().getId(),orderBean.getId())[0]).intValue();
    }
}
