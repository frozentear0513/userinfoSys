package Day2020_12_04.service;

import Day2020_12_04.jdbc.user_jdbc;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class user_sevice {
    public  boolean check(HttpServletRequest request) {
        Object a = request.getSession().getAttribute("username");//判断Session中存不存在：那么
        if (a == null) {//如果有，返回return
            return true;
        }
        return false;//否则返回false
    }

    public JSONObject userlist(HttpServletRequest request) {

        if (check(request)) {
            JSONObject  jsonObject = new JSONObject("{cocd:500,msg:'请登录后再试'}");
            return jsonObject;
        }
        user_jdbc jdbc = new user_jdbc();
        JSONObject  jsonObject = jdbc.userlist();
        return  jsonObject;
    }

}











