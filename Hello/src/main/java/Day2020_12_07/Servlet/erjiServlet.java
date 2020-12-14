package Day2020_12_07.Servlet;

import Day2020_12_07.Service.erjiService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/erjiServlet")
public class erjiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String  erjimingcheng= req.getParameter("erjimingcheng");
        String  yijiid= req.getParameter("yijiid");

        erjiService erjiservice   = new erjiService();
        JSONObject jsonObject= erjiservice.insert(erjimingcheng,yijiid);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);
    }
}
