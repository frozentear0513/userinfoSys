package Day2020_12_12.Service;

import Day2020_12_12.JDBC.JDBC;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class Service {

    public JSONObject zhuce(String username, String password) {
        if (username == null || password == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "数据错误");
            return jsonObject;
        }
        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.zhuce(username, password);
        return jsonObject;
    }

    public JSONObject denglu(String username, String password) {
        if (username == null || password == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "数据错误");
            return jsonObject;
        }
        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.denglu(username, password);
        return jsonObject;
    }

    public boolean check(HttpServletRequest req) {
        Object object = req.getSession().getAttribute("username");
        if (object == null) {
            return true;
        }
        return false;
    }

    public JSONObject chaxun(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        if (check(req)) {
            jsonObject.put("code", 500);
            jsonObject.put("msg", "请登录后再试");
            return jsonObject;
        }
        JDBC jdbc = new JDBC();
        jsonObject = jdbc.chaxun();
        return jsonObject;
    }

    public JSONObject chaxun2(String id, String username, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        /*if (check(req)) {
            jsonObject.put("code", 500);
            jsonObject.put("msg", "请登录后再试");
            return jsonObject;
        }*/
        JDBC jdbc = new JDBC();
        return jdbc.chaxun2(id,username);

    }

    public JSONObject shanchu(String id) {
        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.shanchu(id);
        return jsonObject;
    }

    public JSONObject update(String id, String username, String password) {

           JDBC jdbc=new JDBC();
          JSONObject jsonObject=  jdbc.update(id,username,password);

        return jsonObject;
    }
}