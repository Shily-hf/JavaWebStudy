Vue
    1) {{}} - 相当于innerText
    2) v-bind:value - 绑定value值      简写：:value
    3)v-model:双向绑定                 简写 v-model
    4)v-if,v-else,v-show
        v-if和v-else之间不能有其它节点
        v-show是通过样式表display来控制节点是否显示
    5）v-for 迭代
        v-for={fruit in fruitList}
    6)v-on 绑定事件
    7)其他：
        -trim:去除首尾空格,split(),join()
        -watch表示侦听属性
        -生命周期

Axios
    Axios是Ajax的一个框架，简化Ajax操作
    Axios执行Ajax操作的步骤：
    1.添加并引入Axios的js文件
    2-1.客户端向服务器端异步发送普通参数值
    -基本格式：axios().then().catch()
    -示例：
       axios({
            method:"post",
            url:"......",
            param:{
                uname:"lina",
                pwd:"ok"
            }
       })
       .then(function(value){})     //成功响应时执行回调     value.data可以获取到服务器响应的内容
       .catch(function(reason){})   //有异常时执行回调      reason.response.data可以获取到响应内容
                                                         reason.message / reason.stack 可以查看错误的信息