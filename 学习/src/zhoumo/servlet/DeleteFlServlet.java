package zhoumo.servlet;

import org.json.JSONObject;
import zhoumo.service.fl.DeleteFlService;
import zhoumo.service.fl.QueryFlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteByYiJiFenLei")
public class DeleteFlServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String yid = req.getParameter("yid");
        if (yid == null || "".equals(yid)) {
            resp.getWriter().println(new JSONObject("{code:500, msg:参数错误}"));
            return;
        }
        DeleteFlService deleteFlService = new DeleteFlService();
        JSONObject flxx = deleteFlService.deleteFlxx(yid);
        resp.getWriter().println(flxx);
    }

}
