package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class shiyonglistJDBC {
    public JSONObject list() {
        JSONObject object=null;
        try {
            Connection connection= MyConnection.getConnection();
            String sql="select   name,erjimingcheng,mingcheng,usershiyong.id from bangongyiji  join bangongerji  on " +
                    "bangongyiji.id=bangongerji.yijiid join usershiyong  on " +
                    "bangongerji.id=usershiyong.erjiid  join users on usershiyong.yonghuid=users.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            JSONArray array=new JSONArray();
            while(resultSet.next()){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",resultSet.getInt("usershiyong.id"));
                jsonObject.put("erjimingcheng",resultSet.getString("erjimingcheng"));
                jsonObject.put("name",resultSet.getString("name"));
                jsonObject.put("mingcheng",resultSet.getString("mingcheng"));
                array.put(jsonObject);
            }
            object=new JSONObject("{code:200,msg:查询成功}");
            object.put("usershiyong",array);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            object=new JSONObject("{code:500;,msg:查询失败}");
        }
        return  object;
    }
}





