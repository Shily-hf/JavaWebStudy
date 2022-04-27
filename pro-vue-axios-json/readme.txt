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

    什么是JSON
        JSON是一种数据格式
        XML也是一种数据格式

        XML格式表示两个学院信息的代码如下
        <students>
            <student sid="s001">
                <sname>jim</sname>
                <age>18</age>
            </student>
             <student sid="s002">
                 <sname>tom</sname>
                 <age>19</age>
            </student>
        </students>

        JSON格式表示两个学院信息代码如下：
        [{sid:"s001",age:18},{sid:"s002",age:19}]
        -JSON表达数据更简洁，更能够节约网络带宽
        -客户端发送JSON格式的数据给服务器端
        1）客户端中param需要修改成：data:
        2）服务器获取参数值不再是 request.getParamter().....
            而是
             StringBuffer stringBuffer = new StringBuffer("");
             BufferedReader bufferedReader = request.getReader();
             String str = null;
             while ((str=bufferedReader.readLine())!=null){
                    stringBuffer.append(str);
             }
             str =stringBuffer.toString();

        3)我们会发现str的内容如下
        {"uname":"lina","pwd":"ok"}

        -服务器端给客户端响应JSON格式的字符串，然后客户端需要将字符串转换成js object