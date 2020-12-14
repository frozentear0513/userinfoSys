package zhoumo.service.fl;

import org.json.JSONObject;
import zhoumo.jdbc.yijifenleijdbc;

public class yijifenleiservice {
    public JSONObject insert(String yijifenleimingcheng) {

               yijifenleijdbc fenleijdbc=  new yijifenleijdbc();
                boolean result= fenleijdbc.insertjdbc(yijifenleimingcheng);
        if(result){
           return new JSONObject("{code:200,msg:查询成功}");
        }
        return new JSONObject("{code:500,msg:系统内部错误}");
    }
}
