package SMS.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;



    /**
     * Created with IntelliJ IDEA.
     * User: thinknovo
     * Date: 2019/01/02
     * Description:  需要mail-1.4.7.jar的支持  https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
     *               这里以QQ邮箱账户为发送方，首先需要进入QQ邮箱，找到左上方的设置，然后选择账户，找到POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务，开启pop3，获得授权码
     * Version: V1.0
     */
    public class MyJavaMail {

        private String username;          // 登录的用户名
        private String email;             // 收件人邮箱
        private String uuid;              // uuid唯一码

        public MyJavaMail(String username, String email) {  // username 平台用户名
            this.username = username;
            this.email = email;
            // java系统默认生成的uuid，但是默认的uuid是有横杠隔开的，项目中需要手动去掉横杠
            this.uuid = UUID.randomUUID().toString().replaceAll("-", "");  // 生成唯一随机码;
        }


        public void run() {
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
                        "<a href='http://localhost:8080/xiugaixinxi?code=" + uuid + "'>" +
                        "http://localhost:8080/xiugaixinxi?code=" + uuid
                        + "</a></h3></body></html>";
                message.setContent(content, "text/html;charset=UTF-8");

                // 7.发送邮件
                Transport.send(message);   // 阻塞方法
                System.out.println("邮件成功发送!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

        /**
         * 提供为外部调用的邮件方法  应该把此方法提出来放置到service服务中
         *
         * @return 返回状态true为成功，false为失败
         */
//        public boolean checkRegister() {
//            // 没有判断邮箱和用户名是否为空，需要额外增加代码处理
//            if (!email.matches("^\\w+@(\\w+\\.)+\\w+$")) {          // 利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
//                return false;
//            }
//
//            User user = new User(username, password, email, uuid);        // 写入用户POJO
//            UserDao userDao = new UserDaoImpl();                          // 将用户保存到数据库
//            if (userDao.save(user) > 0) {                                   // 保存成功则通过线程的方式给用户发送一封邮件
//                new Thread(new MyJavaMail(username, email)).start();       // 启动一个子线程发送邮件，第一个参数为接收账号，第二参数为随机码
//                return true;
//            }
//            return false;
//
//
//            public static void main (String[]args){
//                // pop3协议如果权限认证失败，会报535错误
//                // 启动一个子线程发送邮件，第一个参数为发送方用户账户，第二参数为被接收的邮件地址
//                new Thread(new MyJavaMail("1358742349@qq.com", "1358742349@qq.com")).start();
//                System.out.println(UUID.randomUUID().toString().replace("-", ""));
//            }
//        }
//    }

