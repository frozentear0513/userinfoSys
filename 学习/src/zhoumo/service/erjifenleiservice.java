package zhoumo.service;

import org.json.JSONObject;
import zhoumo.jdbc.erjijdbc;

public class erjifenleiservice {
    public JSONObject insert(String erjifenleimingcheng, String yijifenleiid) {
        erjijdbc erji  =new erjijdbc();
     boolean result= erji.insert(erjifenleimingcheng,yijifenleiid);
       if(result){
               return new JSONObject("{code:200,msg:插入成功}");
           }
           return new JSONObject("{code:500,msg:系统内部错误}");
       }
    }


