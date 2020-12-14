package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class shiyongJDBC {

    public boolean insert(String id, String yonghuid, String erjiid) {
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into usershiyong(id,yonghuid,erjiid) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, yonghuid);
            preparedStatement.setString(3, erjiid);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
