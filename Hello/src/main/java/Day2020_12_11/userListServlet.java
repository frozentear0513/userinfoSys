package Day2020_12_11;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class userListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonObject=new JSONObject();
        JSONArray array= new JSONArray();
        JSONObject jsonObject1=new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("username","刘俊先");
        jsonObject.put("password","123456");
        jsonObject.put("sex","男");
        jsonObject.put("xueli","大专");
        array.put(jsonObject);

        jsonObject=new JSONObject();
        jsonObject.put("id",2);
        jsonObject.put("username","小刘");
        jsonObject.put("password","123456");
        jsonObject.put("sex","男");
        jsonObject.put("xueli","本科");
        array.put(jsonObject);

        jsonObject1.put("code",200);
        jsonObject1.put("msg","查询成功");
        jsonObject1.put("userList",array);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject1);
    }
}

