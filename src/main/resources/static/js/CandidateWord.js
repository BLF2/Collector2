var indexForKeyValue = 1;
function chooseToSelect(ele) {
    var index = ele.selectedIndex;
    var sel = ele.options[index].value;
    if(sel == 'text' || sel == 'date')
        deleteInputForValueCondition(ele);
    else if(sel == 'single' || sel == 'multiple')
        addInputForValueCondition(ele);
    else if(sel == 'forceKeyValue')
        addTextAreaForValue(ele);

}
function deleteInputForValueCondition(ele) {
    var id = ele.parentNode.parentNode.id;
    var isExistsDiv = document.getElementById("valueCondition"+id);
    if(isExistsDiv){
        isExistsDiv.parentNode.removeChild(isExistsDiv);
    }
}
function addInputForValueCondition(ele) {
    var id = ele.parentNode.parentNode.id;
    var isExistsDiv = document.getElementById("valueCondition"+id);
    var isExistsInput = document.getElementById("valueCondition"+id+"input");
    var pele = document.getElementById(id);
    if(isExistsDiv && !isExistsInput){
        deleteInputForValueCondition(ele);
    }else if(isExistsDiv && isExistsInput)
        return ;
    var div = document.createElement("div");
    div.setAttribute("class","form-group");
    div.setAttribute("id","valueCondition"+id);
    var input = document.createElement("input");
    input.setAttribute("type","text");
    input.setAttribute("name","infoTemplateList["+(indexForKeyValue-1)+"].conditionValue");
    input.setAttribute("placeholder","多个项目请用英文逗号隔开");
    input.setAttribute("class","form-control");
    input.setAttribute("id","valueCondition"+id+"input");
    div.appendChild(input);
    pele.appendChild(div);
}

function addPrimaryIterm() {
    //start div项目名
    var lable1 = document.createElement("label");
    lable1.innerHTML="项目名：";
    var buttonForDel = document.createElement("button");
    buttonForDel.setAttribute("type","button");
    buttonForDel.setAttribute("class","btn btn-danger btn-sm");
    buttonForDel.setAttribute("onclick","deleteThisItem(this)");
    var spanForDel = document.createElement("span");
    spanForDel.setAttribute("class","glyphicon glyphicon-trash");
    spanForDel.setAttribute("aria-hidden","true");
    buttonForDel.appendChild(spanForDel);
    var input1 = document.createElement("input");
    input1.setAttribute("type","text");
    input1.setAttribute("class","form-control");
    input1.setAttribute("name","infoTemplateList["+indexForKeyValue+"].itermName");
    input1.setAttribute("placeholder","项目名");
    var div1 = document.createElement("div");
    div1.setAttribute("class","form-group");
    div1.appendChild(lable1);
    div1.appendChild(buttonForDel);
    div1.appendChild(input1);
    //end
    //start div 项目约束
    var label2 = document.createElement("label");
    var input2 = document.createElement("input");
    input2.setAttribute("type","checkbox");
    input2.setAttribute("name","infoTemplateList["+indexForKeyValue+"].itermCondition");
    input2.setAttribute("value","RequiredInformation");
    var divForStr1 = document.createElement("div");
    divForStr1.innerHTML="必填（默认为选填）";
    label2.appendChild(input2);
    label2.appendChild(divForStr1);
    var span1 = document.createElement("span");
    span1.innerHTML ="项目约束";
    var div2 = document.createElement("div");
    div2.setAttribute("class","checkbox");
    div2.appendChild(span1);
    div2.appendChild(label2);
    //end
    //start 值约束
    var span2 = document.createElement("span");
    span2.innerHTML="值约束：";
    var select1 = document.createElement("select");
    select1.setAttribute("name","infoTemplateList["+indexForKeyValue+"].valueCondition");
    select1.setAttribute("onchange","chooseToSelect(this)");
    var option1 = document.createElement("option");
    option1.setAttribute("selected","selected");
    option1.setAttribute("value","text");
    option1.innerHTML="文本";
    var option2 = document.createElement("option");
    option2.setAttribute("value","date");
    option2.innerHTML="日期";
    var option3 = document.createElement("option");
    option3.setAttribute("value","single");
    option3.innerHTML="单选";
    var option4 = document.createElement("option");
    option4.setAttribute("value","multiple");
    option4.innerHTML="多选";
    var option5 = document.createElement("option");
    option5.setAttribute("value","forceKeyValue");
    option5.innerHTML="键值对";
    select1.appendChild(option1);
    select1.appendChild(option2);
    select1.appendChild(option3);
    select1.appendChild(option4);
    select1.appendChild(option5);
    var divForValueCondition = document.createElement("div");
    divForValueCondition.setAttribute("class","select");
    divForValueCondition.appendChild(span2);
    divForValueCondition.appendChild(select1);
    //end
    //一个具体项目
    var parent = document.getElementById("itemDiv0").parentNode;
    var itemDiv = document.createElement("div");
    itemDiv.appendChild(document.createElement("hr"));
    itemDiv.setAttribute("id","itemDiv"+indexForKeyValue);
    indexForKeyValue++;
    itemDiv.appendChild(div1);
    itemDiv.appendChild(div2);
    itemDiv.appendChild(divForValueCondition);
    parent.appendChild(itemDiv);
}
function addTextAreaForValue(ele) {
    var id = ele.parentNode.parentNode.id;
    var isExistsDiv = document.getElementById("valueCondition"+id);
    var isExistsTextarea = document.getElementById("valueCondition"+id+"textarea");
    var pele = document.getElementById(id);
    if(isExistsDiv && !isExistsTextarea){
        deleteInputForValueCondition(ele);
    }else if(isExistsDiv && isExistsTextarea)
        return ;
    var div = document.createElement("div");
    div.setAttribute("class","form-group");
    div.setAttribute("id","valueCondition"+id);
    var textarea = document.createElement("textarea");
    textarea.setAttribute("class","form-control");
    textarea.setAttribute("rows","3");
    textarea.setAttribute("placeholder","键和值中间用#隔开，多个键值对用英文逗号隔开");
    textarea.setAttribute("id","valueCondition"+id+"textarea")
    textarea.setAttribute("name","infoTemplateList["+(indexForKeyValue-1)+"].conditionValue");
    div.appendChild(textarea);
    pele.appendChild(div);
}
function deleteThisItem(ele) {
    var id = ele.parentNode.parentNode.id;
    var delEle = document.getElementById(id);
    delEle.parentNode.removeChild(delEle);
}