package zuoye.Servlet;

import org.json.JSONObject;
import zuoye.Service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/insert")
public class insert extends HttpServlet{ @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String sex = req.getParameter("sex");
            String age = req.getParameter("age");
            String bmmc = req.getParameter("bmmc");
            String qxmc = req.getParameter("qxmc");
            String zhuceshijian = req.getParameter("zhuceshijian");
            String denglushijian = req.getParameter("denglushijian");
            Service service = new Service();
            JSONObject jsonObject= service.insert(username,password,sex,age,bmmc,qxmc,zhuceshijian,denglushijian);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(jsonObject);
        }
}
