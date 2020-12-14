package Day2020_12_04.servlet;

import Day2020_12_04.jdbc.denglu_jdbc;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/denglu10")
public class denglu_servlet extends HttpServlet {
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
            denglu_jdbc denglu = new denglu_jdbc();
            json = denglu.denglu(username, password);
            if(json.getInt("code")==200){
               req.getSession().setAttribute("username",0);
            }
            resp.getWriter().println(json);

        }
    }
}


