<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
   <!-- 样式 -->
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="../css/templatemo-style.css" rel="stylesheet" type="text/css">
   <link href="../css/main.css" rel="stylesheet" type="text/css">
   <!-- 日历 -->
   <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
   <!-- js -->
   <script src="../js/sort.js"></script><script src="../js/pagination.js"></script>
   <script>
   $( function(){
   $('#start').click(
	function(){
		var activity=new Object();
		activity.title=$("#title").val();
		activity.info=$("#info").val();
		activity.startTime=$("#startTime").val();
		activity.duration=$("#duration").val();
		activity.leastNumOfEnroll=$("#least").val();
		var info=JSON.stringify(activity);
		 $.ajax({
			type:'post',
			contentType:'application/json',
			url:'/yzwish/activity/act_add',
			data:info,											
			dataType:"json",
			beforeSend:function(){
			//处理发送之前的检查事务。
			if($('#title').val()==''||$('#info').val()==''||$('#startTime').val()==''){
					alert("请填写完整！");
					return false;
				}
			},										
		    success:function(data){
		    	//通过data.key值取出相应信息
		    	//发起活动失败
		    	if(data.msg=='false')									    	
		    		alert("发起失败！请重新尝试！");
		    	else if(data.msg=='wrong')
		    		alert("发起失败！您的用户状态异常！");
		    	//发起活动成功
		    	else if(data.msg=='success'){
		    		alert("发起成功！");
		    		post('/yzwish/activity/act_index');
		    	}
		    },
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
		    }
		});
	}
	);	
   });
	 function setCondition(ch1,ch2){
		 if(ch2!="unknown"){
			 document.getElementById('du').value=ch2;
		 } 
		 else{
			 ch2=document.getElementById('du').value;
		 }
		 if(ch1!="unknown"){
			 document.getElementById('st').value=ch1;
		 } 
		 else{
			 ch1=document.getElementById('st').value;
		 }
		 post('/yzwish/activity/act_index', {"duration":ch2,"sta":ch1});  
	 }
	 function post(URL, PARAMS) {        
		 var temp = document.createElement("form");        
		 temp.action = URL;        
		 temp.method = "POST";        
		 temp.style.display = "none";        
		 for (var x in PARAMS) {        
			 var opt = document.createElement("textarea");        
		     opt.name = x;        
		     opt.value = PARAMS[x];        
		     temp.appendChild(opt);        
		    }        
		 document.body.appendChild(temp);        
		 temp.submit();        
		 return temp; 
	}
	</script>
