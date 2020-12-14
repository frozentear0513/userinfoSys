package zhoumo.jdbc;

import Day2020_12_03.util.Myconnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class FlJdbc {
    public boolean insertYjfl(String yiJiFenLeiMingCheng) {
        try {
            Connection connection = Myconnection.getConnection();
            String sql = "insert into yjfl (id, mc) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UUID.randomUUID().toString().replaceAll("-",""));
            preparedStatement.setString(2, yiJiFenLeiMingCheng);
            int number = preparedStatement.executeUpdate();
            return number > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
