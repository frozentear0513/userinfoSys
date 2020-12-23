package SMS.Servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



    /**
     * Created with IntelliJ IDEA.
     * User: thinknovo
     * Date: 2019/01/02
     * Description:        支付步骤开发: 1.注册蚂蚁金服开放平台账户，并申请成为开发者 https://docs.open.alipay.com/291/105971
     *                                   2.创建web应用程序 https://openhome.alipay.com/platform/manageHome.htm
     *                                   3.生成APP的密钥和公钥 https://docs.open.alipay.com/291/105971
     *                                   4.通过APP公钥，兑换支付宝公钥 https://openhome.alipay.com/platform/appDaily.htm?tab=info
     *                                   5.在第4步中的页面下载沙箱测试环境支付宝APP(只支持android测试)
     *                                   6.在第4步中的页面左侧可以查看沙箱环境中的商家账户和个人支付测试账户，虚拟钱可以随意充值，但上限为999.99万
     *                                   7.在第4步中的页面最下面有各种接入计费规则可以用作测试
     *                                   8.下载alipay-sdk-java-3.4.49.ALL.jar
     *                                   9.编写实例代码完成测试
     *
     *                       沙箱环境详细说明   https://docs.open.alipay.com/200/105311/
     *                       支付请求参数查询   https://docs.open.alipay.com/270/alipay.trade.page.pay
     * Version: V1.0
     */
    @WebServlet(name = "PayServlet", urlPatterns = "/payServlet")
    public class alipay  extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String serverURL = "https://openapi.alipaydev.com/gateway.do";     // alipay测试支付地址   生产环境支付地址为https://openapi.alipay.com/gateway.do
            String APP_ID = "2021000116679271";   // 创建的应用ID
            String mKey= "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCvWjPhFncLuN+e+NFTbeAqW8xXQ+LJLCXShiuqfMs2uzFtWYEhdKRPvP6nMswDyBc+QVb4kjRwou4nWNSDgjEHJi22sSzx7CYsO8kZLLWWxzJIEyrwauoUctco3NxillaJ093oiK6hv8ciAJ3Wi3r0MrfYjH8cPekS850H0grSnRiEeTMSSEOylxZhMtIQxagt6HGZi0vP958fkHvkKS85zbPvzUiC7iiBQsBgC0TFQJYkP6dDno3BO8/ap98mjhyPpujigmEi8xH2a0au771QAaLE9mqR7IIgL6aB0jDSQNo3JX0PH+XMbcIE9rz3PJqlTb/8i/XuWI0uM3AFPMUjAgMBAAECggEAP9JX4YvYplaSZXzwSypJlYE13Wki6M99sKMOz41+NRxqv/78pd2bVD5YfSha4uF3vDvx2KT2Gyzh+0Y0iuzexVha+QyBbEfkGONlrRj9urQjk+IOFdUD/49QylmgiO1o799B/DmLPencI38P859uj+TJzzJLrU7HPxOVytvi/oZ+FN4k2Dg/cbQTjWVKCQLmEuOife114NLBRPg8+W/Z+2eM4Jdn9rilE1IJjWejv/eRUKZqZN4gus1Tu2vwwwewj05fJFn9WdW4puFwJKCUQu2Zk75KNVfkcu3Fdaq5/dc/coaMXOFykqQzn9kQqubj0VIrwIlBvxBjT1yojfcIkQKBgQDoy3KQSGIxPZMuM0P8mAySPN+x+dwkkHShSBk5ULudRKwAwWu4UAwZxguNw0jioYftbIQV7G2zqsLPAmUnK77/lFwqKC27YkTnD8qX3CxJbj0Sycye+2cvqmUsLyk7g8JgXRl3NmIumagDDsg/ydvXo14QgnDhLOSsGM9TPN0xSQKBgQDA1OrBljXhwQQ8pit04zBrGv2B8whUt95owAVWEcWPhL83ZGIuBzIUnPA0SCdaJesF7+B0XC0A1961Y1AqFkH3DaflwPu7ktHsN5pJQEpnMNW9VSJpca+kZ2g6r47DnAh4WgUqgQF244mcBDOSEtdsFWTs6ZKKWN/rFr5iMXVvCwKBgQDH5vRgy5duOzboxGMMRDD/XsCkny+YQme0myn12srQ2Mhu+akbo7lQZI1FVb1/xz/m9HlULithzi/s/VyJT3p7FBY0qT3kHBWY6SmKUCnDgKN3P8yzdKT9/G9WisT4c0N15lxYz11uMMpinc39YMSknofG24w9AIT493EJREmY6QKBgHBFRy1RFKwNIjdhlFmo0Q77FvBrHMWejCq26iRzkm/+HQrWMmeUHu9rdFssS2UsQIsjX66/4B/mj69SVGE8tkluNPwLAUXztK9wrC7L9MXh5AApKjD0VQxGfKP2hQuUtEXGO83EcKLU1dD1jUUWHUC7sBhT4dMU7cJFarFnFHWDAoGBAIyZb3lKD6+VAUJJmZiJit7OaK9W8gmokPM5pDtF0EmCwkLRZV7g+J/exWaXtyj2JAUuN7xf0qBQNRmJ2N4+djloioao8R/UiDesvkavFzm9u+ComIY9gc2161hKaYccBbvQWzM6v7JpA6gZlT050i1yGUHAzRFnbDNXmCemNunb";
            String aKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhhD25Lx7jVhP5Wc5MmcO0c0WFMCxLUunVmpaEW3kehmNRhHu2zI9r6X9663aXRqX4R2NiQXUC+1D1gbxsVtYws8/pidu2UvrqBuv/Ls3Nr8lKrD41m3NW1h3DpuBlyhydIx6HxNyuODPZg8dF92ccsX81N5s1d1cXSjWyZ+fBRjiLhWZJVmQEj+wG2vW0tvfgeW7LFBMKW5Mk70YOyVaSh6InIdClEvEoBpaHvbUkzHzC3RJbSBxVBFalDah3ITrZyf6P5dMB1f0SUz7dzOElfANpL5WvaPw8eP+2EDDYyInv37rWhryttgY1cjWAYCcYYa/AqnPlIsWnV+cokbzOwIDAQAB";
            AlipayClient alipayClient = new DefaultAlipayClient(serverURL, APP_ID,
                    mKey, "json", "UTF-8", aKey, "RSA2");   //  获得初始化的AlipayClient
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();   //  创建API对应的request
            alipayRequest.setReturnUrl("http://localhost:8080/success.html");        // 用户确认支付后，支付宝get请求returnUrl
            alipayRequest.setNotifyUrl("http://localhost:8080/payReturnServlet");    // 交易成功后，支付宝post请求notifyUrl（商户入参传入）
        /*
            由于同步返回的不可靠性，支付结果必须以异步通知或查询接口返回为准，不能依赖同步跳转。
            商户系统接收到异步通知以后，必须通过验签（验证通知中的sign参数）来确保支付通知是由支付宝发送的。
         */

        /*
             填充业务参数
                 out_trade_no   每笔订单号需要自身的唯一id，支付宝会根据此id判断是否交易
                 total_amount   这笔订单的价格
                 subject        订单商品名称
                 product_code   支付方式：QUICK_WAP_PAY 登录账号支付
             这四个参数应该从结算页面传递过来
         */
            // 每笔订单号需要自身的唯一id，支付宝会根据此id判断是否交易
            String out_trade_no = "2212232936751";
            // 这笔订单的价格
            String total_amount = "8888";
            // 订单商品名称
            String subject = "荣耀100 512G";
            // 支付方式：QUICK_WAP_PAY 登录账号支付
            String product_code = "QUICK_WAP_PAY";

            String bizContent = "{" +
                    "\"out_trade_no\":\"" + out_trade_no + "\"," +
                    "\"total_amount\":" + total_amount + "," +
                    "\"subject\":\"" + subject + "\"," +
                    "\"product_code\":\"" + product_code + "\"}";          // 生成支付json数据
            System.out.println("支付生成json数据 bizContent=" + bizContent);

            alipayRequest.setBizContent(bizContent);                         // 提交支付请求

            String form = "";
            try {
                form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(form); // 直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doPost(request, response);
        }
    }

