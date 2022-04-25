package edu.shily.book.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Shily-zhang
 * @Description
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static Properties properties = new Properties();

    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection creatConn(){
        try {
            DataSource druidDataSource = DruidDataSourceFactory.createDataSource(properties);

            return druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Connection getConn(){
        //获取conn对象
        Connection conn = threadLocal.get();

        if (conn == null){
            conn = creatConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();

        if (conn == null){
           return;
        }
        if (conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
