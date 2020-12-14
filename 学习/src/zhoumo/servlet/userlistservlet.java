package zhoumo.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import zhoumo.service.bumen.userlistservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userlist")
public class userlistservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userlistservice listservice  = new userlistservice();
         JSONObject jsonObject= listservice.chaxun();
           resp.setContentType("text/html;charset=utf-8");
           resp.getWriter().println(jsonObject);
    }
}
