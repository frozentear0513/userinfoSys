package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class erjiJDBC {

    public JSONObject insert(String erjimingcheng, String yijiid) {
        JSONObject jsonObject = null;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into bangongerji(erjimingcheng,yijiid) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, erjimingcheng);
            preparedStatement.setString(2, yijiid);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                jsonObject = new JSONObject("{code:200,msg:插入成功}");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject = new JSONObject("{code:200,msg:系统内部错误}");
        }

        return jsonObject;
    }
}