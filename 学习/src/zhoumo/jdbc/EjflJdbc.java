package zhoumo.jdbc;

import Day2020_12_03.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class EjflJdbc {
    public boolean insertEjfl(String erJiFenLeiMingCheng, String yid) {
        try {
            Connection connection = Myconnection.getConnection();
            String sql = "insert into ejfl (id, mc, id_yjfl) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UUID.randomUUID().toString().replaceAll("-",""));
            preparedStatement.setString(2, erJiFenLeiMingCheng);
            preparedStatement.setString(3, yid);
            int number = preparedStatement.executeUpdate();
            return number > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
