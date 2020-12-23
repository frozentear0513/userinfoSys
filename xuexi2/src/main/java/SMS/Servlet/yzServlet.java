package SMS.Servlet;

import SMS.Service.Service;
import SMS.util.quyzm;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fasongyanzhengma")
public class yzServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String tel= req.getParameter("tel");
            Service service=  new Service();
           JSONObject jsonObject= service.insert(tel);
           resp.setContentType("text/html;charset=utf-8");
           resp.getWriter().println(jsonObject);
        }
    }

