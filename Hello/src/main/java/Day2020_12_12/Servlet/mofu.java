package Day2020_12_12.Servlet;

import Day2020_12_12.Service.Service;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chaxun2")
public class mofu extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        Service service = new Service();
           JSONObject jsonObject= service.chaxun2(id,username,req);
           resp.setContentType("text/html;charset=utf-8");
           resp.getWriter().println(jsonObject);
    }
}
