package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bgyjflJDBC {

    public boolean insert(String mingcheng) {
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into bangongyiji(mingcheng) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mingcheng);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}