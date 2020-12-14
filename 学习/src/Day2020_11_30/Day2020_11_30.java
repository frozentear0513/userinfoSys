package Day2020_11_30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Day2020_11_30 {
    public static void main(String[] args)throws  Exception {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true";
            String user = "root";
            String password = "LIU915807";
            long startime =System.currentTimeMillis();
           Connection conn=DriverManager.getConnection(url,user,password);
           conn.setAutoCommit(false);
            Statement st=conn.createStatement();
            for(int x=0; x<=10000;x++) {
                st.addBatch("insert into users(user,mima) values('" + x + "','" + x + "')");
            }
            st.executeBatch();
            conn.commit();
        System.out.println(System.currentTimeMillis() - startime);
        }














}
