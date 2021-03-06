package zhoumo.service.fl;

import org.json.JSONObject;
import zhoumo.jdbc.EjflJdbc;
import zhoumo.jdbc.FlJdbc;

public class EjflService {
    public JSONObject insertEjfl(String erJiFenLeiMingCheng, String yid) {
        //操作数据库插入一级分类
        EjflJdbc ejflJdbc = new EjflJdbc();
        boolean insertResult = ejflJdbc.insertEjfl(erJiFenLeiMingCheng, yid);
        if(insertResult){
            return new JSONObject("{code:200, msg:操作成功}");
        }
        return new JSONObject("{code:500, msg:系统内部错误}");
    }
}
