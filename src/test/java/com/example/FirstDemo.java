package com.example;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * DBUtils教程：http://www.yiidian.com/dbutils/dbutils-insert.html
 *
 */
public class FirstDemo {
    // 驱动程序
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // URL连接
    static final String DB_URL = "jdbc:mysql://localhost:33306/db_xygw";

    //数据库信息
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        QueryRunner queryRunner = new QueryRunner();

        //第一步：注册驱动程序
        DbUtils.loadDriver(JDBC_DRIVER);

        //第二步：创建连接
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        //第三步: 创建ResultSetHandler对象

        try {
            List<Map<String, Object>> list = queryRunner.query(conn, "select a.* , b.* from t_admin a ,t_order b", new MapListHandler());
            System.out.println(list);
            for (Map<String,Object> map:list) {
                System.out.println(map.get("userRealname"));
            }
        } finally {
            //第五步：关闭连接
            DbUtils.close(conn);
        }
    }
}