package ali;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.junit.Test;

/**
 * Created by Song on 2017/7/18.
 */
public class PayTest {

    private String APP_ID = "2016080500170254";
    private String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDVqM2nlqiLSh1yETVnVHFiUk5nf9M5P8H365V5bY7yjJvPvS+MW7iZ/6QbSR4QEhRGH7cgTkHZE9EhO+d7SJF2DgmI0BqOJUWkr7a6mW47P/4w6RhFRIZn2s+XTARj+rx2rlnF1P6lIJv2L/JRv+a1P6APPhlVv3Aez+Woft0aZL7E5XkEERxoK6sqtQtxFdsbCz092jnfuxNdPGJnVOSPn3M29TAeIsOLwRphWMfPUx2KgHcmI9SRjuHrZJGX5eDJSk6SztX65RiWOWnRU4m/Okh9sa7kSWBQ/T5jSLUXRmJpXyfxkufKrSqjOER9axLnmOtm0Aw40OqL0Ea5f1VxAgMBAAECggEBANUjRKyW5WG2OLOOqSiAG2/KzX4ADNUCXlwUPhNZEI2zaMeOYX1yDjBJzgqOIRhFAMaHYuYuSw/i7e9qU9wvIzSSgj5h4uvEELpdszpoTod6qfdewCBxYGb/57B1W7fk69a0zMdGWBSf+BVZ9TBJ8DsE0oYCG8HnbFDAcchpbeWhoeq+E9nYUGGL6pR/HCwlbwQfyMNZHbM5Ipti3AyiNWGf99zwzM+gzqbXNUljtMNsvvLAI3KUJBOg3YwCn6m+MAb7DEYOIo7zE/232I6L4Xhtf+3CK7b4Kj0XrYEWdu7exTutKMX1YD0CnUhEl7VckqhKhhkRqWlv380A13jmwUECgYEA7NCeBaRqXjHxJmIvOP8qrIDUkNyRJ0X0nOnH01kLCfIUtOA8CiC/Eb7vhQQ5Kep6wcw7rPNZYYEFlxjnb0cwZ6DEH14GMRehbEODmQBFgiktlZrg5OtqtzGaClxcGN8lgcRXi6dcN5ug8WCsjzerSkWbuTER/LOsGVuUM3FpgmkCgYEA5vf0vWi2NJbkQhSubv/lwhI/bO2+bnF6mYC0UjkDIazX5cVOKsrxvr82RcYX0eYDkzY5NM5JcEgJ0nnd68F0piBI/fIVzzN12IthLPjmVusDf9DWhg+M3h0K1IralQBPgYB9hhNMbn3cUjcpw3ab2Ut5R+OdSq/LBS2l03y4SckCgYEA33AamQI/Zi7C9E9RdPq0vdw0M5YY0rBbky2038G0+6ZQSTUFER1CijDX9247rUPSnUXgzFcy7x94Mc9KliDaF/yE73+cYfeE1AyDfvnVm3DU1MI0DWnd34lKaRopv0HUNrMVUuwOw1zwpq5rQhnpYQN6YWpeKUsxRx+0HjGUtnkCgYBYeBu/vW8lX7LY+PEDxXMtTi4LPFmYFlF+oMTxRbNEX99mvJe/XnMQvE8+lK0lEjTGW5u39Vg98OoKO/HcTwKua2lqFCN0dWKral/ziPc5zdVOTSEOwsATiA5Q8UrejfrmQOBTzeqV3BHbUBPoMfNqXTV8atwh8ooSVrTQ+FHiuQKBgEuIgTt0vd8dOgW7HufbiOPWPgIfk02Fk5X7dpQfyxpma62yQUxdXuxXmY88K/AtvB5k2aUgEzA6SQQ02AgPZdRG59lLXAZ3bseQ15t8qm75i0wspNnYN1yq0epuKOivJe/J7QQHm5n+6evJXAdd4xKvath6fvHEE40VpRdiEO66";
    private String FORMAT = "json";
    private String CHARSET = "UTF-8";
    private String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq3gBfGt3/CIy055ciLl2eTVZp9eedQcYYvs3hcBywmPvi7eETwqhfPfeO3wjHpESpGTC2CHOq8pp1171LkIZWP2Utfq1ZWOOBz6ZQaGEdn99s4Ax3rC4A1SzsK9yNqzOyWZNtm+E+lqet3pGVhwuWqBkfQqJE/oqTTaN+r+GIsfLTMSthCgtvNqXaqXDRHw7z4icvxglBxM2T9nzrpNycef36DLmxazosGNMWxubhKLJ2tpYrIwAYwUThoK/E1ZGbVVa2VDecMv/2RP0ySEd4fmf2kz7Vw45w3A20TWirJwo2cYDXFNLmcBjvGU+yAGDmsGj5tFrKxZFZgvDidre5wIDAQAB";
    private String SIGN_TYPE = "RSA2";
    private String URL = "https://openapi.alipaydev.com/gateway.do";

    @Test
    public void testPay() {
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20160320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        /*httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();*/
        System.out.println();
    }

    @Test
    public void testQuery() throws Exception{
        AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20160320010101001\"," +
                "    \"trade_no\":\"2014112611001004680 073956707\"" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

    }
}
