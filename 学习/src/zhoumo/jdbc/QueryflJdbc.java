package zhoumo.jdbc;

import Day2020_12_03.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class QueryflJdbc {
    public ResultSet queryAllFlxx() {
    try {
        Connection connection = Myconnection.getConnection();
        String sql = "SELECT ejfl.mc as ejflmc, yjfl.mc as yjflmc from ejfl LEFT JOIN yjfl ON ejfl.id_yjfl = yjfl.id";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        return null;
    }
}
}
