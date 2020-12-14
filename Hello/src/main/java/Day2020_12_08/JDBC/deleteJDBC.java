package Day2020_12_08.JDBC;

import Day2020_12_08.util.MyConnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteJDBC {
    public JSONObject delete(String yijiid) {
        JSONObject jsonObject = null;
        try {

            Connection connection = MyConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "select id  from  bangongerji where yijiid=?";
            String sql2 = "delete  from  usershiyong where erjiid=?";
            String sql3 = "delete  from  bangongerji where yijiid=?";
            String sql4 = "delete  from  bangongyiji where id=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, yijiid);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                prepareStatement = connection.prepareStatement(sql2);
                prepareStatement.setInt(1, id);
                prepareStatement.executeUpdate();
            }
              prepareStatement = connection.prepareStatement(sql3);
             prepareStatement.setString(1, yijiid);
            prepareStatement.executeUpdate();

          prepareStatement = connection.prepareStatement(sql4);
            prepareStatement.setString(1, yijiid);
            prepareStatement.executeUpdate();
            connection.commit();
            jsonObject = new JSONObject("{code:200,msg:删除成功}");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject = new JSONObject("{code:500,msg:系统内部错误}");
        }
        return jsonObject;
    }
}




