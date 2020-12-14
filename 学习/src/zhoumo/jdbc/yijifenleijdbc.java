package zhoumo.jdbc;


import zhoumo.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class yijifenleijdbc {


    public boolean insertjdbc(String yijifenleimingcheng) {
        try {
            Connection connection= Myconnection.getConnection();
               String sql ="insert into yijifenleibiao(yijifenleimingcheng) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,yijifenleimingcheng);
             int  result= preparedStatement.executeUpdate();
            return result>0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
