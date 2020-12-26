package zuoye2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static Connection connection;

    private MyConnection() {
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/lianxi2";//数据库地址
            String user = "root";//使用人
            String password = "LIU915807";//密码
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
