package Day2020_12_07.Service;

import Day2020_12_07.JDBC.deleteJDBC;
import org.json.JSONObject;

public class deleteService {

    public JSONObject delete(String mingcheng) {
        if(mingcheng==null){
            return new JSONObject("{code:500,msg:数据错误}");
    }
      deleteJDBC deletejdbc=new deleteJDBC();
        boolean result=deletejdbc.delete(mingcheng);
        if (result) {
            return new JSONObject("{code:200,msg:插入成功}");
        }
        return new JSONObject("{code:500,msg:系统内部错误}");
    }
}
