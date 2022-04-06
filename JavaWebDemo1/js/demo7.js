

//当鼠标悬停时，显示背景颜色
function showBGColor(){
    //event:当前发生的事件
    //event.srcElement:事件源
    // alert(event.srcElement);
    // alert(event.srcElement.tagName);=>TD
    if (event && event.srcElement && event.srcElement.tagName=="TD"){
        var td = event.srcElement;
        //获取td的父元素  ->tr
        var tr = td.parentElement;
        //如果想要通过js代码设置某节点的样式，则需要加上.style
        tr.style.backgroundColor = "navy ";

        //获取这个tr中的所有单元格
        var tds = tr.cells;
        for (var i = 0;i <tds.length;i++){
            tds[i].style.color = "white";
        }
    }
}

//当鼠标离开时
function clearBGColor() {
    if (event && event.srcElement && event.srcElement.tagName=="TD"){
        var td = event.srcElement;
        var tr = event.parentElement;
        tr.style.backgroundColor = "transparent";
        var tds = tr.cells;
        for (var i = 0;i < tds.length;i++){
            tds[i].style.color = "threeddarkshadow";
        }
    }
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand(){
    if (event && event.srcElement && event.srcElement.tagName=="TD"){
        var td = event.srcElement;
        //cursor：光标
        td.style.cursor="pointer";
    }
}