package edu.shily.qqzone.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Shily-zhang
 * @Description
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    //jdbc:mysql://localhost:3306/qqzonedb2
    public static final String URL = "jdbc:mysql://localhost:3306/qqzonedb2?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "201008hf" ;

    private static Connection creatConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
