package zhoumo.service.bumen;

import org.json.JSONArray;
import org.json.JSONObject;
import zhoumo.jdbc.userlistjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userlistservice {
        public JSONObject chaxun() {
        userlistjdbc listjdbc  =new userlistjdbc();
        ResultSet result= listjdbc.chaxun();

        if(result==null){
            return  new JSONObject("{code:500,msg:没有数据}");
        }
        JSONObject  jsonObject=new JSONObject();
         JSONArray array=new JSONArray();
            try {
                while (result.next()){
                        JSONObject ja=new JSONObject();
                        ja.put("name",result.getString("name"));
                        ja.put("bumenmingcheng",result.getString("bumenmingcheng"));
                        ja.put("password",result.getString("password"));
                        array.put(ja);
                }

                jsonObject.put("code",200);
                jsonObject.put("msg","查询成功");
                jsonObject.put("userlist",array);
            } catch (SQLException e) {
                e.printStackTrace();
               return new JSONObject("{code:500,msg:系统内部错误}");
            }
            return jsonObject;
        }
    }

