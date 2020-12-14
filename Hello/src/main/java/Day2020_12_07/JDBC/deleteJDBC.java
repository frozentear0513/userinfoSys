package Day2020_12_07.JDBC;

import Day2020_12_07.util.MyConnection;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteJDBC {
    public boolean delete(String mingcheng) {

        JSONObject object=null;
        try {
            Connection connection= MyConnection.getConnection();
            String sql="delete from usershiyong where mingcheng=?  ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,mingcheng);
           int result= preparedStatement.executeUpdate();
           return result>0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}



