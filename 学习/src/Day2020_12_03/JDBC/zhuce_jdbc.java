package Day2020_12_03.JDBC;

import Day2020_12_03.util.Myconnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class zhuce_jdbc {
    public JSONObject zhuce(String username, String password){
        JSONObject jsonObject=null;
        Connection connection=null;
        try {
        connection  = Myconnection.getConnection();
        String sql="insert into xingming(name,password) values(?,?)";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1,username);
        pre.setString(2,password);
        pre.executeUpdate();
        jsonObject = new JSONObject("{code:200,msg:'注册成功'}");

        } catch (ClassNotFoundException |SQLException e) {
        jsonObject = new JSONObject("{code:500,msg:'注册失败'}");

        }
        return  jsonObject;
        }
    public static boolean panduan(String username) {

        Connection connection=null;
        try {
            connection = Myconnection.getConnection();
            String sql = "select * from xingming where name=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
//
//    public static void main(String[] args) {
//        zhuce_jdbc a=new zhuce_jdbc();
//      JSONObject S=  a.zhuce("李","123456");
//        System.out.println(S);
//    }






