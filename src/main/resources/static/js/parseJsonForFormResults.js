var size = parseInt(document.getElementById("formResultSizeInput").value);
for(var i = 1;i <= size;i++){
    var td = document.getElementById("formResultMap"+i);
    if(td){
        var txt = td.innerHTML;
        txt = JSON.stringify(txt,null,4);
        td.innerHTML=txt;
    }
}