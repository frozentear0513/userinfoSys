package Day2020_12_04.jdbc;

import Day2020_12_03.util.Myconnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_jdbc {
    public JSONObject userlist(){
        JSONObject object=new JSONObject();
        try {
            Connection connection= Myconnection.getConnection();
            String sql ="select * from xingming ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            JSONArray array=new JSONArray();
            while (resultSet.next()){
                JSONObject jn=new JSONObject();
                jn.put("name",resultSet.getInt("id"));
                jn.put("password",resultSet.getString("password"));
                array.put(jn);
            }
            object=new JSONObject("{code:200,msg:'查询成功'}");
            object.put("userlist",array);
                return object;
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
            object=new JSONObject("{code：500,msg:'系统内部错误'}");
        }

        return object;
    }
}
