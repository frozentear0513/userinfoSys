package SMS.Service;

import SMS.JDBC.JDBC;
import SMS.Servlet.HttpUtils;
import SMS.util.MailFiled;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

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
    public boolean run(String email, String uuid) {
        String host = "smtp.qq.com";                        // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)  这里需要继续扩展做判断分支处理

        Properties properties = System.getProperties();      // 获取系统属性

        properties.setProperty("mail.smtp.host", host);     // 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");  // 打开认证

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MailFiled.mailName, MailFiled.mailCode); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);

            // 3.设置发件人
            message.setFrom(new InternetAddress(MailFiled.mailName));

            // 4.设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // 5.设置邮件主题
            message.setSubject("码农社区账号激活");

            // 6.设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接，如果不是本人操作，请勿点击</h1><h3>" +
                    "<a href='http://localhost:8080/xiugaixinxi.html?code=" + uuid + "'>" +
                    "http://localhost:8080/xiugaixinxi.html?code=" + uuid
                    + "</a></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");

            // 7.发送邮件
            Transport.send(message);   // 阻塞方法
            System.out.println("邮件成功发送!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }

    public JSONObject send(String username, String email) {
       String  uuid = UUID.randomUUID().toString().replaceAll("-", "");

          Service serivce=  new Service();
        boolean result=serivce.run(email,uuid);
         if(result){
            JDBC jdbc= new JDBC();
            int result1= jdbc.jilu(uuid,username);
            if(result1>0){
                return new JSONObject("{code:200,msg:发送成功}") ;
            }
         }
        return new JSONObject("{code:500,msg:系统错误}") ;
    }

    public JSONObject xiugai(String password, String uuid) {

                JDBC jdbc= new JDBC();
                 int result=  jdbc.xiugai(password,uuid);
                 JSONObject jsonObject=new JSONObject();
                 if(result>0){
                     return new JSONObject("{code:200,msg:修改成功}");
                 }
                 return new JSONObject("{code:200,msg:修改失败}");
    }
}
