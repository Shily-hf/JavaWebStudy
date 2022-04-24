1.Cookie
    1.创建Cookie对象
    2.在客户端保存Cookie
    3.设置Cookie的有效时长
        cookie.setMaxAge(60);    设置cookie的有效时长
        cookie.setDomain(pattern);
        cookie.setPath(uri);
    4.Cookie的应用
        4-1:记住用户名和密码十天  setMaxAge(60 * 60 * 24 * 10)
        4-2:十天免登录          先将cookie保存时长设置，然后从中获取用户名和密码

2.Kaptcha
    1.为什么需要验证码
    2.kaptcha如何使用
        -添加jar包 交给maven
        -在web.xml文件中注册KaptchaServlet,并设置验证码图片的相关属性
        -在html页面上编写一个img标签，然后设置src等于KaptchaServlet对应的url-pattern
    3.kaptcha验证码图片的各个属性在常量接口：Constans中
    4.KaptchaServlet在生成验证码图片时，会同时将验证码信息保存到session中
        因此，我们注册请求时，首先将用户文本框中输入的验证码值和session中保存的值进行比较，相等则进行注册

