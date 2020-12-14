package zhoumo.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import zhoumo.service.fl.QueryFlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectErJiFenLei")
public class QueryFlServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String eid = req.getParameter("eid");
        if (eid == null || "".equals(eid)) {
            resp.getWriter().println(new JSONObject("{code:500, msg:参数错误}"));
            return;
        }
        QueryFlService queryFlService = new QueryFlService();
        JSONObject flxx = queryFlService.queryEjFlxx(eid);
        resp.getWriter().println(flxx);
    }

}
