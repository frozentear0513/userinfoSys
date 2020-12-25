package SMS.Servlet;

import SMS.Service.Service;
import SMS.util.MyJavaMail;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/youxiangyanzheng")
public class faServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String email = req.getParameter("yx");
     Service service= new Service();
    JSONObject jsonObject=service.send(username,email);

    resp.setContentType("text/html;charset=utf-8");
    resp.getWriter().println(jsonObject);

     }
 }
