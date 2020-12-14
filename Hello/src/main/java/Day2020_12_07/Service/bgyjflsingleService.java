package Day2020_12_07.Service;

import Day2020_12_07.JDBC.bgyjflsingleJDBC;
import org.json.JSONObject;

public class bgyjflsingleService {
    public JSONObject Single(String id) {
        if(id==null){
            return new JSONObject("{code:500,msg:数据错误}");
        }
        bgyjflsingleJDBC Service = new bgyjflsingleJDBC();
        JSONObject result=Service.Single(id);
        return result;
    }
}
