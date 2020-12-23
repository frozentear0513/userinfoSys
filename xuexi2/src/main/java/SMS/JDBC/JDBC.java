package SMS.JDBC;

import org.json.JSONObject;
import zuoye.util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public int insert(String tel, int mobile_code, Long times) {
        try {
            Connection connection = MyConnection.getConnection();
            String sql = " insert into users( tel, shijian, yzm)  values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tel);
            preparedStatement.setLong(2, times);
            preparedStatement.setInt(3, mobile_code);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean yanzheng(String tel, int mobile_code) {

        try {
            Connection connection = MyConnection.getConnection();

            String sql = " select * from users where tel=? and yzm=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tel);
            preparedStatement.setInt(2, mobile_code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                    return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;

    }






    public boolean shijian(String tel, int mobile_code) {

        try {
            Connection connection = MyConnection.getConnection();
            Long time = System.currentTimeMillis();
            String sql = " select shijian from users where tel=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tel);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long start_time = resultSet.getLong("shijian");
                System.out.println(start_time);
                if ((time - start_time)/1000/60 < 5)
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } return false;
    }

    public int update(String tel) {

        try {
            Connection connection = MyConnection.getConnection();
            String sql = "update users set yzm=0 where tel=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tel);
            int resultSet = preparedStatement.executeUpdate();
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}