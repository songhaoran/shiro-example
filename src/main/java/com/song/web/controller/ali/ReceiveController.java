package com.song.web.controller.ali;

import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Song on 2017/7/18.
 */
@Controller
public class ReceiveController {
    private String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq3gBfGt3/CIy055ciLl2eTVZp9eedQcYYvs3hcBywmPvi7eETwqhfPfeO3wjHpESpGTC2CHOq8pp1171LkIZWP2Utfq1ZWOOBz6ZQaGEdn99s4Ax3rC4A1SzsK9yNqzOyWZNtm+E+lqet3pGVhwuWqBkfQqJE/oqTTaN+r+GIsfLTMSthCgtvNqXaqXDRHw7z4icvxglBxM2T9nzrpNycef36DLmxazosGNMWxubhKLJ2tpYrIwAYwUThoK/E1ZGbVVa2VDecMv/2RP0ySEd4fmf2kz7Vw45w3A20TWirJwo2cYDXFNLmcBjvGU+yAGDmsGj5tFrKxZFZgvDidre5wIDAQAB";


    @RequestMapping("/ali/pay/resp")
    public void getPayResponse(String app_id,
                               String method,
                               String sign_type,
                               String sign,
                               String charset,
                               String timestamp,
                               String version,
                               String auth_app_id,
                               String out_trade_no,
                               String product_code) throws  Exception {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("app_id", app_id);
        paramsMap.put("method",method );
        paramsMap.put("sign_type", sign_type);
        paramsMap.put("sign", sign);
        paramsMap.put("charset",charset );
        paramsMap.put("timestamp",timestamp );
        paramsMap.put("version",version );
        paramsMap.put("auth_app_id",auth_app_id );
        paramsMap.put("out_trade_no",out_trade_no );
        paramsMap.put("product_code",product_code );

        AlipaySignature.rsaCheckV2(paramsMap, ALIPAY_PUBLIC_KEY, charset);
    }
}
