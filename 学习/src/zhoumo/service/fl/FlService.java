package zhoumo.service.fl;

import org.json.JSONObject;
import zhoumo.jdbc.FlJdbc;

public class FlService {

    public JSONObject insertYjfl(String yiJiFenLeiMingCheng) {
        //操作数据库插入一级分类
        FlJdbc flJdbc = new FlJdbc();
        boolean insertResult = flJdbc.insertYjfl(yiJiFenLeiMingCheng);
        if(insertResult){
            return new JSONObject("{code:200, msg:操作成功}");
        }
        return new JSONObject("{code:500, msg:系统内部错误}");
    }
}
