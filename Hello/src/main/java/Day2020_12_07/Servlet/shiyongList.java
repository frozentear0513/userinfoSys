package Day2020_12_07.Servlet;

import Day2020_12_07.Service.erjilistSerice;
import Day2020_12_07.Service.shiyong1Service;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shiyongList")
public class shiyongList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       shiyong1Service shiyong1=new shiyong1Service();
        JSONObject jsonObject=shiyong1.list();
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);
    }
}
