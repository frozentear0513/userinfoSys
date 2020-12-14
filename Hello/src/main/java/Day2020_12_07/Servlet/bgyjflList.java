package Day2020_12_07.Servlet;

import Day2020_12_07.Service.bgyjflListService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bgyjfllist")
public class bgyjflList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bgyjflListService serice  =  new bgyjflListService();
       JSONObject jsonObject= serice.list();
       resp.setContentType("text/html;charset=utf-8");
       resp.getWriter().println(jsonObject);
    }
}
