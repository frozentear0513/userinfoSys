package Day2020_12_07.Service;

import Day2020_12_07.JDBC.bgyjflJDBC;
import org.json.JSONObject;

public class bgyjflService {

    public JSONObject insert(String mingcheng) {
        bgyjflJDBC bgyjflbdjc = new bgyjflJDBC();
        boolean result = bgyjflbdjc.insert(mingcheng);

        if (result) {
            return new JSONObject("{code:200,msg:插入成功}");
        }
            return new JSONObject("{code:500,msg:系统内部错误}");
    }
}
