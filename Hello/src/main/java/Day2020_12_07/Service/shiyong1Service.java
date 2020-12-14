package Day2020_12_07.Service;

import Day2020_12_07.JDBC.shiyonglistJDBC;
import org.json.JSONObject;

public class shiyong1Service {
    public JSONObject list() {
        shiyonglistJDBC shiyong1jdbc=  new shiyonglistJDBC();
         JSONObject jsonObject=  shiyong1jdbc.list();
         return jsonObject;
    }
}
