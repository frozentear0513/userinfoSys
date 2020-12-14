package zhoumo.servlet;

import org.json.JSONObject;
import zhoumo.service.bumen.bm_service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/qqq")
public class bm_servlet extends HttpServlet {
    @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String bumenmingcheng= req.getParameter("bumenmingcheng");
        bm_service bumen= new bm_service();
      JSONObject  jsonObject= bumen.check(bumenmingcheng);
      resp.setContentType("text/html;charset=utf-8");
      resp.getWriter().println(jsonObject);}


//      public static void main(String[] args) {
//          bm_service bun= new bm_service();
//          bun.check(" klgg");
//    }
}
