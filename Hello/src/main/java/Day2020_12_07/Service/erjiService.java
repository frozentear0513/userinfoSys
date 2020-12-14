package Day2020_12_07.Service;

import Day2020_12_07.JDBC.bgyjflsingleJDBC;
import Day2020_12_07.JDBC.erjiJDBC;
import org.json.JSONObject;

public class erjiService {

    public JSONObject insert( String erjimingcheng, String yijiid) {
        if( erjimingcheng==null | yijiid==null){
            return new JSONObject("{code:500,msg:数据错误}");
        }
        erjiJDBC erjijdbc = new erjiJDBC();
        JSONObject result=erjijdbc.insert(erjimingcheng,yijiid);
        return result;
    }
}
