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
<title>预知愿——个人中心</title>
<style>
/*
div{
border:1px solid red;
}*/
td,th{
text-align:center;
}
</style>

</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		    <h1 class="text-center" style="margin-top:50px;font-size:30px">
				系统通知
			</h1>
		    <div style="margin:0 auto;width:600px;margin-top:20px">
		    <form   id="f1" name="f1">
		    <table style="font-size:15px;text-align:center" id="tblSort" sortCol="-1" class="table">
					<thead>
					<tr>
					<th style="width:50px;text-align:center;cursor:pointer" onclick="sortTable('tblSort',0,'date')">通知内容</th>
					<th style="width:100px;text-align:center;cursor:pointer" onclick="sortTable('tblSort',1,'string')">时间</th>
					<th style="width:50px;text-align:center;cursor:pointer" onclick="sortTable('tblSort',2,'string')">状态</th>
					</tr>
					</thead>
					<tbody>					
					<c:forEach items="${requestScope.msgList}" var="msg">
					<tr id="${msg.mId }">
					
					<c:if test="${msg.isRead==0 }">
					<th>
					</c:if>
					<c:if test="${msg.isRead==1 }">
					<td>
					</c:if>
					<div id="modal-${msg.mId }">
					<div>
					<a id="modal-87433" href="#" role="button" style="display:block"
					class="btn" onclick="openMask(${msg.mId })">
					<%--截取部分内容，未实现 --%>
					<span id="pc-${msg.mId }">
					
					</span>
					</a>
					<script>
					var thisMId="${msg.mId}";
					var thisContext="${msg.context }";
					
					var partContext=thisContext.substring(0,10);
					if(thisContext.length>30)
					    $('#pc-'+thisMId).html(partContext+'...');
					else{
						$('#pc-'+thisMId).html(partContext);
					}
					</script>
			
			<div id="modal-container-87433-${msg.mId }" style="background:rgba(96,96,96,0.5);display:none"
			class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">
								系统通知内容
							</h4>
						</div>
						<div class="modal-body">
						    ${msg.time }
						    <br/>
							${msg.context }
							<input type="hidden" value="${msg.context }" id="context-${msg.mId }"/>
					        <input type="hidden" value="${msg.time }" id="time-${msg.mId }"/>
							
						</div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-default" onclick="closeMask(${msg.mId })">关闭</button>
						</div>
					</div>
					
				</div>
				
			</div>
			       </div>
			
					
			
			
			        <c:if test="${msg.isRead==1 }">
					</td>
					</c:if>
					<c:if test="${msg.isRead==0 }">
					</th>
					</c:if>
			        
					
					<c:if test="${msg.isRead==0 }">
					<th>${msg.time }</th>
					</c:if>
					<c:if test="${msg.isRead==1 }">
					<td>${msg.time }</td>
					</c:if>
					
					<c:if test="${msg.isRead==1 }">
					<td>已读</td>
					</c:if>
					<c:if test="${msg.isRead==0 }">
					<th>未读</th>
					
					</c:if>
					</tr>
					</c:forEach>
					</tbody>
					<tfoot >  
            <tr align="center">  
            <td colspan="5">  
                <jsp:include page="/jsp/paging_footer.jsp"></jsp:include>  
            </td>  
            </tr>  
            </tfoot>
			</table>
			  
            <form>   
		    </div>
		</div>
	</div>
</div>
<script type="text/javascript">
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
function openMask(mId){

	var modal=document.getElementById('modal-container-87433-'+mId);
	modal.style.display='block';
	$.ajax({
		type:'post',
		url:'/yzwish/userInfo/readMessage',
		contentType:'application/x-www-form-urlencoded',
		data:{
			mId:mId
		},	
		async: false,
		beforeSend:function(){										
		},										
	    success:function(data){

	    },
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("未知错误");
	    }
	}
			);
}
function closeMask(mId){
	var modal=document.getElementById('modal-container-87433-'+mId);
	modal.style.display='none';
	//设置th为tr
	//var context=$('#context-'+mId).val();
	//var time=$('#time-'+mId).val();
	//var modal=$('#modal-'+mId).html();
	//$('#'+mId).html('<td><div id="modal-'+mId+'">'+modal+'</div></td><td>'+time+'</td><td>已读</td>');
	location.reload(true);   
}
</script>
</body>

</html>