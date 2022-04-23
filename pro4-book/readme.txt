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
