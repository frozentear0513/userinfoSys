package zuoye2.JDBC;

import org.json.JSONObject;
import zuoye.util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
    public JSONObject delete(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "delete  from  yh where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                jsonObject.put("code", 200);
                jsonObject.put("msg", "删除成功");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "删除失败");
        }
        return jsonObject;
    }
    public JSONObject insert(String xingming, String xingbie, String tel, String js, String bmid, String dwid, String cjsj) {

        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into yh(xingming,xingbie,tel,js,bmid,dwid,cjsj) values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, xingming);
            preparedStatement.setString(2, xingbie);
            preparedStatement.setString(3, tel);
            preparedStatement.setString(4, js);
            preparedStatement.setString(5, bmid);
            preparedStatement.setString(6, dwid);
            preparedStatement.setString(7, cjsj);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                jsonObject.put("code", 200);
                jsonObject.put("msg", "新增成功");
                return jsonObject;
            }
            jsonObject.put("code", 500);
            jsonObject.put("msg", "新增失败");
            return jsonObject;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "系统错误");
            return jsonObject;
        }
    }
    public JSONObject update(String id, String xingming, String xingbie,  String tel, String js, String bmid, String dwid,String cjsj) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "update yh set xingming=?,xingbie=?,tel=?,js=?,bmid=?,dwid=?,cjsj=?, where id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, xingming);
            preparedStatement.setString(2, xingbie);
            preparedStatement.setString(3, tel);
            preparedStatement.setString(4, js);
            preparedStatement.setString(5, bmid);
            preparedStatement.setString(6, dwid);
            preparedStatement.setString(7, cjsj);

            preparedStatement.setString(8, id);
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                jsonObject.put("code", 200);
                jsonObject.put("msg", "新增成功");
                return jsonObject;
            }
            jsonObject.put("code", 500);
            jsonObject.put("msg", "新增失败");
            return jsonObject;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "系统错误");
            return jsonObject;
        }
    }
}
