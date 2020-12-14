package zhoumo.servlet;

import org.json.JSONObject;

import zhoumo.service.bumen.user_service;

import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class user_servlet extends HttpServlet {
    @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              String  a=req.getParameter("name");
              String  b =req.getParameter("bumenmingcheng");


             user_service service= new user_service();
             JSONObject jsonObject=service.check1(a,b);

             resp.setContentType("text/html;charset=utf-8");
             resp.getWriter().println(jsonObject);
    }

//    public static void main(String[] args) {
//        user_service service= new user_service();
//        JSONObject jsonObject=service.check1("刘俊先","1");
//    }
}
