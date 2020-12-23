package zuoye.JDBC;

import com.mysql.jdbc.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import zuoye.util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public int selectCount(String username, String bmid, String zckssj, String zcjssj, String qxid) {
        try {
            Connection connection = MyConnection.getConnection();
            String sql = " select count(user.id) from user, qx, bm where user.qxid = qx.id and bm.id = user.bmid";
            List<String> countList = new ArrayList<>();
            sql = setWhereSql(sql, countList, username, bmid, zckssj, zcjssj, qxid);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < countList.size(); i++) {
                preparedStatement.setString(i + 1, countList.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count(user.id)");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public String setWhereSql(String sql, List<String> countList, String username, String bmid, String zckssj, String zcjssj, String qxid) {

        if (!StringUtils.isNullOrEmpty(username)) {
            sql = sql + " and user.username like ?";
            countList.add("%" + username + "%");
        }
        if (!StringUtils.isNullOrEmpty(bmid)) {
            sql = sql + " and bm.id = ?";
            countList.add(bmid);
        }
        if (!StringUtils.isNullOrEmpty(qxid)) {
            sql = sql + " and qx.id = ?";
            countList.add(qxid);
        }
        if (!StringUtils.isNullOrEmpty(zckssj) && !StringUtils.isNullOrEmpty(zcjssj)) {
            sql = sql + " and user.zhuceshijian between ? and ?";
            countList.add(zckssj);
            countList.add(zcjssj);
        }
        return sql;
    }


    public JSONArray pageList(int index, String username, String bmid, String zckssj, String zcjssj, String qxid) {
        JSONArray array = new JSONArray();

        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select user.id,user.qxid,bmid,username,password,sex,age,zhuceshijian,denglushijian,` bmmc` as bmmc,qx.qxmc from user,bm,qx where bmid=bm.id and qxid=qx.id";
            List<String> countList = new ArrayList<>();
            sql = setWhereSql(sql, countList, username, bmid, zckssj, zcjssj, qxid);
            sql = sql + " order by user.id limit ?,10";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < countList.size(); i++) {
                preparedStatement.setString(i + 1, countList.get(i));
            }
            preparedStatement.setInt(countList.size() + 1, index);
            ResultSet resultSet = preparedStatement.executeQuery();

            JSONObject jsonObject1 = null;
            while (resultSet.next()) {
                jsonObject1 = new JSONObject();
                jsonObject1.put("id", resultSet.getString("id"));
                jsonObject1.put("username", resultSet.getString("username"));
                jsonObject1.put("password", resultSet.getString("password"));
                jsonObject1.put("sex", resultSet.getString("sex"));
                jsonObject1.put("age", resultSet.getString("age"));
                jsonObject1.put("zhuceshijian", resultSet.getString("zhuceshijian"));
                jsonObject1.put("denglushijian", resultSet.getString("denglushijian"));
                jsonObject1.put("bmmc", resultSet.getString("bmmc"));
                jsonObject1.put("qxmc", resultSet.getString("qx.qxmc"));
                jsonObject1.put("bmid", resultSet.getString("bmid"));
                jsonObject1.put("qxid", resultSet.getString("user.qxid"));
                array.put(jsonObject1);
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return array;
    }

    public JSONObject delete(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "delete  from  user where id=?";
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

    public JSONObject insert(String username, String password, String sex, String age, String bmmc, String qxmc, String zhuceshijian, String denglushijian) {

        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "insert into user(username,password,sex,age,bmid,qxid,zhuceshijian,denglushijian) values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, sex);
            preparedStatement.setString(4, age);
            preparedStatement.setString(5, bmmc);
            preparedStatement.setString(6, qxmc);
            preparedStatement.setString(7, zhuceshijian);
            preparedStatement.setString(8, denglushijian);
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

    public JSONObject update(String id, String username, String password, String sex, String age, String bmmc, String qxmc, String zhuceshijian, String denglushijian) {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "update user set username=?,password=?,sex=?,age=?,zhuceshijian=?,denglushijian=?,bmid=?,qxid=? where id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, sex);
            preparedStatement.setString(4, age);
            preparedStatement.setString(5, zhuceshijian);
            preparedStatement.setString(6, denglushijian);
            preparedStatement.setString(7, bmmc);
            preparedStatement.setString(8, qxmc);
            preparedStatement.setString(9, id);
            int result = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(sql);
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

    public JSONObject bmList() {

        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select  id,` bmmc`as bmmc from  bm ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            JSONObject jsonObject1 = null;
            JSONArray array = new JSONArray();
            while (resultSet.next()) {
                jsonObject1 = new JSONObject();
                jsonObject1.put("id", resultSet.getInt("id"));
                jsonObject1.put("bmmc", resultSet.getString("bmmc"));
                array.put(jsonObject1);
            }
            jsonObject.put("code", 200);
            jsonObject.put("msg", "查询成功");
            jsonObject.put("data", array);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "查询失败");
        }
        return jsonObject;
    }

    public JSONObject qxList() {
        JSONObject jsonObject = new JSONObject();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "select  id,qxmc from  qx ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            JSONObject jsonObject1 = null;
            JSONArray array = new JSONArray();
            while (resultSet.next()) {
                jsonObject1 = new JSONObject();
                jsonObject1.put("id", resultSet.getInt("id"));
                jsonObject1.put("qxmc", resultSet.getString("qxmc"));
                array.put(jsonObject1);
            }
            jsonObject.put("code", 200);
            jsonObject.put("msg", "查询成功");
            jsonObject.put("data", array);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "查询失败");
        }
        return jsonObject;
    }

//    public static void main(String[] args) {
//       JDBC jdbc=   new JDBC();
//       JSONObject jsonObject=jdbc.bmList();
//
//    }

//    public JSONArray pageList(int index, String username, String bmid, String zckssj, String zcjssj, String qxid) {
//        JSONArray array = new JSONArray();
//
//        try {
//            Connection connection = MyConnection.getConnection();
//            String sql = "select user.id,user.qxid,bmid,username,password,sex,age,zhuceshijian,denglushijian,` bmmc` as bmmc,qx.qxmc from user,bm,qx where bmid=bm.id and qxid=qx.id";
//            if (username != null && !"".equals(username)) {
//                sql = sql + " and username like' %" + username + "%'";
//            }
//            if (bmid != null && !"".equals(bmid)) {
//                sql = sql + " and bmid= " + bmid;
//            }
//            if (bmid != null && !"".equals(bmid)) {
//                sql = sql + " and qxid= " + qxid;
//            }
//            if (zckssj != null && !"".equals(zckssj) && zcjssj != null && !"".equals(zcjssj)) {
//                sql = sql + " and zhuceshijian between '" + zckssj + "' and '" + zcjssj + "'";
//            }
//            sql = sql + " order by user.id limit ?,10";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, index);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            JSONObject jsonObject1 = null;
//            while (resultSet.next()) {
//                jsonObject1 = new JSONObject();
//                jsonObject1.put("id", resultSet.getString("id"));
//                jsonObject1.put("username", resultSet.getString("username"));
//                jsonObject1.put("password", resultSet.getString("password"));
//                jsonObject1.put("sex", resultSet.getString("sex"));
//                jsonObject1.put("age", resultSet.getString("age"));
//                jsonObject1.put("zhuceshijian", resultSet.getString("zhuceshijian"));
//                jsonObject1.put("denglushijian", resultSet.getString("denglushijian"));
//                jsonObject1.put("bmmc", resultSet.getString("bmmc"));
//                jsonObject1.put("qxmc", resultSet.getString("qx.qxmc"));
//                jsonObject1.put("bmid", resultSet.getString("bmid"));
//                jsonObject1.put("qxid", resultSet.getString("user.qxid"));
//                array.put(jsonObject1);
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//
//        return array;
//    }
//

}



