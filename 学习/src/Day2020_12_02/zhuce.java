package Day2020_12_02;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/zhuce")
public class zhuce extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            response.getWriter().println("获取驱动名失败");
        }
        String url = "jdbc:mysql://localhost:3306/test";//数据库地址
        String user = "root";//使用人
        String password = "LIU915807";//密码
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);//创建连接池
        } catch (Exception e) {
            response.getWriter().println("获取数据库连接失败");
        }

        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            response.getWriter().println("创建数据库执行集失败");
        }

        String se = request.getParameter("username");
        String sr = request.getParameter("password");
        response.getWriter().println("获取到用户名为：" + se + " 密码为：" + sr);
        try {
            ResultSet resultSet = st.executeQuery("select * from denglu where name='" + se + "'");
            if (se == null || se.isEmpty() || sr == null || sr.isEmpty()) {
                JSONObject json = new JSONObject();
                json.put("code",500);
                json.put("msg", "用户名或密码为空");
                response.getWriter().println(json);
            } else if (se.length() > 10 || sr.length() > 10) {
                JSONObject json = new JSONObject();
                json.put("code",500);
                json.put("msg", "用户名或者密码过长");
                response.getWriter().println(json);
            } else if (resultSet.next()) {
                JSONObject json = new JSONObject();
                json.put("code",500);
                json.put("msg", "用户名已注册");
                response.getWriter().println(json);
            } else {
                st.execute("insert into denglu(name,pass) values('" + se + "','" + sr + "')");
//                response.getWriter().println("注册成功");
                response.setContentType("text/json;charset=UTF-8");
                JSONObject json = new JSONObject();
                json.put("code", 200);
                json.put("msg", "注册成功");
                response.getWriter().println(json);
            }
       } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


