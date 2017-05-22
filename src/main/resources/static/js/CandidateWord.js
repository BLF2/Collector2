index = 1;
flag = false;
function addNewHxxForSingel() {
    if(!checkExistsForhxx())
        addNewHouxuanxiang();
}
function checkExistsForhxx() {
    if(!flag){
        flag = true;
        return false;
    }
    for(var i = 1; i < index;i++){
        var checkDiv = document.getElementById("houxuanxiang"+i);
        if(checkDiv != undefined)
            return true;
    }
    return false;
}
function addNewHouxuanxiang() {
    var houxuanxiangwz = document.getElementById("houxuanxiangwz");

    var newP = document.createElement("p");
    newP.innerHTML="候选项：";
    var newInput = document.createElement("input");
    newInput.setAttribute("class","form-control");
    newInput.setAttribute("type","text");
    newInput.setAttribute("name","choices"+index);
    newInput.setAttribute("id","houxuanValue"+index);
    <!-- 增加按钮 -->
    var newSpanForAdd = document.createElement("span");
    newSpanForAdd.setAttribute("class","glyphicon glyphicon-plus");
    newSpanForAdd.setAttribute("aria-hidden","true");
    newSpanForAdd.innerHTML="增加一项";
    var newButtonForAdd = document.createElement("button");
    newButtonForAdd.setAttribute("type","button");
    newButtonForAdd.setAttribute("class","btn btn-primary btn-sm");
    newButtonForAdd.setAttribute("onclick","addNewHouxuanxiang()");
    newButtonForAdd.appendChild(newSpanForAdd);
    <!-- 删除按钮 -->
    var newSpanForDel = document.createElement("span");
    newSpanForDel.setAttribute("class","glyphicon glyphicon-trash");
    newSpanForDel.setAttribute("aria-hidden","true");
    newSpanForDel.innerHTML="删除本项";
    var newButtonForDel = document.createElement("button");
    newButtonForDel.setAttribute("type","button");
    newButtonForDel.setAttribute("class","btn btn-danger btn-sm");
    var delHxxId = "houxuanxiang"+index;
    newButtonForDel.setAttribute("onclick","delHouxuanxiang('"+delHxxId+"')");
    newButtonForDel.appendChild(newSpanForDel);

    var newDiv = document.createElement("div");
    newDiv.appendChild(newP);
    newDiv.appendChild(newInput);
    newDiv.appendChild(newButtonForAdd);
    newDiv.appendChild(newButtonForDel);
    newDiv.setAttribute("id","houxuanxiang"+index);
    houxuanxiangwz.appendChild(newDiv);
    index++;
}
function delHouxuanxiang(id) {
    var delDiv = document.getElementById(id);
    if(delDiv){
        delDiv.parentNode.removeChild(delDiv);
    }
}