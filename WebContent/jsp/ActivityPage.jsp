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
   <script>
   $( function(){
	   $('#question').click(
		function(){
			var activityquestion=new Object();
			activityquestion.acQuContext=$("#acQu").val();
			activityquestion.activityId=$("#aId").val();
			var info=JSON.stringify(activityquestion);
			 $.ajax({
				type:'post',
				contentType:'application/json',
				url:'/yzwish/activity/act_que',
				data:info,											
				dataType:"json",
				beforeSend:function(){
				//处理发送之前的检查事务。
				if($('#acQu').val()==''){
						alert("请填写问题内容！");
						return false;
					}
				},										
			    success:function(data){
			    	//通过data.key值取出相应信息
			    	//发起活动失败
			    	if(data.msg=='false')									    	
			    		alert("提问失败！请重新尝试！");
			    	else if(data.msg=='wrong')
			    		alert("提问失败！您的用户状态异常！");
			    	//发起活动成功
			    	else if(data.msg=='success'){
			    		alert("提问成功！");
			    		post('/yzwish/activity/act_page-'+$("#aId").val());
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
   function check(choice){
	   if(choice==1){
		   var val=$('input:radio[name="cancelReason"]:checked').val();
	       if(val!=null){
	    	   if(val==0){
	    		   var reason=document.getElementById('ohterRe').value;
	      			if(reason==''){
	      				alert("请填写理由！");
	      				return false;
	      			}
	      			else
	      				return true;
	   			}
	    	   else
	    		   return true;
	       }
	       else{
	    	   alert("请选择理由！");
	    	   return false;
	       }
	   }
	   else if(choice==2){
		   var context=document.getElementById('context').value;
 			if(context==''){
 				alert("请填写回答！");
 				return false;
 			}
 			else
 				return true;
	   }
	   
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
<title>${activity.title}</title>
</head>
<body class="templatemo-bg-gray">
<div class="container">
  <jsp:include page="up.jsp"/>
      <div id="back" style="height:2000px;background-color: rgb(248,248,248);" class="img-rounded">
		<div class="col-md-12 column">
		   <div align="left" style="margin-top:15px"><a href="/yzwish/activity/act_index" class="btn btn-default">返回</a></div>
		   <div class="page-header">
			<h3>活动标题：${activity.title}</h3><p>
			<h4>活动简介：${activity.info}</h4><p>
			活动举办人：<a href="#">${activity.name}</a>
		   </div>
		   <div class="col-md-12 column" style="margin-bottom:15px">
		     <!-- 关注 报名 -->
		     <c:if test="${sessionScope.duty=='2'||sessionScope.duty=='1'}">
		     <div align="right">
		     <form action="act_follow" method="post">
	          <input type="hidden" name="activityId" value="${activity.activityId}">
	          <input type="hidden" name="where" value="2">
	          <button type="submit" id="follow" class="btn btn-default">关注</button>
	         </form>
	         <c:if test="${activity.activityStatus==1}">
	          <c:set value="false" var="isEnroll"></c:set>
	          <c:if test="${el!=null}">
	            <c:forEach items="${el}" var="e">
	               <c:if test="${e.activityId==activity.activityId}">
	                <c:set value="true" var="isEnroll"></c:set>
	               </c:if>
	            </c:forEach>
	          </c:if>
	          <c:if test="${isEnroll==true}">
	          <form action="act_unEnroll" method="post">
	          <input type="hidden" name="activityId" value="${activity.activityId}">
	          <input type="hidden" name="where" value="2">
	          <button type="submit" id="unEnroll" class="btn btn-default">已报名</button>
	          </form>
	          </c:if>
	          <c:if test="${isEnroll!=true}">
	          <form action="act_enroll" method="post">
	          <input type="hidden" name="activityId" value="${activity.activityId}">
	          <input type="hidden" name="where" value="2">
	          <button type="submit" id="enroll" class="btn btn-default">报名</button>
	          </form>
	          </c:if>
	         </c:if>
	         </div>
	         </c:if>
	         <!-- 取消活动 -->
		    <c:if test="${activity.activityStatus==1}">
		    <c:if test="${sessionScope.duty=='3'&&sessionScope.id==activity.id}">
		        <div align="right">
					<a id="modal-166519" href="#modal-container-166519" role="button" class="btn btn-default" data-toggle="modal">取消活动</a>
				</div>
				<div class="modal fade" id="modal-container-166519" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				     <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">
							选择取消理由
						</h4>
					 </div>
					 <form action="act_cancel" method="post" onSubmit="return check(1);">
					 <div class="modal-body">
					  <table>
					    <tr>
					    <td>取消理由：</td>
					    <td>
				        <input type="radio" id="cancelReason1" name="cancelReason" value="emerengcy" checked="checked" />突发急事，无法如期举办活动。</td>
					    </tr>
					    <tr><td></td>
					    <td><input type="radio" id="cancelReason2" name="cancelReason" value="wrong" />活动信息填写错误，将重新发起活动。</td>
					    </tr>
					    <tr><td></td>
					    <td><input type="radio" id="cancelReason3" name="cancelReason" value="0" /><input type="text" id="ohterRe" name="ohterRe" maxlength="50"></td>
					    </tr>
				      </table>
					 </div>
					 <input type="hidden" name="activityId" value="${activity.activityId}">
					 <div class="modal-footer">
						<button type="submit" class="btn btn-primary">确认取消</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					 </div>
					 </form>
					</div>
				  </div>
				</div>
	         </c:if>
		      活动还未开始，请耐心等待！
		    </c:if>
		    <c:if test="${activity.activityStatus==2}">
		<c:if test="${ql==null}">
		  还没有人提问！
		</c:if>
		<c:if test="${ql!=null}">
		<div align="center">
		 <c:forEach items="${ql}" var="q">
		    <div style="background-color:white;width:80%;border:10px solid white;" class="img-rounded">
		    <div align="left">
		    <div style="text-align:center;background-color:#E8CCFF;width:10%;border:10px solid #E8CCFF;margin-bottom:30px"
		     class="img-rounded"><b><a href="#">${q.id}</a></b></div>
		    <div class="bubble-box arrow-left" style="width:500px;"> 
		    <div class="wrap" style="width:500px;">
		      <b>提问内容：</b>${q.acQuContext}
		    </div> </div></div>
		    <c:if test="${(q.acQeStatus!=1&&sessionScope.id==q.id)||sessionScope.id==activity.id}">
		      <div align="right">
		       <div style="text-align:center;background-color:#E8CCFF;width:10%;border:10px solid #E8CCFF;margin-bottom:30px"
		           class="img-rounded"><b>${activity.name}</b></div>
		       <div class="bubble-box arrow-right" style="width:500px;" align="left"> 
		       <div class="wrap" style="width:485px;">
		       <b>回答：</b>${q.answer}
		       </div> </div>
		       <c:if test="${q.acQeStatus==2&&sessionScope.id==q.id}">
		         <a href="">标记为已读</a>
		       </c:if>
		      </div>
		    </c:if>
		    <c:if test="${q.acQeStatus==1&&sessionScope.id==q.id}">
		      <div align="right">
		       <div style="text-align:center;background-color:#E8CCFF;width:10%;border:10px solid #E8CCFF;margin-bottom:30px"
		           class="img-rounded"><b>${activity.name}</b></div>
		       <div class="bubble-box arrow-right" style="width:500px;"> 
		       <div class="wrap" style="width:485px;">
		       <b>暂无回答</b>
		       </div> </div>
		      </div>
		    </c:if>
		    <c:if test="${q.acQeStatus==1&&sessionScope.duty=='3'&&sessionScope.id==activity.id}">
		       <div align="right">
		       <div style="text-align:center;background-color:#E8CCFF;width:10%;border:10px solid #E8CCFF;margin-bottom:30px"
		           class="img-rounded"><b>${activity.name}</b></div>
		       <div class="bubble-box arrow-right" style="width:500px;"> 
		       <div class="wrap" style="width:485px;">
		       <b>请回答：</b><br>
		       <form method="post" action="act_ans" onSubmit="return check(2);">
		       <textarea class="form-control" id="context" name="context" style="resize:none;" rows="5"
		          maxlength="500" placeholder="回答请不要超过250字"></textarea> 
		       <input type="hidden" id="qId" name="qId" value="${q.acQuestionId}">
		       <input type="hidden" id="aId" name="activityId" value="${activity.activityId}"> 
		       <button type="submit" class="btn btn-default">提交</button>
		       </form>
		       </div> </div>
		      </div>
		    </c:if>
		    </div>
		    <HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="100%" SIZE=3>
		 </c:forEach>
		</div>
		</c:if>
		      <c:if test="${sessionScope.duty=='2'||sessionScope.duty=='1'}">
		        <!-- 我要提问 -->
			          <div align="right">
					  <a id="modal-166520" href="#modal-container-166520" role="button" class="btn btn-default" data-toggle="modal">我要提问</a>
					  </div>
					  <div class="modal fade" id="modal-container-166520" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					    <div class="modal-dialog">
					      <div class="modal-content">
					       <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								提问
							</h4>
						   </div>
						   <div class="modal-body">
					  <table>
				      <tr><td><label for="acQuContext">问题内容</label></td>
				      <td>
		              <textarea class="form-control" id="acQu" name="acQuContext" cols="150" style="resize:none;" rows="5"
		          maxlength="300" placeholder="内容请不要超过150字"></textarea></td>
				      </tr>
				      </table>
						   </div>
						   <input type="hidden" id="aId" name="activityId" value="${activity.activityId}">
						   *每次提问将扣除您20虚拟币！
						   <div class="modal-footer">
						     <button type="button" id="question" class="btn btn-primary">确认发起</button>
							 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						   </div>
						  </div>
						</div>
					  </div>
			    <!-- 我要提问 -->
		      </c:if>
		    </c:if>
		    <c:if test="${activity.activityStatus==3}">
		      活动已结束！
		    </c:if>
		    <c:if test="${activity.activityStatus==4}">
		      活动已取消！
		    </c:if>
		   </div>
		</div>
	</div>
  <jsp:include page="down.jsp"/>
</div>

</body>
</html>