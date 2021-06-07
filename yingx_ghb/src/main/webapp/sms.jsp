<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <%
            String PostData = "account=w115444&password=mojiacai521.-+&mobile=手机号码&content="+java.net.URLEncoder.encode("您的验证码是：【变量】。请不要把验证码泄露给其他人。如非本人操作，可不用理会！。");
            //out.println(PostData);
            String ret = com.dxton.www.Send.SMS(PostData, "http://sms.106jiekou.com/gbk/sms.aspx");
            out.println(ret);
            //请自己反序列化返回的字符串并实现自己的逻辑
        %>

        </h1>
    </body>
</html>
