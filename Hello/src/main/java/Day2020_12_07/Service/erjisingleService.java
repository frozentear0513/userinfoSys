package Day2020_12_07.Service;

import Day2020_12_07.JDBC.bgyjflsingleJDBC;
import Day2020_12_07.JDBC.erjisingleJDBC;
import org.json.JSONObject;

public class erjisingleService {
    public JSONObject Single(String yijiid) {
        if(yijiid==null){
            return new JSONObject("{code:500,msg:数据错误}");
        }
        erjisingleJDBC jdbc = new erjisingleJDBC();
        JSONObject result=jdbc.Single(yijiid);
        return result;

    }
}
