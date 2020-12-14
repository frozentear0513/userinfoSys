package zhoumo.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import zhoumo.service.fl.EjflService;
import zhoumo.service.fl.QueryFlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectFenLei")
public class QueryAllflServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        QueryFlService queryFlService = new QueryFlService();
        JSONArray flxxs = queryFlService.queryAllFlxx();
        resp.getWriter().println(flxxs);
    }

}
