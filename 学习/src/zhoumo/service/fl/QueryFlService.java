package zhoumo.service.fl;

import org.json.JSONArray;
import org.json.JSONObject;
import zhoumo.jdbc.EjflJdbc;
import zhoumo.jdbc.QueryflJdbc;

import java.sql.ResultSet;

public class QueryFlService {
    public JSONArray queryAllFlxx() {
        //操作数据库插入一级分类
        QueryflJdbc queryflJdbc = new QueryflJdbc();
        ResultSet queryFlxxs = queryflJdbc.queryAllFlxx();
        if(queryFlxxs == null){
            return new JSONArray();
        }
        JSONArray flxxs = new JSONArray();
        try {
            while (queryFlxxs.next()){
                JSONObject flxx = new JSONObject();
                flxx.put("二级分类名称", queryFlxxs.getString("ejflmc"));
                flxx.put("一级分类名称", queryFlxxs.getString("yjflmc"));
                flxxs.put(flxx);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new JSONArray();
        }
        return flxxs;
    }

    public JSONObject queryEjFlxx(String eid) {

        return null;

    }
}
