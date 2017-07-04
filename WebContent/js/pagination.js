/**
 * 
 */
/**********分页操作**********/
var pageSize = 5;    //每页显示的记录条数
var curPage=0;        //当前页
var lastPage;        //最后页
var direct=0;        //方向
var len;            //总行数
var page;            //总页数
var begin;
var end;

                
$(document).ready(function display(){   
	len =$("#tblSort tr").length - 1;    // 求这个表的总行数，剔除第一行介绍
    page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
    // alert("page==="+page);
    var cur=document.getElementById("curPage");
    if(cur!=null&&cur.value!=null)
    	curPage=cur.value;
    else
    	curPage=1;    // 设置当前为第一页
    displayPage(1);//显示第一页

    document.getElementById("btn0").innerHTML="当前 " + curPage + "/" + page + " 页  "/*"  每页 "*/;    // 显示当前多少页   
    if(document.getElementById("curPage")!=null)
    	document.getElementById("curPage").value=curPage;
    document.getElementById("sjzl").innerHTML="数据总量 " + len + "";        // 显示数据量
    document.getElementById("pageSize").value = pageSize;

    $("#btn1").click(function firstPage(){    // 首页
    	curPage=1;
        direct = 0;
        displayPage();
    });
    $("#btn2").click(function frontPage(){    // 上一页
        direct=-1;
        displayPage();
    });
    $("#btn3").click(function nextPage(){    // 下一页
        direct=1;
        displayPage();
    });
    $("#btn4").click(function lastPage(){    // 尾页
        curPage=page;
        direct = 0;
        displayPage();
    });
    $("#btn5").click(function changePage(){    // 转页
        curPage=document.getElementById("changePage").value * 1;
        if (!/^[1-9]\d*$/.test(curPage)){
        	alert("请输入正整数");
        	return ;
        }
        if (curPage > page) {
        	alert("超出数据页面");
        	return ;
        }
        direct = 0;
        displayPage();
    });

    
    $("#pageSizeSet").click(function setPageSize(){    // 设置每页显示多少条记录
        pageSize = document.getElementById("pageSize").value;    //每页显示的记录条数
        if (!/^[1-9]\d*$/.test(pageSize)) {
        	alert("请输入正整数");
        	return ;
        }
        len =$("#tblSort tr").length - 1;
        page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
        curPage=1;        //当前页
        direct=0;        //方向
        firstPage();
    });
});

function displayPage(){
    if(curPage <=1 && direct==-1){
        direct=0;
        alert("已经是第一页了");
        return;
    } else if (curPage >= page && direct==1) {
        direct=0;
        alert("已经是最后一页了");
        return ;
    }

    lastPage = curPage;

    // 修复当len=1时，curPage计算得0的bug
    if (len > pageSize) {
        curPage = ((curPage + direct + len) % len);
    } else {
        curPage = 1;
    }

    
    document.getElementById("btn0").innerHTML="当前 " + curPage + "/" + page + " 页"/*"  每页 "*/;        // 显示当前多少页
    if(document.getElementById("curPage")!=null)
    	document.getElementById("curPage").value=curPage;
    begin=(curPage-1)*pageSize + 1;// 起始记录号
    end = begin + 1*pageSize - 1;    // 末尾记录号

    
    if(end > len ) end=len;
    $("#tblSort tr").hide();    // 首先，设置这行为隐藏
    $("#tblSort tr").each(function(i){    // 然后，通过条件判断决定本行是否恢复显示
        if((i>=begin && i<=end) || i==0 )//显示begin<=x<=end的记录
        	$(this).show();
    });
 }
/***********************************************************/

/**********分页操作2**********/
var pageSize2 = 10;    //每页显示的记录条数
var curPage2=0;        //当前页
var lastPage2;        //最后页
var direct2=0;        //方向
var len2;            //总行数
var page2;            //总页数
var begin2;
var end2;

                
$(document).ready(function display2(){   
	len2 =$("#tblSort2 tr").length - 1;    // 求这个表的总行数，剔除第一行介绍
    page2=len2 % pageSize2==0 ? len2/pageSize2 : Math.floor(len2/pageSize2)+1;//根据记录条数，计算页数
    // alert("page==="+page2);
    curPage2=1;    // 设置当前为第一页
    displayPage2(1);//显示第一页

    document.getElementById("btn00").innerHTML="当前 " + curPage2 + "/" + page2 + " 页    每页 ";    // 显示当前多少页
    document.getElementById("sjzl2").innerHTML="数据总量 " + len2 + "";        // 显示数据量
    document.getElementById("pageSize2").value = pageSize2;

    $("#btn11").click(function firstPage2(){    // 首页
    	curPage2=1;
        direct2 = 0;
        displayPage2();
    });
    $("#btn22").click(function frontPage2(){    // 上一页
        direct2=-1;
        displayPage2();
    });
    $("#btn33").click(function nextPage2(){    // 下一页
        direct2=1;
        displayPage2();
    });
    $("#btn44").click(function lastPage2(){    // 尾页
        curPage2=page2;
        direct2 = 0;
        displayPage2();
    });
    $("#btn55").click(function changePage2(){    // 转页
        curPage2=document.getElementById("changePage2").value * 1;
        if (!/^[1-9]\d*$/.test(curPage2)){
        	alert("请输入正整数");
        	return ;
        }
        if (curPage2 > page2) {
        	alert("超出数据页面");
        	return ;
        }
        direct2 = 0;
        displayPage2();
    });

    
    $("#pageSizeSet2").click(function setPageSize2(){    // 设置每页显示多少条记录
        pageSize2 = document.getElementById("pageSize2").value;    //每页显示的记录条数
        if (!/^[1-9]\d*$/.test(pageSize2)) {
        	alert("请输入正整数");
        	return ;
        }
        len2 =$("#tblSort2 tr").length2 - 1;
        page2=len2 % pageSize2==0 ? len2/pageSize2 : Math.floor(len2/pageSize2)+1;//根据记录条数，计算页数
        curPage2=1;        //当前页
        direct2=0;        //方向
        firstPage2();
    });
});

function displayPage2(){
    if(curPage2 <=1 && direct2==-1){
        direct2=0;
        alert("已经是第一页了");
        return;
    } else if (curPage2 >= page2 && direct2==1) {
        direct2=0;
        alert("已经是最后一页了");
        return ;
    }

    lastPage2 = curPage2;

    // 修复当len=1时，curPage计算得0的bug
    if (len2 > pageSize2) {
        curPage2 = ((curPage2 + direct2 + len2) % len2);
    } else {
        curPage2 = 1;
    }

    
    document.getElementById("btn00").innerHTML="当前 " + curPage2 + "/" + page2 + " 页    每页 ";        // 显示当前多少页

    begin2=(curPage2-1)*pageSize2 + 1;// 起始记录号
    end2 = begin2 + 1*pageSize2 - 1;    // 末尾记录号

    
    if(end2 > len2 ) end2=len2;
    $("#tblSort2 tr").hide();    // 首先，设置这行为隐藏
    $("#tblSort2 tr").each(function(i){    // 然后，通过条件判断决定本行是否恢复显示
        if((i>=begin2 && i<=end2) || i==0 )//显示begin<=x<=end的记录
        	$(this).show();
    });
 }
/***********************************************************/