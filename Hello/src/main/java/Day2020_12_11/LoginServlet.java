package Day2020_12_11;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pppp")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");


//       resp.sendRedirect("/success.html");//转向
//        req.getRequestDispatcher("/success.html").forward(req,resp);//转发
           JSONObject jsonObject=new JSONObject();
           jsonObject.put("code",200);
           jsonObject.put("msg","登陆成功");
           resp.getWriter().println(jsonObject);



    }
}
