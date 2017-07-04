<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/css/templatemo_style.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.zh-CN.js"></script>
<title>预知愿——个人中心</title>
<style>
/*
div{
border:1px solid red;
}*/
.left{float:left}
.clear{display:block;overflow:hidden;}
.datainp{ width:200px; height:30px; border:1px #ccc solid;}
.datep{ margin-bottom:40px;}
</style>
</head>
<body>
<script type="text/javascript">
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
                curPage=1;    // 设置当前为第一页
                displayPage(1);//显示第一页

                document.getElementById("btn0").innerHTML="当前 " + curPage + "/" + page + " 页    每页 ";    // 显示当前多少页
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
                    if (!/^[1-9]\d*$/.test(curPage)) {
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
                    len =$("#mytable tr").length - 1;
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

               
                document.getElementById("btn0").innerHTML="当前 " + curPage + "/" + page + " 页    每页 ";        // 显示当前多少页

                begin=(curPage-1)*pageSize + 1;// 起始记录号
                end = begin + 1*pageSize - 1;    // 末尾记录号

               
                if(end > len ) end=len;
                $("#mytable tr").hide();    // 首先，设置这行为隐藏
                $("#mytable tr").each(function(i){    // 然后，通过条件判断决定本行是否恢复显示
                    if((i>=begin && i<=end) || i==0 )//显示begin<=x<=end的记录
                        $(this).show();
                });

             }
</script>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		    <div style="margin-bottom:20px">
		    <h1 class="text-center">我的钱包</h1>
		    </div>
			<div class="row clearfix" style="width:1000px;margin:0 auto" >
				<div class="col-md-4 column" style="width:200px">
					<div class="panel-group" id="panel-976353">
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title" data-toggle="collapse" data-parent="#panel-976353" href="#panel-element-497330">虚拟币</a>
							</div>
							<div id="panel-element-497330" class="panel-collapse collapse in">
								<div class="panel-body">
									<a href="/yzwish/userInfo/showBalance">余额</a>
								</div>
								<div class="panel-body">
									<a href="/yzwish/jsp/deposite.jsp">充值</a>
								</div>
								<div class="panel-body"  style="background:rgba(92,192,222,0.2)">
									<a href="/yzwish/jsp/transRecoredInfo.jsp" >交易记录</a>
								</div>
								<c:if test="${sessionScope.duty=='3' }">
								<div class="panel-body">
									<a href="/yzwish/userInfo/showTodayLimit">提取</a>
								</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8 column" style="margin-left:20px">
					
					<div>
					<form method="post" action="/yzwish/userInfo/showTR"
					onSubmit="return checkTime()">
					<table >
					<tr>
					<th>选择时间</th>
					<th>方式</th>
					<th></th>
					</tr>
					
					<tbody>
					
					<tr>
					
					<td >
					<div class="clear" style="margin-right:10px">
					<div class="left" style="margin-right:10px">
                    <input class="date-picker form-control" id="from" name="from" value="${requestScope.from }"/>
				    </div>
				    <div class="left" style="margin-right:10px"><span style="font-size:15px">至</span></div>
					<div class="left">
                    <input class="date-picker form-control" id="to" name="to" value="${requestScope.to }"/>
				    </div>
					</div>
					</td>
					
					<td>
					<div  style="margin-right:10px">
					<select class="form-control" id="way" name="way">
					<option value="1" selected="selected">充值</option>
					<option value="2">奖励</option>
					<option value="3">提取</option>
					<option value="4">活动消费</option>
					<option value="5">活动所得</option>
					</select>
					</div>
					</td>
					
					<td>
					<button type="submit" id="btn" class="btn btn-lg btn-info" 
					style="font-size:15px;width:50px;padding:10px"
					>查询</button>
					</td>
					</tr>
					
					</tbody>
					</table>
					</form>
					</div>
					
					<div style="margin-top:20px">
					<h4>查询结果</h4>
					<div>
					<form   id="f1" name="f1">
					<table style="font-size:15px" id="tblSort" sortCol="-1" class="table">
					<thead>
					<tr>
					<th style="width:200px;cursor:pointer" onclick="sortTable('tblSort',0,'date')">时间</th>
					<th style="width:200px;cursor:pointer" onclick="sortTable('tblSort',1,'string')">虚拟币</th>
					<th style="width:200px" >方式</th>
					</tr>
					</thead>
					
					<tbody>					
					<c:forEach items="${requestScope.trList}" var="tr">
					<tr>
					<td>${tr.time }</td>
					<td>${tr.virtualNum }</td>
					<c:if test="${tr.way==1 }">
					<td>充值</td>
					</c:if>
					<c:if test="${tr.way==2 }">
					<td>奖励</td>
					</c:if>
					<c:if test="${tr.way==3 }">
					<td>提取</td>
					</c:if>
					<c:if test="${tr.way==4 }">
					<td>活动消费</td>
					</c:if>
					<c:if test="${tr.way==5 }">
					<td>活动所得</td>
					</c:if>
					</tr>
					</c:forEach>
					</tbody>
					</table>
					 <div style="margin-top:10px">
                <a id="btn0"></a>
<input id="pageSize" type="text" style="width:50px"
    size="1" maxlength="2" value="getDefaultValue()"/><a> 条 </a> <a href="#" id="pageSizeSet">设置</a> 
<a id="sjzl"></a> 
<a  href="#" id="btn1">首页</a>
<a  href="#" id="btn2">上一页</a>
<a  href="#" id="btn3">下一页</a>
<a  href="#" id="btn4">尾页</a> 
<a>转到 </a>
<input id="changePage" type="text" style="width:50px" size="1" maxlength="4"/>
<a>页 </a>
<a  href="#" id="btn5">跳转</a>
                                
                 </div>
					</form>
					</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('.date-picker').datepicker({
    language: 'zh-CN',
    autoclose: true,
    todayHighlight: true
})
function checkTime(){
	var from=$('#from').val();
	var to=$('#to').val();
	if(from==''){
		alert("请选择起止时间！");
		return false;
	}
	else if(to==''){
		alert("请选择结束时间！");
		return false;
	}
	else if(from.localeCompare(to)>0){
		alert("结束时间小于起止时间");
		return false;
	}	
}
window.onload=function(){
	var way="${requestScope.way}";

	if(way==null||way==''){
		$('#way').val("1");
		
	}	
	else{
		
		$('#way').val(way);
	}
		
}
/**************************************************************************
排序的主方法，有三个形参，STableTd,iCol,sDataType分别为需要排序的表格ID，
需要排序的表格列号，所在列的数据类型（支持int,float,date,string四种数据类型)
**************************************************************************/
function sortTable(sTableId,iCol,sDataType){
var oTable=document.getElementById(sTableId);//获取表格的ID
var oTbody=oTable.tBodies[0]; //获取表格的tbody
var colDataRows=oTbody.rows; //获取tbody里的所有行的引用
var aTRs=new Array; //定义aTRs数组用于存放tbody里的行
for(var i=0;i<colDataRows.length;i++){//依次把所有行放如aTRs数组/
aTRs.push(colDataRows[i]);
}
/***********************************************************************
sortCol属性是额外给table添加的属性，用于作顺反两种顺序排序时的判断，区分
首次排序和后面的有序反转
************************************************************************/
if(oTable.sortCol==iCol){//非首次排序
aTRs.reverse();
}
else{ //首次排序
aTRs.sort(generateCompareTRs(iCol,sDataType));
}
var oFragment=document.createDocumentFragment();//创建文档碎片/
for(var i=0;i<aTRs.length;i++){ //把排序过的aTRs数组成员依次添加到文档碎片
oFragment.appendChild(aTRs[i]);
}
oTbody.appendChild(oFragment); //把文档碎片添加到tbody,完成排序后的显示更新
oTable.sortCol=iCol;//把当前列号赋值给sortCol,以此来区分首次排序和非首次排序,//sortCol的默认值为-1

};

//比较函数，用于两项之间的排序
function generateCompareTRs(iCol,sDataType){
return function compareTRs(oTR1,oTR2){
var vValue1=convert(oTR1.cells[iCol].firstChild.nodeValue,sDataType);
var vValue2=convert(oTR2.cells[iCol].firstChild.nodeValue,sDataType);
if(vValue1<vValue2){
return -1;
}
else if(vValue1>vValue2){
return 1;
}
else{
return 0;
}
};
};
//数据类型转换函数
function convert(sValue,sDataType){
switch(sDataType){
case "int":return parseInt(sValue);
case "float": return parseFloat(sValue);
case "date":return new Date(Date.parse(sValue));
default:return sValue.toString();
}
};
</script>
</body>
</html>