package SMS.Service;

import SMS.JDBC.JDBC;
import SMS.Servlet.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Service {
    public JSONObject insert(String tel) {

        long times=System.currentTimeMillis();
        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000); // 五位验证码  只需要随机三位 (321) 另外两位直接赋值(32132)
        String host = "https://intlsms.market.alicloudapi.com";
        String path = "/comms/sms/sendmsgall";
        String method = "POST";
        String appcode = "6250989a4d784541ad67bc23ed52361e";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");
        bodys.put("channel", "0");
        bodys.put("mobile", "+86"+tel);
        bodys.put("templateID", "0000000");
        bodys.put("templateParamSet", mobile_code+", 1");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
            //获取response的body
//        System.out.println(EntityUtils.toString(response.getEntity()));
//            jsonObject =new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject  jsonObject1 = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonObject1.getInt("code"));
            if(jsonObject1.getInt("code")==0000){
                JDBC jdbc= new JDBC();
                int result=  jdbc.insert(tel,mobile_code,times);
                if (result>0) {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("code",200);
                    jsonObject.put("msg","发送成功");
                    jsonObject.put("mobile_code",mobile_code);
                    jsonObject.put("times",times);
                    return  jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new JSONObject("{code:500,msg:'发送失败'}");
    }




    public boolean shijian(String tel, int mobile_code) {
        JDBC jdbc= new JDBC();
        boolean result= jdbc.shijian(tel,mobile_code);
        if(result){
            return true;
        }
        return false;
    }
    public JSONObject yanzheng(String tel, int mobile_code) {
        JSONObject jsonObject = null;
        Service serice = new Service();
        boolean result1 = serice.shijian(tel, mobile_code);

        if (result1) {
            JDBC jdbc1 = new JDBC();
            boolean result = jdbc1.yanzheng(tel, mobile_code);
            if (result) {
                JDBC jdbc = new JDBC();
                int result3 = jdbc.update(tel);
                if (result3 > 0) {
                    return jsonObject = new JSONObject("{code:200,msg:'登陆成功'}");
                }
                return jsonObject = new JSONObject("{code:500,msg:'登录失败'}");
            }
        }
        return jsonObject = new JSONObject("{code:500,msg:'验证码失效'}");
    }
}
