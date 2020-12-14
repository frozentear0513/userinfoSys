package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class erjisingleJDBC {
    public JSONObject Single(String yijiid) {
        JSONObject jsonObject=null;
        try {
            Connection connection= MyConnection.getConnection();
            String sql="select * from bangongerji left join bangongyiji on bangongerji.yijiid=bangongyiji.id where yijiid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,yijiid);
            ResultSet resultSet= preparedStatement.executeQuery();
            JSONArray array =new JSONArray();
           while(resultSet.next()){
               JSONObject  object=new JSONObject();
                object.put("id",resultSet.getInt("id"));
                object.put("erjimingcheng",resultSet.getString("erjimingcheng"));
                object.put("mingcheng",resultSet.getString("mingcheng"));
                array.put(object);
            }
            jsonObject=new JSONObject("{code:200,msg:查询成功}");
            jsonObject.put ("erjisingle",array);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject=new JSONObject("{code:500;,msg:查询失败}");
        }
        return  jsonObject;
    }
}


