package Day2020_12_03.JDBC;

import Day2020_12_03.util.Myconnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class denglu_jdbc01 {
    public boolean denglu(String username, String password) {

        try {
            Connection connection = Myconnection.getConnection();
            String sql = "select * from xingming where name=? and  password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

    public JSONObject dlsb(String username) {
        //1.查询当前登录人的信息
        //2.判断登录状态  锁定--返回
        //3.非锁定的状态  根据错误次数判断是增加错误次数还是修改锁定状态

        JSONObject result = new JSONObject();
        result.put("code", 500);
        try {
            Connection  connection = Myconnection.getConnection();
            String sql = "select * from xingming where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer sbzt = resultSet.getInt("sbzt");
                String sqldlsb = "update xingming set sbcs = ?, sbzt=? where name = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sqldlsb);
                if (sbzt == null || sbzt != 1) {
                    //正常
                    Integer sbcs = resultSet.getInt("sbcs");
                    if (sbcs == null) {
                        sbcs = 0;
                    }
                    if (sbcs < 2) {//正常
                        sbcs++;
                        preparedStatement1.setInt(1, sbcs);
                        preparedStatement1.setInt(2, 2);
                        preparedStatement1.setString(3, username);
                        preparedStatement1.executeUpdate();
                        String resultMsg = "用户名或密码错误第" + sbcs + "次错误";
                        result.put("msg", resultMsg);
                        return result;
                    }//锁定
                    preparedStatement1.setInt(1, 0);
                    preparedStatement1.setInt(2, 1);
                    preparedStatement1.setString(3, username);
                    preparedStatement1.executeUpdate();
                    String resultMsg = "用户名或密码错误，用户账户已锁定，请联系管理员解锁";
                    result.put("msg", resultMsg);
                    return result;
                }
                //本身已锁定
                return result.put("msg", "用户账户已锁定，请联系管理员解锁");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return result.put("msg", "系统内部错误");
        }
        return result.put("msg", "当前用户不存在");
    }

    public void clearDlcs(String username) {

        try {
            Connection  connection = Myconnection.getConnection();
            String sqldlsb = "update xingming set sbcs = ? where name = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqldlsb);
            preparedStatement1.setInt(1, 0);
            preparedStatement1.setString(2, username);
            preparedStatement1.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
