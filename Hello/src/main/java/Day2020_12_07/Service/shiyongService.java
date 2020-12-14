package Day2020_12_07.Service;

import Day2020_12_07.JDBC.shiyongJDBC;
import org.json.JSONObject;

public class shiyongService {

    public JSONObject insert(String id, String yonghuid, String erjiid) {
        if (id == null | yonghuid == null || erjiid == null) {
            return new JSONObject("{code:500,msg:数据异常}");
        }
        shiyongJDBC shiyong2 =new shiyongJDBC();
        boolean result= shiyong2.insert(id,yonghuid,erjiid);
        if (result){
            return new JSONObject("{code:200,msg:插入成功}");
        }
        return new JSONObject("{code:500,msg:系统内部错误}");
        }
    }
