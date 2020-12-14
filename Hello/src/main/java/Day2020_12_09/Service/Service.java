package Day2020_12_09.Service;

import Day2020_12_12.JDBC.JDBC;

import org.json.JSONObject;

public class Service {

    public JSONObject zhuce(String username, String password) {
        if(username==null||password==null){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",500);
            jsonObject.put("msg","数据错误");
            return jsonObject;
    }
       JDBC jdbc=  new JDBC();
        JSONObject jsonObject=jdbc.zhuce(username,password);
        return jsonObject;
}

    public JSONObject denglu(String username, String password) {
        if(username==null||password==null){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",500);
            jsonObject.put("msg","数据错误");
            return jsonObject;
        }
        JDBC jdbc=  new JDBC();
        JSONObject jsonObject=jdbc.denglu(username,password);
        return jsonObject;
    }
}

