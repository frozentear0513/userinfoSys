package SMS.Servlet;

import SMS.Service.Service;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dengluyanzheng")
public class dlServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tel = req.getParameter("tel");
        String mobile_code1 = req.getParameter("mobile_code");
         int mobile_code=Integer.parseInt(mobile_code1);
        Service service = new Service();
        JSONObject jsonObject = service.yanzheng(tel, mobile_code);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);
    }
    }


