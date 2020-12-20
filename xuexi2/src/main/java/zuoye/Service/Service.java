package zuoye.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import zuoye.JDBC.JDBC;

public class Service {
    public JSONObject pageList(String page, String limit,String username,String bmmc,String zckssj,String zcjssj,String qxmc) {
        JSONArray array = null;
        JSONObject jsonObject = new JSONObject();
        JDBC jdbc = new JDBC();
        int count = jdbc.selectCount(username,bmmc,zckssj,zcjssj,qxmc);
        int pageInt = Integer.parseInt(page);
        int limitInt = Integer.parseInt(limit);
        int pageCount = (count - 1) / limitInt + 1;
        if (page == null || "".equals(page)) {
            array = jdbc.pageList(0,username,bmmc,zckssj,zcjssj,qxmc);
        } else {
            array = jdbc.pageList((pageInt - 1) * limitInt,username,bmmc,zckssj,zcjssj,qxmc);
        }
        jsonObject.put("code", 0);
        jsonObject.put("msg", "查询成功");
        jsonObject.put("limit", 10);
        jsonObject.put("page", pageInt);
        jsonObject.put("data", array);
        jsonObject.put("count", count);
        jsonObject.put("pagecount", pageCount);
        return jsonObject;
    }

    public JSONObject delete(String id) {

        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.delete(id);
        return jsonObject;
    }

    public JSONObject insert(String username, String password, String sex, String age, String bmmc, String qxmc, String zhuceshijian, String denglushijian) {

        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.insert(username, password, sex, age, bmmc, qxmc, zhuceshijian, denglushijian);
        return jsonObject;

    }

    public JSONObject update(String id, String username, String password, String sex, String age, String bmmc, String qxmc, String zhuceshijian, String denglushijian) {

        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.update(id, username, password, sex, age, bmmc, qxmc, zhuceshijian, denglushijian);
        return jsonObject;

    }

    public JSONObject bmList() {

        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.bmList();
        return jsonObject;
    }

    public JSONObject qxList() {

        JDBC jdbc = new JDBC();
        JSONObject jsonObject = jdbc.qxList();
        return jsonObject;
    }
}

