package zhoumo.servlet;

import org.json.JSONObject;
import zhoumo.service.fl.EjflService;
import zhoumo.service.fl.FlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertEr")
public class EjflServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String erJiFenLeiMingCheng = req.getParameter("erJiFenLeiMingCheng");
        String yid = req.getParameter("yid");
        if (erJiFenLeiMingCheng == null || "".equals(erJiFenLeiMingCheng) || yid == null || "".equals(yid)) {
            resp.getWriter().println(new JSONObject("{code:500, msg:参数错误}"));
            return;
        }
        EjflService ejflService = new EjflService();
        JSONObject jsonObject = ejflService.insertEjfl(erJiFenLeiMingCheng, yid);
        resp.getWriter().println(jsonObject);
    }

}
