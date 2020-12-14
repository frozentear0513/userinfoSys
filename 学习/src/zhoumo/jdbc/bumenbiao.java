package zhoumo.jdbc;

import org.json.JSONObject;
import zhoumo.util.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bumenbiao {
    public  JSONObject  zhixing(String bumenmingcheng){
        JSONObject jsonObject=null;
        try {
            Connection connection = Myconnection.getConnection();
            String sql="insert into bm(bumenmingcheng) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bumenmingcheng);
            preparedStatement.executeUpdate();
           jsonObject=new JSONObject("{code:200,msg:'添加成功'}");
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
           jsonObject=new JSONObject("{code:500,msg:'系统内部错误'}");
        }
        return jsonObject;
    }


//    public static void main(String[] args) {
//        bumenbiao a=new bumenbiao();
//        a.zhixing("后勤部");

//    }
}
