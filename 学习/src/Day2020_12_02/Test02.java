package Day2020_12_02;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class Test02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    {"weatherinfo":{"city":"成都",
//            "cityid":"101270101",
//            "temp":"21",
//            "WD":"北风",
//            "WS":"小于3级",
//            "SD":"61%",
//            "AP":"945.7hPa",
//            "njd":"暂无实况",
//            "WSE":"<3",
//            "time":"17:00",
//            "sm":"0",
//            "isRadar":"1",
//            "Radar":"JC_RADAR_AZ9280_JB"}}
            JSONObject ja = new JSONObject();
            ja.put("city", "成都");
            ja.put("cityid", "101270101");
            ja.put("WD", "北风");
            ja.put("WS", "小于3级");
            ja.put("SD", "61%");
            ja.put("AP", "945.7hPa");
            ja.put("njd", "暂无实况");
            ja.put("WSE", "<3");
            ja.put("time", "17:00");
            ja.put("sm", "0");
            ja.put("isRadar", "1");
            ja.put("Radar", "JC_RADAR_AZ9280_JB");
            JSONObject jb = new JSONObject();
            jb.put("weatherinfo", ja);
            System.out.println(jb);
            
            resp.getWriter().println(jb);
        }

    }


