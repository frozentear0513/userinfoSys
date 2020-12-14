package Day2020_12_03.JDBC;

import Day2020_12_03.util.Myconnection;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class denglu_jdbc {
    public JSONObject denglu(String username, String password) {
        JSONObject jsonObject = null;
        try {
            Connection  connection = Myconnection.getConnection();
            String sql = "select * from xingming where name=? and  password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jsonObject = new JSONObject("{code:200, msg:'登录成功'}");
            } else {
                jsonObject = new JSONObject("{code:500, msg:'登录失败，用户名或者密码错误'}");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
