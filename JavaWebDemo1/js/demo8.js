function $(id){
    return document.getElementById(id);
}
window.onload = function () {
    updateZJ();
    //当页面加载完成，我们需要绑定各种事件
    //根据id获取到fruit表格
    const fruitTbl = $("tbl_fruit");
    //获取表格中的所有的行
    const rows = fruitTbl.rows;
    for (let i = 1; i < rows.length-1; i++) {
        const tr = rows[i];
        trBindEvent(tr);
    }
    $("addbtn").onclick=addFruit;
}

function trBindEvent(tr){
    //绑定鼠标悬浮以及离开设置背景颜色事件
    tr.onmouseover = showBGColor;
    tr.onmouseout = clearBGColor();
    //获取tr这一行的所有单元格
    const cells = tr.cells;
    const priceTD = cells[1];
    //绑定鼠标悬浮在单价单元格变手势的事件
    priceTD.onmouseover = showHand;
    //绑定鼠标单价单元格的事件
    priceTD.onclick = editPrice;

    //绑定删除小图标的点击事件
    var img = cells[4].firstChild;
    if (img && img.tagName=="IMG"){
        //绑定单击事件
        img.onclick = delFruit;

    }
}
//添加水果信息
function addFruit() {
    let fname = $("fname").value;
    let price = parseInt($("price").value);
    let fcount = parseInt($("fcount").value);
    var xj = price * fcount;

    var fruitTbl = $("tbl_fruit");
    var tr = fruitTbl.insertRow(fruitTbl.rows.length-1);
    var fnameTD = tr.insertCell();
    fnameTD.innerText = fname;

    var priceTD = tr.insertCell();
    priceTD.innerText = price;

    var fcountTD = tr.insertCell();
    fcountTD.innerText = fcount;

    var xjTD = tr.insertCell();
    xjTD.innerText = xj;

    var imgTD = tr.insertCell();
    imgTD.innerHTML = "<img src = 'imgs/del.webp' class='delImg' />"

    updateZJ();

    trBindEvent(tr);
}
function delFruit(){
    if (event && event.srcElement && event.srcElement.tagName=="IMG"){
        //alert表示弹出一个对话框，只有确定按钮
        //confirm表示弹出一个对话框，只有确定和取消。当点击确定，返回true,否则返回false
        if (window.confirm("是否确认删除当前库存记录")){
            var img = event.srcElement;
            var tr = img.parentElement.parentElement;
            var fruitTbl = $("tbl_fruit");
            fruitTbl.deleteRow(tr.rowIndex);

            updateZJ();
        }

    }
}
//单鼠点击单价单元格时进行价格编辑
function editPrice() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var priceTD = event.srcElement;
        //目的是判断当前priceTD有子节点，而且第一个子节点是文本节点，TextNode对应的是3 ELementNode对应的是1
        if (priceTD.firstChild && priceTD.firstChild.nodeType == 3) {
            //innerText表示设置或者获取当前节点的内部文本
            var oldPrince = priceTD.innerText;
            //innerHTML表示设置当前节点的内部HTML
            priceTD.innerHTML = "<input type='text' size='4'/>";
            var input = priceTD.firstChild;
            if (input.tagName == "INPUT") {
                input.value = oldPrince;
                //选中输入框内部的文本
                input.select();
                //绑定输入框失去焦点事件，失去焦点，更新单价
                input.onblur=updatePrice;
                //在输入框上绑定键盘按下的事件，此处我们需要保证用户输入的是数字
                input.onkeydown=ckinput;
            }
        }
    }
}

function ckinput(){
    let kc = event.keyCode;
    //backspace:8
    //enter:13
    // console.log(kc);
    if (!((kc >= 48 && kc <= 57) || kc == 8 || kc == 13)){
        event.returnValue=false;
    }

    if (kc == 13){
        event.srcElement.blur();
    }
}

function updatePrice() {
    if (event && event.srcElement && event.srcElement.tagName=="INPUT"){
        var input = event.srcElement;
        var newPrice = input.value;
        //input节点
        var priceTD = input.parentElement;
        priceTD.innerText = newPrice;

        //更新当前行的小计的这一个格子的值
        updateXJ(priceTD.parentElement);
    }
}

function updateXJ(tr){
    if (tr && tr.tagName=="TR"){
        var tds = tr.cells;
        var price = tds[1].innerText;
        var count = tds[2].innerText;

        var xj = parseInt(price) * parseInt(count)
        tds[3].innerText = xj;
        //更新总计
        updateZJ();
    }
}

//更新总计
function updateZJ(){
    var fruitTbl = $("tbl_fruit");
    var rows = fruitTbl.rows;
    var sum = 0;
    for (var i = 1;i < rows.length-1;i++){
        var tr = rows[i];
        var xj = parseInt(tr.cells[3].innerText);
        sum = sum + xj;

    }
    rows[rows.length-1].cells[1].innerText = sum;
}

//当鼠标悬停时，显示背景颜色
function showBGColor() {
    //event:当前发生的事件
    //event.srcElement:事件源
    // alert(event.srcElement);
    // alert(event.srcElement.tagName);=>TD
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        const td = event.srcElement;
        //获取td的父元素  ->tr
        const tr = td.parentElement;
        //如果想要通过js代码设置某节点的样式，则需要加上.style
        tr.style.backgroundColor = "green ";

        //获取这个tr中的所有单元格
        const tds = tr.cells;
        for (let i = 0; i < tds.length; i++) {
            tds[i].style.color = "white";
        }
    }
}

//当鼠标离开时
function clearBGColor() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        const td = event.srcElement;
        const tr = td.parentElement;
        tr.style.backgroundColor = "transparent";
        const tds = tr.cells;
        for (let i = 0; i < tds.length; i++) {
            tds[i].style.color = "black";
        }
    }
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        const td = event.srcElement;
        //cursor：光标
        td.style.cursor = "pointer";
    }
}