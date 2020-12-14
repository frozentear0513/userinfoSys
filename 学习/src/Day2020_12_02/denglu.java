package Day2020_12_02;

import com.mysql.jdbc.ResultSetRow;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/denglu")
public class denglu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        resp.setContentType("text/html;charset=utf-8");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            resp.getWriter().println("驱动异常");
        }
        String url = "jdbc:mysql://localhost:3306/test";//数据库地址
        String user = "root";//使用人
        String password = "LIU915807";//密码
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            resp.getWriter().println("连接数据库失败");
        }
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            resp.getWriter().println("获取执行集失败");
        }
        String se = req.getParameter("username");
        String sr = req.getParameter("password");
        resp.getWriter().println("输入的username:" + se + "输入的password：" + sr);
        System.out.println(se +
                sr);
        try {
            ResultSet resultSet = st.executeQuery("select * from denglu where name='" + se + "' and pass='" + sr + "'");
//                    if (!resultSet.next()) {
//                       resp.getWriter().println("该用户未注册");
//                    }
                   if (resultSet.next()) {
                        JSONObject ja = new JSONObject();
                        ja.put("code", 200);
                        ja.put("msg", "登录成功");
                        resp.getWriter().println(ja);
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