<title>名师解答</title>
</head>
<body class="templatemo-bg-gray">
<div class="container">
  <jsp:include page="up.jsp"/>
      <div id="back" style="height:1500px;background-color: rgb(248,248,248);" class="img-rounded">
		<div class="col-md-12 column">
			<div class="panel-group" id="panel-536478" style="margin-top:10px">
			    <!-- 筛选 -->
				<div class="panel panel-default" style="margin-bottom:20px;margin-top:20px">
					<div id="panel-element-606732" class="panel-collapse collapse in">
						<div class="panel-body">
						   <div class="list-group">
						    <label class="list-group-item active">活动时长</label>
						    <div class="list-group-item">
						     <c:if test="${duration==0}">
						       <a onClick="javascript:setCondition('unknown','0')" class="btn btn-sm btn-default">不限</a> 
						     </c:if>
						     <c:if test="${duration!=0}">
						       <a onClick="javascript:setCondition('unknown','0')" class="btn btn-sm">不限</a> 
						     </c:if>
						     <c:if test="${duration==1}">
						       <a onClick="javascript:setCondition('unknown','1')" class="btn btn-sm btn-default"><3小时</a>
						     </c:if>
						     <c:if test="${duration!=1}">
						       <a onClick="javascript:setCondition('unknown','1')" class="btn btn-sm"><3小时</a>
						     </c:if>
						     <c:if test="${duration==2}">
						       <a onClick="javascript:setCondition('unknown','2')" class="btn btn-sm btn-default">3~24小时</a> 
						     </c:if>
						     <c:if test="${duration!=2}">
						       <a onClick="javascript:setCondition('unknown','2')" class="btn btn-sm">3~24小时</a> 
						     </c:if>
						     <c:if test="${duration==3}">
						       <a onClick="javascript:setCondition('unknown','3')" class="btn btn-sm btn-default">>24小时</a>
						     </c:if>
						     <c:if test="${duration!=3}">
						       <a onClick="javascript:setCondition('unknown','3')" class="btn btn-sm">>24小时</a>
						     </c:if>
						     <input id="du" type="hidden" value="${duration}">
						    </div>
						   </div>
						   <div class="list-group">
						    <label class="list-group-item active">活动状态</label>
						    <div class="list-group-item">
						     <c:if test="${sta==0}">
						       <a onClick="javascript:setCondition('0','unknown')" class="btn btn-sm btn-default">不限</a> 
						     </c:if>
						     <c:if test="${sta!=0}">
						       <a onClick="javascript:setCondition('0','unknown')" class="btn btn-sm">不限</a> 
						     </c:if>
						     <c:if test="${sta==1}">
						       <a onClick="javascript:setCondition('1','unknown')" class="btn btn-sm btn-default">报名中</a> 
						     </c:if>
						     <c:if test="${sta!=1}">
						       <a onClick="javascript:setCondition('1','unknown')" class="btn btn-sm">报名中</a> 
						     </c:if>
						     <c:if test="${sta==2}">
						       <a onClick="javascript:setCondition('2','unknown')" class="btn btn-sm btn-default">举办中</a> 
						     </c:if>
						     <c:if test="${sta!=2}">
						       <a onClick="javascript:setCondition('2','unknown')" class="btn btn-sm">举办中</a> 
						     </c:if>
						     <c:if test="${sta==3}">
						       <a onClick="javascript:setCondition('3','unknown')" class="btn btn-sm btn-default">举办成功</a> 
						     </c:if>
						     <c:if test="${sta!=3}">
						       <a onClick="javascript:setCondition('3','unknown')" class="btn btn-sm">举办成功</a> 
						     </c:if>
						     <c:if test="${sta==4}">
						       <a onClick="javascript:setCondition('4','unknown')" class="btn btn-sm btn-default">已取消</a> 
						     </c:if>
						     <c:if test="${sta!=4}">
						       <a onClick="javascript:setCondition('4','unknown')" class="btn btn-sm">已取消</a> 
						     </c:if>
						     <input id="st" type="hidden" value="${sta}">
						    </div>
						   </div>
						   
						</div>
					</div>
					<div class="panel-heading" align="center">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-536478" href="#panel-element-606732">
						 筛选条件</a>
					</div>
				</div>
				<!-- 筛选 -->
				<!-- 列表 -->
			  <c:if test="${actList!=null}">
					     <table class="table table-bordered" id="tblSort" sortCol="-1">
					     <thead style="background:white">
					     <tr><th onclick="sortTable('tblSort',0)" style="cursor:pointer">活动标题</th>
					         <th onclick="sortTable('tblSort',1,'date')" style="cursor:pointer">活动举办时间</th>
					         <th onclick="sortTable('tblSort',2,'date')" style="cursor:pointer">活动时长</th>
					         <th onclick="sortTable('tblSort',3)" style="cursor:pointer">举办人</th>
					         <th onclick="sortTable('tblSort',4)" style="cursor:pointer">活动状态</th>
					         <th onclick="sortTable('tblSort',5,'int')" style="cursor:pointer">报名人数</th>
					         <c:if test="${sessionScope.duty=='2'||sessionScope.duty=='1'}">
					         <th></th>
					         </c:if>
					     </tr>
					     </thead>
					     <tbody id="tbody">
					     <c:forEach items="${actList}" var="a" varStatus="status">
					     <c:if test="${status.count%2==0 }">
					     <tr style="background:#eeeeff">
					     </c:if>
					     <c:if test="${status.count%2!=0 }">
					     <tr style="background:#dedeff">
					     </c:if>
					      <td>
					      <a href="/yzwish/activity/act_page-${a.activityId}">${a.title}</a>
					      </td>
					      <td>${a.startTime}</td><td>${a.duration}小时</td>
					      <td>
					      <a href="#">${a.name}</a></td>
					      <td><c:choose>
					       <c:when test="${a.activityStatus==1}">报名中</c:when>
					       <c:when test="${a.activityStatus==2}">举办中</c:when>
					       <c:when test="${a.activityStatus==3}">举办成功</c:when>
					       <c:when test="${a.activityStatus==4}">已取消</c:when>
					      </c:choose></td>
					      <td>${a.numOfEnroll}</td>
					      <c:if test="${sessionScope.duty=='2'||sessionScope.duty=='1'}">
					      <td align="center">
					          <form action="act_follow" method="post">
					          <input type="hidden" name="activityId" value="${a.activityId}">
					          <input type="hidden" name="where" value="1">
					          <input name="duration" type="hidden" value="${duration}">
					          <input name="st" type="hidden" value="${sta}">
					          <button type="submit" id="follow" class="btn btn-default">关注</button>
					          </form>
					          <c:if test="${a.activityStatus==1}">
					          <c:set value="false" var="isEnroll"></c:set>
					          <c:if test="${el!=null}">
					            <c:forEach items="${el}" var="e">
					               <c:if test="${e.activityId==a.activityId}">
					                <c:set value="true" var="isEnroll"></c:set>
					               </c:if>
					            </c:forEach>
					          </c:if>
					          <c:if test="${isEnroll==true}">
					          <form action="act_unEnroll" method="post">
					          <input type="hidden" name="activityId" value="${a.activityId}">
					          <input type="hidden" name="where" value="1">
					          <input name="duration" type="hidden" value="${duration}">
					          <input name="st" type="hidden" value="${sta}">
					          <button type="submit" id="unEnroll" class="btn btn-default">已报名</button>
					          </form>
					          </c:if>
					          <c:if test="${isEnroll!=true}">
					          <form action="act_enroll" method="post">
					          <input type="hidden" name="activityId" value="${a.activityId}">
					          <input type="hidden" name="where" value="1">
					          <input name="duration" type="hidden" value="${duration}">
					          <input name="st" type="hidden" value="${sta}">
					          <button type="submit" id="enroll" class="btn btn-default">报名</button>
					          </form>
					          </c:if>
					          </c:if>
					      </td>
					      </c:if>
					     </tr>
					     </c:forEach>
					     </tbody>
					     </table>
					     <div align="center" style="margin-top:10px">
					     <ul class="pagination">
					     <li><a href="" id="btn1">首页</a></li>
					     <li><a href="" id="btn2">上一页</a></li>
					     <li><a id="btn0"></a></li>
					     <li><a href="" id="btn3">下一页</a></li>
					     <li><a href="" id="btn4">尾页</a></li>
					     </ul> 
					     <input id="changePage" type="hidden" size="1" maxlength="4"/>
					     <input id="pageSize" type="hidden" size="1" maxlength="2" value="getDefaultValue()"/>
					     <input id="sjzl" type="hidden">
					     </div>
			  </c:if>
			  <c:if test="${actList==null}">
				  对不起，暂时没有符合的活动。
			  </c:if>
			    <!-- 列表 -->
			    <!-- 发起活动 -->
			    <c:if test="${sessionScope.duty=='3'}">
			          <div align="right">
					  <a id="modal-166519" href="#modal-container-166519" role="button" class="btn btn-default" data-toggle="modal">我要发起</a>
					  </div>
					  <div class="modal fade" id="modal-container-166519" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					    <div class="modal-dialog">
					      <div class="modal-content">
					       <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								发起活动
							</h4>
						   </div>
						   <div class="modal-body">
					  <table>
				      <tr><td><label for="title">活动标题</label></td>
				      <td><input id="title" class="form-control" name="title" type="text" maxlength="20" /></td></tr>
				      <tr><td><label for="info">活动简介</label></td>
				      <td><textarea class="form-control" id="info" name="info" style="resize:none;" rows="3"
		          maxlength="100" placeholder="简介请不要超过50字"></textarea>
				      </tr>
				      <tr><td><label for="startTime">活动开始时间</label></td>
				      <td>
				      <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                       <input class="form-control" size="16" type="text" id="startTime" name="startTime" readonly>
                       <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					   <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                      </div>
				      <input type="hidden" id="dtp_input1" value="" /><br/>
				      </td></tr>
				      <tr><td><label for="duration">活动时长</label></td>
				      <td>
				       <select id="duration" name="duration" class="btn btn-default">
				        <c:forEach var="x" begin="1" end="72">
				          <option value="${x}">${x}小时</option>
				        </c:forEach>
				       </select>
				      </td></tr>
				      <tr><td><label for="least">报名最低人数</label></td>
				      <td><select id="least" name="least" class="btn btn-default">
				        <c:forEach var="x" begin="10" end="100">
				          <option value="${x}">${x}人</option>
				        </c:forEach>
				       </select>
				      </td></tr>
				      </table>
						   </div>
						   <div class="modal-footer">
						     <button type="button" id="start" class="btn btn-primary">确认发起</button>
							 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						   </div>
						  </div>
						</div>
					  </div>
				   </c:if>
			    <!-- 发起活动 -->
			</div>
			
		</div>
	</div>
  <jsp:include page="down.jsp"/>
</div>

   <!-- 日历 -->
   <script type="text/javascript" src="../jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
   <script type="text/javascript" src="../js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
   <script type="text/javascript" src="../js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
   <script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language:  'ch',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
   </script>
   <!-- 日历 -->

</body>
</html>