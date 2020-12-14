package zhoumo.servlet;

import org.json.JSONObject;
import zhoumo.service.fl.FlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertYi")
public class flServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String yiJiFenLeiMingCheng = req.getParameter("yiJiFenLeiMingCheng");
        if (yiJiFenLeiMingCheng == null || "".equals(yiJiFenLeiMingCheng)) {
            resp.getWriter().println(new JSONObject("{code:500, msg:参数错误}"));
            return;
        }
        FlService flService = new FlService();
        JSONObject jsonObject = flService.insertYjfl(yiJiFenLeiMingCheng);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);
    }

}
