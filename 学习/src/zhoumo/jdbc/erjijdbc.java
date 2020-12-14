package zhoumo.jdbc;



import zhoumo.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class erjijdbc {
    public boolean insert(String erjifenleimingcheng, String yijifenleiid) {
        try {
            Connection connection = Myconnection.getConnection();
            String sql = "insert  into erjifenlei(erjifenleimingcheng,yijifenleiid) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, erjifenleimingcheng);
            preparedStatement.setString(2, yijifenleiid);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }
}
