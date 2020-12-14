package zhoumo.jdbc;

import Day2020_12_03.util.Myconnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class user_jdbc01 {
    public user_jdbc01(String name, String bumenid) {
    }

    public user_jdbc01() {

    }

    public JSONObject zhixing(String name, String bumenmingcheng) {
        JSONObject jsonObject = null;
        try {
            Connection connection = Myconnection.getConnection();
            String sql = "update bm set bumenmingcheng=? where id=(select bumenid from user where name=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bumenmingcheng);
            preparedStatement.setString(2, name);
            int number = preparedStatement.executeUpdate();
            if(number > 0){
                jsonObject = new JSONObject("{code:200,msg:'修改成功'}");
            }else {
                jsonObject = new JSONObject("{code:500,msg:'当前用户不存在'}");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject = new JSONObject("{code:500,msg:'系统内部错误'}");
        }
        return jsonObject;
    }
}
