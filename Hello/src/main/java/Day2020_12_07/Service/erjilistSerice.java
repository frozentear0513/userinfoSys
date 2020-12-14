package Day2020_12_07.Service;

import Day2020_12_07.JDBC.erjilistJDBC;
import org.json.JSONObject;

public class erjilistSerice {


    public JSONObject list() {
        erjilistJDBC erjilistjdbc = new erjilistJDBC();
        JSONObject result = erjilistjdbc.list();
        return result;
    }
}
