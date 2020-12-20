package zuoye.Servlet;


import org.json.JSONObject;
import zuoye.Service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pagelist")
public class pageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        String username = req.getParameter("search_username");
        String bmmc = req.getParameter("search_bmmc");
        String zckssj = req.getParameter("search_start_zhuceshijian");
        String zcjssj = req.getParameter("search_end_zhuceshijian");
        String qxmc = req.getParameter("search_qxmc");
        Service  service= new Service();
        JSONObject jsonObject= service.pageList(page,limit,username,bmmc,zckssj,zcjssj,qxmc);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(jsonObject);
    }
}
