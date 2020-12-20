package Day2020_12_12.Servlet;

import Day2020_12_12.Service.Service;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class updateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String id = req.getParameter("id");
         Service updateservice = new Service();
        JSONObject jsonObject=updateservice.update(id,username,password);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);
    }
}
