package Day2020_12_07.Service;

import Day2020_12_07.JDBC.bgyjflsingleJDBC;
import Day2020_12_07.JDBC.shiyongsingleJDBC;
import org.json.JSONObject;

public class SingleService {

    public JSONObject Single(String id) {

        if(id==null){
            return new JSONObject("{code:500,msg:数据错误}");
        }
        shiyongsingleJDBC jdbc =new shiyongsingleJDBC();
        JSONObject result=jdbc.Single(id);
        return result;
    }
    }
