package zhoumo.jdbc;

import Day2020_12_03.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userlistjdbc {
    public ResultSet chaxun() {
        try {
            Connection connection = Myconnection.getConnection();
            String sql = "select name,bumenmingcheng,password from bm left join user on bm.id=user.bumenid";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

