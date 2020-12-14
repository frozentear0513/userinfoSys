package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class erjilistJDBC {
    public JSONObject list() {
        JSONObject object=null;
        try {
            Connection connection= MyConnection.getConnection();
            String sql="select * from bangongerji";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            JSONArray array=new JSONArray();
            while(resultSet.next()){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",resultSet.getInt("id"));
                jsonObject.put("erjimingcheng",resultSet.getString("erjimingcheng"));
                jsonObject.put("yijiid",resultSet.getString("yijiid"));
                array.put(jsonObject);
            }
            object=new JSONObject("{code:200,msg:查询成功}");
            object.put("erjiList",array);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            object=new JSONObject("{code:500;,msg:查询失败}");
        }
        return  object;
    }
}


