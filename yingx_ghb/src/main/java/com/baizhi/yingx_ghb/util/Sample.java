package com.baizhi.yingx_ghb.util;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;

import java.util.Random;

public class Sample {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


    //生成随机数
    public static String getRandom(int n){
        char[] code="0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(code[new Random().nextInt(code.length)]);
        }
        return  sb.toString();
    }
    //调用方法 发送验证码
    public static String sendPhoneCode(String phoneNumbers, String code) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("LTAI5tJskyFFdYmPg2gcUM2G", "3RvcU24SlnarluPo6rXs0VJiqqDSEN");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                .setSignName("小蛋黄")
                .setTemplateCode("SMS_171112532")
                .setTemplateParam("{\"code\":\"+code+\"}");
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse smsResponse = client.sendSms(sendSmsRequest);
        //获取响应数据对象
        SendSmsResponseBody responseBody = smsResponse.getBody();
        //获取验证码的状态
        String sendCode = responseBody.code;
        String message=null;
        if (sendCode.equals("OK")){
            message="发送成功";
        }
        if (sendCode.equals("isv.MOBILE_NUMBER_ILLEGAL")){
            message="非法的手机号";
        }
        if (sendCode.equals("isv.OUT_OF_SERVICE")){
            message="业务停机";
        }
        if (sendCode.equals("isv.INVALID_PARAMETERS")){   // isv.INVALID_PARAMETERS
            message="参数异常";
        }
        return message;
    }

    //测试
    public static void main(String[] args_) {
        //获取随机验证码
        String random = getRandom(6);
        System.out.println(random);
        //获取一个手机号
        String phone="17634009044";

        //获取手机验证码
        try {
            String msg= sendPhoneCode(phone, random);
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
