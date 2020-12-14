package Day2020_12_04.servlet;

import Day2020_12_04.service.user_sevice;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/user")
public class userlist_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       String req1=request.getParameter("name");
//       String req2=request.getParameter("password");
        user_sevice usersevice =new user_sevice();
        JSONObject jsonObject=usersevice.userlist(request);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonObject);


    }
}
