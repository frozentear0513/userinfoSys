package EXCEL.Servlet;



import EXCEL.util.MyExcelDownLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/excelDownLoadServlet")
    public class ExcelDownLoadServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            MyExcelDownLoad myExcelDownLoad = new MyExcelDownLoad();
//            JSONObject jsonObject=null;
            try {
               myExcelDownLoad.excelDownLoad(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


