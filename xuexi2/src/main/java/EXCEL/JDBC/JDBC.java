package EXCEL.JDBC;


import zuoye.util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBC {

    public ResultSet excelList() {
        ResultSet resultSet = null;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select user.id,user.qxid,bmid,username,password,sex,age,zhuceshijian,denglushijian,` bmmc` as bmmc,qx.qxmc from user,bm,qx where bmid=bm.id and qxid=qx.id order by user.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return resultSet;
    }

    public void daoruList(List<String> list) {
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into cs(username,password,sex,age) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
           for(int i=0;i<list.size();i++){
            preparedStatement.setString(1,list.get(0));
            preparedStatement.setString(2,list.get(1));
            preparedStatement.setString(3,list.get(2));
            preparedStatement.setString(4,list.get(3));
           }
           preparedStatement.executeUpdate();
            System.out.println(list);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}





