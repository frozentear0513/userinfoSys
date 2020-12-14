package zhoumo.service.bumen;

import org.json.JSONObject;
import zhoumo.jdbc.user_jdbc01;

public class user_service {
    public JSONObject check1(String name, String bumenid){

        if(bumenid==null||"".equals(bumenid)||name==null || "".equals(name)) {
            return new JSONObject("{code:500,msg:'输入错误'}");
        }
        user_jdbc01 jdbc= new user_jdbc01();
        JSONObject jsonObject=  jdbc.zhixing(name,bumenid);
        return jsonObject;
    }


}
