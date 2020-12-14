package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bgyjflsingleJDBC {

    public JSONObject Single(String id) {
        JSONObject object=null;
        try {
            Connection connection= MyConnection.getConnection();
            String sql="select * from bangongyiji where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
           if(resultSet.next()){
               object=new JSONObject("{code:200,msg:查询成功}")  ;
               object.put("id",resultSet.getInt("id"));
               object.put("mingcheng",resultSet.getString("mingcheng"));
           }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            object=new JSONObject("{code:500;,msg:查询失败}");
        }
        return  object;
    }
}




