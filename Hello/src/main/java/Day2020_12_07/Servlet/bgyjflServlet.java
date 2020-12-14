package Day2020_12_07.Servlet;

import Day2020_12_07.Service.bgyjflService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/bgyjfl")
public class bgyjflServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String mingcheng = req.getParameter("mingcheng");
            resp.setContentType("text/html;charset=utf-8");
            if(mingcheng==null){
                resp.getWriter().println(new JSONObject("{code:500,msg:数据错误}"));
                return;
            }
            bgyjflService bgyjflservice = new bgyjflService();
            JSONObject jsonObject= bgyjflservice.insert(mingcheng);
            resp.getWriter().println(jsonObject);

        }
    }


