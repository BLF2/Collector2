var indexMax = 100;
var map = [];
for(var i = 1;i <= indexMax;i++){
    var ele = document.getElementById("forceValueItem"+i);
    if(!ele)
        break;
    map[ele.name] = ele.value;
}
var indexDivTr = {};
function fillValueByKey(ele) {
    var id = ele.parentNode.parentNode.id;
    var index = ele.selectedIndex;
    var sel = ele.options[index].value;
    var fillValue = document.getElementById(id+"FillValue");
    fillValue.removeAttribute("readonly");
    fillValue.setAttribute("value", map[sel]);
    if(sel != '其它') {
        fillValue.setAttribute("readonly", "readonly");
    }
}

function addNewForceKeyValue(ele){
    var id = ele.parentNode.parentNode.parentNode.parentNode.parentNode.id;
    if(!indexDivTr[id])
        indexDivTr[id] = 2;
    var td1 = document.createElement("td");
    td1.setAttribute("class","form-group");
    td1.innerHTML = document.getElementById(id+"Tr1Td1").innerHTML;

    var inputFill = document.createElement("input");
    inputFill.setAttribute("type","text");
    inputFill.setAttribute("class","form-control");
    inputFill.setAttribute("readonly","readonly");
    inputFill.setAttribute("id",id+"Tr"+indexDivTr[id]+"FillValue");
    var td2 = document.createElement("td");
    td2.setAttribute("class","form-group");
    td2.appendChild(inputFill);

    var inputReal = document.createElement("input");
    inputReal.setAttribute("type","text");
    inputReal.setAttribute("class","form-control");
    inputReal.setAttribute("id",id+"Tr"+indexDivTr[id]+"RealValue");
    var td3 = document.createElement("td");
    td3.setAttribute("class","form-group");
    td3.appendChild(inputReal);

    var td4 = document.createElement("td");
    td4.innerHTML = document.getElementById(id+"Tr1Td4").innerHTML;

    var newTr = document.createElement("tr");
    newTr.setAttribute("id",id+"Tr"+indexDivTr[id]);
    indexDivTr[id]++;
    newTr.appendChild(td1);
    newTr.appendChild(td2);
    newTr.appendChild(td3);
    newTr.appendChild(td4);

    var table = document.getElementById(id+'tbody');
    table.appendChild(newTr);

}

function collectInfo() {
    var names = ['forceKeyValueItemNameText','forceKeyValueItemNameDate','forceKeyValueItemNameSingle',
        'forceKeyValueItemNameMultiple','forceKeyValueDiv'];
    var data = {};
    for(var i =1; i<= indexMax;i++){
        var flag = false;
        for(var j = 0;j < 5;j++){
            var name = names[j] + i;
            if(j == 3)
                name += '0';
            var item = document.getElementById(name);
            if(item) {
                flag = true;
                if (name.indexOf("Text") >= 0 || name.indexOf('Date') >= 0) {
                    data[item.name] = item.value;
                    break;
                }
                else if (name.indexOf("Single") >= 0) {
                    var indexItem = item.selectedIndex;
                    data[item.name] = item.options[indexItem].text;
                    break;
                } else if (name.indexOf("Multiple") >= 0) {
                    var itemFb = document.getElementsByName(item.name);
                    var valueArr = new Array();
                    for(var k in itemFb){
                        if(itemFb[k].checked){
                            valueArr.push(itemFb[k].value);
                        }
                    }
                    data[item.name]=valueArr.join(",");
                    break;
                }else if(name.indexOf("forceKeyValueDiv") >= 0){
                    var sum = 0;
                    var isAllNum = true;
                    var dataKey = document.getElementById(name+"TemplateName").value;
                    var dataValue = "";
                    for(var k = 1;k < indexDivTr[name]?indexDivTr[name]:20;k++){
                        var realKey = null;
                        var realValue = null;
                        if(document.getElementById(name+"Tr"+k+"RealValue"))
                            realKey = document.getElementById(name+"Tr"+k+"RealValue").value;
                        if(document.getElementById(name+"Tr"+k+"FillValue"))
                            realValue = document.getElementById(name+"Tr"+k+"FillValue").value;
                        if(!realKey && !realValue)
                            break;
                        if(realKey && realValue){
                            dataValue += (realKey + " " + realValue + ";");
                            if(!isNaN(realValue)){
                                sum += parseFloat(realValue);
                            }else{
                                isAllNum = false;
                            }
                        }
                    }
                    data[dataKey] = dataValue;
                    if(isAllNum)
                        data["总计 "] = sum;
                    break;
                }
            }
        }
        if(!flag)
            break;
    }
    var dataJson = JSON.stringify(data);
    var inputForData = document.getElementById("collectedData");
    var deData = encodeURIComponent(dataJson);
    inputForData.value = deData;
}
function selectAll() {
    var selectAllInput = document.getElementById("selectUserAll");
    var checkedForAll = false;
    if(selectAllInput.checked == true){
       checkedForAll = true
    }
    else{
        checkedForAll = false;
    }
    var maxIndex = parseInt(document.getElementById("userInfoListSize").value);
    for(var i = 1;i <= maxIndex;i++){
        var selectUser = document.getElementById("selectUser"+i);
        if(checkedForAll)
            selectUser.checked = true;
        else
            selectUser.checked = false;
    }
}