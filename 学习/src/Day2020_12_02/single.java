package Day2020_12_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class single {
    private static Connection con;

    private single() {
    }
    public static Connection nianjie() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName("driver");
        String url = "jdbc:mysql://localhost:3306/test";//数据库地址
        String user = "root";//使用人
        String password = "LIU915807";//密码
        Connection con = DriverManager.getConnection(url, user, password);//创建连接池
        //con.setAutoCommit(false);
        return con;
    }
}
