1.需求分析
2.数据库设计
1）实体分析
    -图书             Book
    -用户             User
    -订单             OrderBean
    -订单详情          OrderItem
    -购物车项          CartItem

2)实体属性分析
    -图书：书名、作者、价格、销量、库存、封面、状态
    -用户：用户名、密码、邮箱
    -订单：订单编号、订单日期、订单金额、订单数量、订单状态、用户
    -订单详情：图书、数量、所属订单
    -购物车项：图书、数量、所属用户

1.显示主页面（index页面）
    -新建BookDAO、BookDAOImpl : getBookList()
    -新建BookService、BookService : getBookList()
    -新建BookController : index()
    -编辑index.html页面

2.首页上登录成功之后显示欢迎语和购物车数量
3.点击具体图书添加到购物车按钮
4.购物车详情

5.结账
    1）订单添加一条记录
    2）订单详情表添加7条记录
    3）购物车项表中需要删除对应的7条记录

6.关于订单信息中的订单数量
7.编辑购物车
8.关于金额的精度问题

9.过滤器判断是否是合法用户：
    -解决方法：新建SessionFilter,用来判断session中是否保存了currentUser
    -如果没有currentUser,表明当前不是一个登录合法的用户，应该跳转到登录页面让其登录

    -现在添加了过滤器之后，出现了如下错误
    localhost将您重定向次数过多
    尝试清除 Cookie

10.注册功能
    1.在注册页面显示验证码
        1）在web.xml文件中配置KaptchaServlet，以及配置相关的属性
        2）在页面上访问这个Serlet,然后这个Servlet做两件事情：
            -在页面上显示验证码图片
            -在session作用域中保存验证码信息，对应的key存储在Constans这个常量接口中
        3.用户在注册页面输入验证码发送给服务器，那么需要和session中保存的进行比较

    2.注册功能的实现
        1）三步骤：定义正则表达式的对象（两种方式）；定义待校验的字符串；校验
        2）正则表达式的规则：
            g i m
            . , \w , \W , \d , \D , \s , \S , \b , ^ , $
            [] [^] [-]
            * + ? {n} {n,} {n,m}
            |

        注册页面表单验证
        1）<form>有一个事件 onsubmit,
            onsubmit = "return false"  , 那么表单不能提交
            onsubmit = "return true"   , 那么表单能够提交

        2）获取文档中某一个节点的方式：
        //DOM:Document
        var unameTxt = document.getElementById("unameTxt");
        //BOM:Browser
        document.forms[0].uname;

    3.原生的Ajax(了解)
    第一步：客户端发送异步请求；并绑定对结果处理的回调函数
        1）<input type="text" name="uname" onblur="ckUname" />
        2）定义ckUname方法
            -创建XMLHttpRequest对象,注意不是xmlHttpRequest
            -XMLHttpRequest对象的操作步骤：
                -open(url,"GET",true)
                -onreadyStatusChange设置回调
                -send()发送请求
            -在回调函数中需要判断XMLHttpRequest对象的状态
               readyState(0-4) , status(200)
    第二步：服务器端做校验，然后将校验结果响应给客户端
