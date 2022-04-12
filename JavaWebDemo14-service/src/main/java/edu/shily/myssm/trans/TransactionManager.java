package edu.shily.myssm.trans;

import edu.shily.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Shily-zhang
 * @Description
 */
public class TransactionManager {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    //开启事物
    public static void beginTrans() throws SQLException {

        ConnUtil.getConn().setAutoCommit(false);//关闭默认提交事物，可以进行事物回滚
    }
    //提交事物
    public static void commit() throws SQLException {

        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }
    //回滚事物
    public static void rollback() throws SQLException {

        Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    }
}
