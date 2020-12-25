package EXCEL.util;



import org.apache.poi.ss.usermodel.Workbook;
import zuoye.JDBC.JDBC;
import EXCEL.util.POIUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
    public class MyExcelDownLoad {
//        JSONObject jsonObject = new JSONObject();
        public void excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
            List<List<String>> arrayList_outer = new ArrayList<>();
            JDBC jdbc = new JDBC();
            ResultSet resultSet = jdbc.excelList();
            List<String> arrayList_inner = null;
            arrayList_inner = new ArrayList<>();

            arrayList_inner.add("id");
            arrayList_inner.add("username");
            arrayList_inner.add("password");
            arrayList_inner.add("sex");
            arrayList_inner.add("age");
            arrayList_inner.add("zhuceshiajian");
            arrayList_inner.add("denglushijain");
            arrayList_inner.add("bmmc");
            arrayList_inner.add("qxmc");
            arrayList_outer.add(arrayList_inner);
            while (resultSet.next()) {
                arrayList_inner = new ArrayList<>();
                arrayList_inner.add(resultSet.getString("id"));
                arrayList_inner.add(resultSet.getString("username"));
                arrayList_inner.add(resultSet.getString("password"));
                arrayList_inner.add(resultSet.getString("sex"));
                arrayList_inner.add(resultSet.getString("age"));
                arrayList_inner.add(resultSet.getString("zhuceshijian"));
                arrayList_inner.add(resultSet.getString("denglushijian"));
                arrayList_inner.add(resultSet.getString("bmmc"));
                arrayList_inner.add(resultSet.getString("qxmc"));
                arrayList_outer.add(arrayList_inner);

            }
                POIUtil poiUtil = new POIUtil();
                Workbook workbook = poiUtil.creatExcelForPOI(arrayList_outer, "第一页");

                if (workbook != null) {
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
                    //获取响应报文输出流对象
                    ServletOutputStream out = response.getOutputStream();
                    //输出
                    workbook.write(out);
                    out.flush();
                    out.close();
//                    jsonObject.put("code", 200);
//                    jsonObject.put("msg", "下载成功");

                } else {
//                    jsonObject.put("code", 500);
//                    jsonObject.put("msg", "系统服务出错");
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println("系统服务出错");
                }

//            return jsonObject;
        }
    }





