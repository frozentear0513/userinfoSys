package Day2020_12_03.servlet;

import Day2020_12_03.JDBC.denglu_jdbc;
import Day2020_12_03.JDBC.denglu_jdbc01;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/denglu2")
public class denglu_servlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=utf-8");
        JSONObject json = new JSONObject();
        if (username == null || password == null) {
            json = new JSONObject("{code:500, msg:'用户名和密码不能为空'}");
            resp.getWriter().println(json);
        } else {
            denglu_jdbc01 denglu = new denglu_jdbc01();
            if(denglu.denglu(username, password)){
                json = new JSONObject("{code:200, msg:'登录成功'}");
                denglu.clearDlcs(username);
            }else{
                //返回json {sfsd  sbcs}
                json = denglu.dlsb(username);
            }
            resp.getWriter().println(json);
        }
    }
}


