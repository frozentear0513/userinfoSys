package zhoumo.servlet;

import org.json.JSONObject;
import zhoumo.service.fl.yijifenleiservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/yijifenlei")
public class yijifenlei extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String yijifenleimingcheng= req.getParameter("yijifenleimingcheng");
      resp.setContentType("text/html;charset=utf-8");
      if (yijifenleimingcheng==null){
          resp.getWriter().println( new JSONObject("{code:500,msg:参数错误}"));          ;
          return;}

            yijifenleiservice yiji=  new yijifenleiservice();
            JSONObject jsonObject=  yiji.insert(yijifenleimingcheng);

            resp.getWriter().println(jsonObject);
      }


    }

