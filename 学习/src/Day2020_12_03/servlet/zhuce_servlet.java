package Day2020_12_03.servlet;

import Day2020_12_03.JDBC.zhuce_jdbc;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/zhuce1")
public class zhuce_servlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String se= req.getParameter("name");
       String sr= req.getParameter("password");
       resp.setContentType("text/html;charset=utf-8");
       JSONObject json = new JSONObject();
        if (se == null || se.isEmpty() || sr == null || sr.isEmpty()) {
            json.put("code",500);
            json.put("msg", "用户名或密码为空");
            resp.getWriter().println(json);
        } else if (se.length() > 16 || sr.length() > 16) {
            json.put("code",500);
            json.put("msg", "用户名或者密码过长");
          resp.getWriter().println(json); }
       else if(zhuce_jdbc.panduan(se)){
            json.put("code",500);
            json.put("msg", "用户名已注册");
         resp.getWriter().println(json);
        }
       else {
            zhuce_jdbc zc=new zhuce_jdbc( );
              json=  zc.zhuce(se,sr);
              resp.getWriter().println(json);
          }


    }

}
