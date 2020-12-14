package Day2020_12_12.JDBC;

import Day2020_12_12.util.MyConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {


    public JSONObject zhuce(String username, String password) {
        JSONObject jsonObject=new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into user(username,password) values(?,?)";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            int result = prepareStatement.executeUpdate();
            if (result > 0) {
                jsonObject.put("code",200);
                jsonObject.put("msg","注册成功");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","注册失败");
        }
        return jsonObject;
    }

    public JSONObject denglu(String username, String password) {
            JSONObject jsonObject=new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select * from  user where username=? and password=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                jsonObject.put("code",200);
                jsonObject.put("msg","登录成功");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","登录失败");
        }
        return jsonObject;
    }

    public JSONObject chaxun() {
        JSONObject jsonObject=new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select id,username,password from  user";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            ResultSet resultSet = prepareStatement.executeQuery();
            JSONArray array=new JSONArray();
            JSONObject jsonObject1=null;
            while(resultSet.next()) {
                jsonObject1=new JSONObject();
                jsonObject1.put("id",resultSet.getInt("id"));
                jsonObject1.put("username",resultSet.getString("username"));
                jsonObject1.put("password",resultSet.getString("password"));
                array.put(jsonObject1);
            }
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
            jsonObject.put("userList",array);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","系统错误");
        }
        return jsonObject;
    }

    public JSONObject chaxun2(String id, String username) {

        JSONObject jsonObject=new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select id,username,password from  user where id like ? and username like ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1,"%"+id+"%");
            prepareStatement.setString(2,"%"+username+"%");
            ResultSet resultSet = prepareStatement.executeQuery();
            JSONArray array=new JSONArray();
            JSONObject jsonObject1=null;
            while(resultSet.next()) {
                jsonObject1=new JSONObject();
                jsonObject1.put("id",resultSet.getInt("id"));
                jsonObject1.put("username",resultSet.getString("username"));
                jsonObject1.put("password",resultSet.getString("password"));
                array.put(jsonObject1);
            }
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
            jsonObject.put("userList",array);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","系统错误");
        }
        return jsonObject;


    }

    public JSONObject shanchu(String id) {
        JSONObject jsonObject=new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "delete from user where id=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1,id);
            int result= prepareStatement.executeUpdate();
            if(result>0){
            jsonObject.put("code",200);
            jsonObject.put("msg","删除成功");
        }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","系统错误");
        }
        return jsonObject;
    }

    public JSONObject update(String id, String username, String password) {

        JSONObject jsonObject=new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "update user set username=?,password=? where id=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1,username);
            prepareStatement.setString(2,password);
            prepareStatement.setString(3,id);
            int result= prepareStatement.executeUpdate();
            if(result>0){
                jsonObject.put("code",200);
                jsonObject.put("msg","修改成功");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","系统错误");
        }
        return jsonObject;
    }


    }


