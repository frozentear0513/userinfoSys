package Day2020_12_07.Servlet;

import Day2020_12_07.Service.erjiService;
import Day2020_12_07.Service.shiyongService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shiyong")
public class shiyongServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String yonghuid = req.getParameter("yonghuid");
        String erjiid = req.getParameter("erjiid");

        shiyongService shiyong1   = new shiyongService();
        JSONObject jsonObject= shiyong1.insert(id,yonghuid,erjiid);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);


    }
}
