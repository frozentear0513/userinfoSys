package Day2020_12_07.Servlet;

import Day2020_12_07.Service.bgyjflsingleService;
import Day2020_12_07.Service.erjisingleService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/erjisingle")
public class erjiSingle extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String yijiid=  req.getParameter("yijiid");
            erjisingleService service = new erjisingleService();
            JSONObject jsonObject= service.Single(yijiid);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(jsonObject);
        }
    }

