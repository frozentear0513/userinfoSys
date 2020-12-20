package zuoye.Servlet;

import org.json.JSONObject;
import zuoye.Service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/delete")
public class delete extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String id = req.getParameter("id");
            Service service = new Service();
            JSONObject jsonObject= service.delete(id);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(jsonObject);
        }
}

