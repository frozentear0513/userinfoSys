package zhoumo.service.fl;

import org.json.JSONObject;
import zhoumo.jdbc.DeleteflJdbc;
import zhoumo.jdbc.EjflJdbc;

public class DeleteFlService {
    public JSONObject deleteFlxx(String yid) {
        //操作数据库插入一级分类
        DeleteflJdbc deleteflJdbc = new DeleteflJdbc();
        boolean deleteResult = deleteflJdbc.deleteFlxx(yid);
        if(deleteResult){
            return new JSONObject("{code:200, msg:操作成功}");
        }
        return new JSONObject("{code:500, msg:系统内部错误}");


    }
}
