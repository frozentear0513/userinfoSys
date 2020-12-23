package SMS.util;

import SMS.Servlet.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class quyzm {
    public  static JSONObject hqyzm( String tel){

    JSONObject jsonObject1=null;
    long time=System.currentTimeMillis();
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
        System.out.println(response.toString());
        //获取response的body
//        System.out.println(EntityUtils.toString(response.getEntity()));
//            jsonObject =new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject  jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));

           if(jsonObject.getInt("code")==0000){
               jsonObject1.put("code",200);
               jsonObject1.put("mobile_code",mobile_code);
               jsonObject1.put("times",time);
           }
    } catch (Exception e) {
        e.printStackTrace();
        jsonObject1=new JSONObject("{code:500,msg:'系统错误，发送失败'}");




        }
        return jsonObject1;
}
}
