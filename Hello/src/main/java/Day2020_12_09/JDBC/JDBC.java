package Day2020_12_09.JDBC;

import Day2020_12_09.util.MyConnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {

    public JSONObject zhuce(String username, String password) {
        JSONObject jsonObject = null;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into user(username,password) values(?,?)";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            int result = prepareStatement.executeUpdate();
            if (result > 0) {
                jsonObject = new JSONObject("{code:200,msg:注册成功}");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject = new JSONObject("{code:500,msg:注册失败}");
        }
        return jsonObject;
    }

    public JSONObject denglu(String username, String password) {
        JSONObject jsonObject = null;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select * from  user where username=? and password=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                jsonObject = new JSONObject("{code:200,msg:登录成功}");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject = new JSONObject("{code:500,msg:登录失败}");
        }
        return jsonObject;
    }
}