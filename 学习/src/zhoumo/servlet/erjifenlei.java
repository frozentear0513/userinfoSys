package zhoumo.servlet;

import org.json.JSONObject;
import zhoumo.service.erjifenleiservice;
import zhoumo.service.fl.yijifenleiservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/erjifenlei")
public class erjifenlei extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String yijifenleiid= req.getParameter("yijifenleiid");
        String erjifenleimingcheng= req.getParameter("erjifenleimingcheng");
        resp.setContentType("text/html;charset=utf-8");
        if (erjifenleimingcheng==null || yijifenleiid==null){
            resp.getWriter().println( new JSONObject("{code:500,msg:参数错误}"));          ;
            return;
        }
        erjifenleiservice erji=  new erjifenleiservice();
        JSONObject jsonObject= erji.insert(erjifenleimingcheng,yijifenleiid);
        resp.getWriter().println(jsonObject);
    }

}



