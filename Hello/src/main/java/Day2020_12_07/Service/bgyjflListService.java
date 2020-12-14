package Day2020_12_07.Service;

import Day2020_12_07.JDBC.bgyjflListJDBC;
import org.json.JSONObject;

public class bgyjflListService {

    public JSONObject list() {
        bgyjflListJDBC bgyjflListjdbc = new bgyjflListJDBC();
        JSONObject jsonObject= bgyjflListjdbc.list();
        return jsonObject;
    }

}
