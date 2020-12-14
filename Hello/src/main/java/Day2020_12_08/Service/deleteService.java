package Day2020_12_08.Service;

import Day2020_12_08.JDBC.deleteJDBC;
import org.json.JSONObject;

public class deleteService {
    public JSONObject delete(String id) {
        if(id==null){
            return new JSONObject("{code:500,msg:数据错误}");
        }
        deleteJDBC deletejdbc =new deleteJDBC();
          JSONObject jsonObject= deletejdbc.delete(id);
          return jsonObject;
    }
}
