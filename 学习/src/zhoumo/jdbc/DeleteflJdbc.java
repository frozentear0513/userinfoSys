package zhoumo.jdbc;

import Day2020_12_03.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteflJdbc {
    public boolean deleteFlxx(String yid) {
        try {
            Connection connection = Myconnection.getConnection();
            String sql = "DELETE from yjfl WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, yid);
            int yjfljg = preparedStatement.executeUpdate();
            sql = "DELETE from ejfl WHERE id_yjfl=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, yid);
            int ejfljg = preparedStatement.executeUpdate();
            return ejfljg+yjfljg > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
