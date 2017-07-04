<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model.*" %>
<%@ page import="java.net.URLEncoder"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人主页</title>

<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

</head>
<body>
<script type="text/javascript">


function changePrivate(){

	if(document.getElementById("duty").value=="1")
	{
	obj = document.getElementsByName("setStu");
	}
	else if(document.getElementById("duty").value=="2")
	{
		obj = document.getElementsByName("setUni");
	}
	else if(document.getElementById("duty").value=="3")
	{
		obj = document.getElementsByName("setTea");
	}
	
    check_val = [];
    for(k in obj){
    	
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    alert("选择"+check_val);
	var info=JSON.stringify(check_val);
	$.ajax({
		type:'post',
		contentType:'application/json',
		url:'/yzwish/comment/changePrivate.do',
		data:info,	
		beforeSend:function(){
			//alert("beforeSend:function");
		},										 
	    success:function(data){
	    	
	    	alert("成功");
	    	
	    },
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("error！");
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
	    }
	}
			);
	
}


function showZy(){
	//显示个人主页
	if(document.getElementById("visitDuty").value=="2")
	{
	//alert("访问角色:学长学姐");
	document.getElementById("privateUni").style.display="none";
	document.getElementById("privateTea").style.display="none";
	document.getElementById("privateStu").style.display="none";
	document.getElementById("baseInfoUni").style.display="";
    
	 
	}
	else  if(document.getElementById("visitDuty").value=="1")
	{
	//alert("访问角色：学生");
	document.getElementById("privateUni").style.display="none";
	document.getElementById("privateTea").style.display="none";
	document.getElementById("privateStu").style.display="none";
	document.getElementById("baseInfoStu").style.display="";
	
	 
	}
	
	else  if(document.getElementById("visitDuty").value=="3")
	{
	//alert("访问角色：老师");
	document.getElementById("privateUni").style.display="none";
	document.getElementById("privateTea").style.display="none";
	document.getElementById("privateStu").style.display="none";
	document.getElementById("baseInfoTea").style.display="";
	
	 
	}
	
	
	
	}
						
						
function showYssz(){
	//显示隐私设置
	
	if(document.getElementById("duty").value=="2")
	{
		//alert("隐私设置角色：学长学姐");
		
		if(document.getElementById("visitDuty").value=="2")
			document.getElementById("baseInfoUni").style.display="none";
			else if(document.getElementById("visitDuty").value=="1")
			document.getElementById("baseInfoStu").style.display="none";
			else  if(document.getElementById("visitDuty").value=="3")
			document.getElementById("baseInfoTea").style.display="none";
		
	    document.getElementById("privateUni").style.display="";
	 
	}
	
	else if(document.getElementById("duty").value=="1")
	{
		//alert("隐私设置角色：高考生");
		
		if(document.getElementById("visitDuty").value=="2")
			document.getElementById("baseInfoUni").style.display="none";
			else if(document.getElementById("visitDuty").value=="1")
			document.getElementById("baseInfoStu").style.display="none";
			else  if(document.getElementById("visitDuty").value=="3")
			document.getElementById("baseInfoTea").style.display="none";
		
		document.getElementById("privateStu").style.display="";
		 
	}
	
	else if(document.getElementById("duty").value=="3")
	{
		//alert("隐私设置角色：老师");
		
		if(document.getElementById("visitDuty").value=="2")
		document.getElementById("baseInfoUni").style.display="none";
		else if(document.getElementById("visitDuty").value=="1")
		document.getElementById("baseInfoStu").style.display="none";
		else  if(document.getElementById("visitDuty").value=="3")
		document.getElementById("baseInfoTea").style.display="none";
		
		document.getElementById("privateTea").style.display="";
		 
    }
	
	
	}
                      
                        
</script>

<input type="hidden" id="duty" value="${duty}">
<input type="hidden" id="visitDuty" value="${visitDuty}">
<input type="hidden" id="id" value="${id}">
<input type="hidden" id="login_status" value="${login_status}">

<!-- 状态栏 -->
<div class="header-banner">
		<div class="container">
			<div class="header-top">
				<div class="social-icons" style = "text-align:right;">
				
				<c:if test="${sessionScope.login_status!=1}">
				  <a href="<%=request.getContextPath()%>/jsp/login.jsp" id="login" style="font-size:20px;">注册/登录</a>
				</c:if>
				<c:if test="${sessionScope.login_status==1}">
				  <!-- 欢迎<a href="<%=request.getContextPath()%>/jsp/userPage.jsp" id="login" style="font-size:20px;">${sessionScope.id}</a> -->
				  欢迎<a href="<%=request.getContextPath()%>/comment/getUser.do" id="login" style="font-size:20px;">${sessionScope.id}</a>
				  <a href="<%=request.getContextPath()%>/login/logout" id="login" style="font-size:20px;">注销</a>
				</c:if>
				
				</div>
				
			</div>
		</div>
		
</div>

<div class="container">

<div class="row clearfix">

<!-- 主页条 -->
				
				


<!-- 带入session user -->


            <h3>
			    ${visitUser.id}的个人主页
			</h3>
			
			<input type="hidden" id="duty" value="${duty}"/>
			<br>
			<a href="#">关注</a>
			<a href="#">举报</a>
			<br>
			<br>


<div class="col-md-2 column">
					<div class="panel-group" id="panel-699894">
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title collapsed"  data-parent="#panel-699894" href="javascript:showZy();">个人主页</a>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title collapsed"  data-parent="#panel-699894" href="javascript:showYssz();">隐私设置</a>
							</div>
						</div>
						
					</div>
</div>

<div class="col-md-10 column">

		   
<!-- 高考生信息展示 -->
		   <c:if test="${visitDuty==1}">
		   <div style="display:" id="baseInfoStu">
		   <c:if test="${visitStuPrivate.showBasic==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							基础信息
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							用户名
						</td>
						<td>
							${visitUser.id}
						</td>
					</tr>
					<tr>
						<td>
							性别
						</td>
						<td>
							${visitUser.sex}
						</td>
					</tr>
					<tr>
						<td>
							年龄
						</td>
						<td>
							${visitUser.age}
						</td>
					</tr>
					<tr>
						<td>
							省/市
						</td>
						<td>
							${visitUser.provinceName}
						</td>
					</tr>
					<tr>
						<td>
							高中
						</td>
						<td>
							${visitUser.hname}
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<c:if test="${visitStuPrivate.showPreference==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							志愿信息
						</th>
					</tr>
				</thead>
			<%
			HashMap<String,String> visitVolunSchool=(HashMap<String,String>)request.getAttribute("visitVolunSchool"); 
			if(visitVolunSchool.isEmpty())
			{
			%>
			<tbody>
					<tr>
						<td>
							暂未添加志愿
					</tr>
				</tbody>
			<%
			}
			else{			
				Iterator VolunSchool = visitVolunSchool.keySet().iterator();
				int VolunSchoolNum = 1;
				
			%>
			
			<% 
			    while(VolunSchool.hasNext())
				{
					
					String volunRecord = (String)VolunSchool.next();
					String volunContent=visitVolunSchool.get(volunRecord);
			%>	
			
			
				<tbody>
					<tr>
						<td>
							志愿类型：<%=volunRecord%>
						</td>
						<td>
							<%=volunContent%>
						</td>
					</tr>
				</tbody>
			<%	
				}
			}
			%>
			</table>
			</c:if>
			
			<c:if test="${visitStuPrivate.showTopic==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我发起的话题
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Topic> listT=(ArrayList<Topic>)request.getAttribute("visitTopic"); 
			if(listT.isEmpty())
			{
				%>
			  <tbody>
					<tr>
						<td>
							暂无话题
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Topic topic:listT)
			{
				String name=topic.getTopicName();
			
			
			%>
			
				<tbody>
					<tr>
						<td>
							话题名
						</td>
						<td>
							<%=name%>
						</td>
					</tr>
				</tbody>
		
			<%
			  }
			}
			
			%>
			</table>
			</c:if>
			<c:if test="${visitStuPrivate.showQuestion==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我提出的问题
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Question> listQ=(ArrayList<Question>)request.getAttribute("visitQuestion"); 
			
			if(listQ.isEmpty())
			{
				%>
			   <tbody>
					<tr>
						<td>
							暂无问题
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Question question:listQ)
			{
				String name=question.getQuestionTitle();
				String time=question.getQuesCreateTime();
			
			
			%>
			
				<tbody>
					<tr>
						<td>
							<%=name%>
						</td>
						<td>
							<%=time%>
						</td>
					</tr>
				</tbody>
			
				<%
			  }
			}
			
			%>
			</table>
			</c:if>
		
			<c:if test="${visitStuPrivate.showAnswer==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的回答
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Answer> listA=(ArrayList<Answer>)request.getAttribute("visitAnswer"); 
			
			if(listA.isEmpty())
			{
				%>
			  <tbody>
					<tr>
						<td>
							暂无回答
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Answer answer:listA)
			{
				String name=answer.getAnswerContent();
				String time=answer.getAnswerTime();
			
			
			%>
			
			
				<tbody>
					<tr>
						<td>
							回答:<%=name%>
						</td>
						<td>
							回答时间:<%=time%>
						</td>
					</tr>
				</tbody>
				<%
			  }
			}
			
			%>
			</table>
			</c:if>
			<c:if test="${visitStuPrivate.showFollow==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的关注
						</th>
					</tr>
				</thead>
			<%
			HashMap<String,String> visitFollow=(HashMap<String,String>)request.getAttribute("visitFollow"); 
			if(visitFollow.isEmpty())
			{
			%>
			<tbody>
					<tr>
						<td>
							暂无关注
						</td>
					</tr>
					</tbody>
			<%
			}
			else{			
				Iterator followkeySet = visitFollow.keySet().iterator();
				int followNum = 1;
				
		
			    while(followkeySet.hasNext())
				{
					
					String followRecord = (String)followkeySet.next();
					String followContent=visitFollow.get(followRecord);
			%>	
				<tbody>
					<tr>
						<td>
							关注类型：<%=followRecord %>
						</td>
						<td>
							关注类型：<%=followContent %>
						</td>
					</tr>
					</tbody>
			
			<%	
				}
			}
			%>
			</table>
			</c:if>
			<c:if test="${visitStuPrivate.showActivity==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的活动
						</th>
					</tr>
				</thead>
			<%
			ArrayList<EnrollTable> listE=(ArrayList<EnrollTable>)request.getAttribute("visitEnrollTable"); 
			if(listE.isEmpty())
			{
				%>
				<tbody>
					<tr>
						<td>
							暂无活动信息
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(EnrollTable e:listE)
			{
				String title=e.getTitle();
				String time=e.getEnrollTime();
			
			
			%>
				<tbody>
					<tr>
						<td>
							<%=title%>
						</td>
						<td>
					        参加时间<%=title%>
						</td>
					</tr>
				</tbody>
				<%
				  } 
			    }
				%>
			</table>
			</c:if>
	     </div>
	     </c:if>

<!-- 学长学姐用户信息展示 -->
            <c:if test="${visitDuty==2}">
	        <div style="display:" id="baseInfoUni">
				
		   <c:if test="${visitUniPrivate.showBasic==1}">				
		    <table class="table table-hover">
				<thead>
					<tr>
						<th>
							基础信息
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							用户名
						</td>
						<td>
							${visitUser.id}
						</td>
					</tr>
					<tr>
						<td>
							性别
						</td>
						<td>
							${visitUser.sex}
						</td>
					</tr>
					<tr>
						<td>
							年龄
						</td>
						<td>
							${visitUser.age}
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<c:if test="${visitUniPrivate.showCredit==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							审核信息
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							真实姓名
						</td>
						<td>
							${visitUser.realName}
						</td>
					</tr>
					<tr>
						<td>
							省/市
						</td>
						<td>
							${visitUser.provinceName}
						</td>
					</tr>
					<tr>
						<td>
							大学
						</td>
						<td>
							${visitUser.universityName}
						</td>
					</tr>
					<tr>
						<td>
							专业
						</td>
						<td>
							${visitUser.majorName}
						</td>
					</tr>
					<tr>
						<td>
							入学年份
						</td>
						<td>
							${visitUser.sYear}
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			
			
			<c:if test="${visitUniPrivate.showTopic==1}">
            <table class="table table-hover">
				<thead>
					<tr>
						<th>
							我发起的话题
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Topic> listT=(ArrayList<Topic>)request.getAttribute("visitTopic"); 
			if(listT.isEmpty())
			{
				%>
			  <tbody>
					<tr>
						<td>
							暂无话题
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Topic topic:listT)
			{
				String name=topic.getTopicName();
			
			
			%>
			
				<tbody>
					<tr>
						<td>
							话题名
						</td>
						<td>
							<%=name%>
						</td>
					</tr>
				</tbody>
		
			<%
			  }
			}
			
			%>
			</table>
			</c:if>
			
			
			<c:if test="${visitUniPrivate.showAnswer==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的回答
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Answer> listA=(ArrayList<Answer>)request.getAttribute("visitAnswer"); 
			
			if(listA.isEmpty())
			{
				%>
			  <tbody>
					<tr>
						<td>
							暂无回答
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Answer answer:listA)
			{
				String name=answer.getAnswerContent();
				String time=answer.getAnswerTime();
			
			
			%>
			
			
				<tbody>
					<tr>
						<td>
							回答:<%=name%>
						</td>
						<td>
							回答时间:<%=time%>
						</td>
					</tr>
				</tbody>
				<%
			  }
			}
			
			%>
			</table>
			</c:if>
			
			
			<c:if test="${visitUniPrivate.showFollow==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的关注
						</th>
					</tr>
				</thead>
			<%
			HashMap<String,String> visitFollow=(HashMap<String,String>)request.getAttribute("visitFollow"); 
			if(visitFollow.isEmpty())
			{
			%>
			<tbody>
					<tr>
						<td>
							暂无关注
						</td>
					</tr>
					</tbody>
			<%
			}
			else{			
				Iterator followkeySet = visitFollow.keySet().iterator();
				int followNum = 1;
				
		
			    while(followkeySet.hasNext())
				{
					
					String followRecord = (String)followkeySet.next();
					String followContent=visitFollow.get(followRecord);
			%>	
				<tbody>
					<tr>
						<td>
							关注类型：<%=followRecord %>
						</td>
						<td>
							关注类型：<%=followContent %>
						</td>
					</tr>
					</tbody>
			
			<%	
				}
			}
			%>
			</table>
			</c:if>
		    </div>
		    </c:if>

	     <c:if test="${visitDuty==3}">
		   <!-- 老师用户信息展示 -->	
		   <div style="display:" id="baseInfoTea">
		   <c:if test="${visitTeaPrivate.showBasic==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							基础信息
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							用户名
						</td>
						<td>
							${visitUser.id}
						</td>
					</tr>
					<tr>
						<td>
							性别
						</td>
						<td>
							${visitUser.age}
						</td>
					</tr>
					<tr>
						<td>
							年龄
						</td>
						<td>
							${visitUser.sex}
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>
			
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							审核信息
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							真实姓名
						</td>
						<td>
							${visitUser.realName}
						</td>
					</tr>
					<tr>
						<td>
							省/市
						</td>
						<td>
							${visitUser.provinceName}
						</td>
					</tr>
					<tr>
						<td>
							大学
						</td>
						<td>
							${visitUser.universityName}
						</td>
					</tr>
					<tr>
						<td>
							研究方向
						</td>
						<td>
							${visitUser.resDirection}
						</td>
					</tr>
					<tr>
						<td>
							职位
						</td>
						<td>
							${visitUser.position}
						</td>
					</tr>
					
					<tr>
						<td>
							评价者：
						</td>
						<td>
							评价内容：
						</td>
					</tr>
					
				</tbody>
			</table>
			<c:if test="${visitTeaPrivate.showTopic==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我发起的话题
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Topic> listT=(ArrayList<Topic>)request.getAttribute("visitTopic"); 
			if(listT.isEmpty())
			{
				%>
			  <tbody>
					<tr>
						<td>
							暂无话题
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Topic topic:listT)
			{
				String name=topic.getTopicName();
			
			
			%>
			
				<tbody>
					<tr>
						<td>
							话题名
						</td>
						<td>
							<%=name%>
						</td>
					</tr>
				</tbody>
		
			<%
			  }
			}
			
			%>
			</table>
			</c:if>
			
			<c:if test="${visitTeaPrivate.showAnswer==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的回答
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Answer> listA=(ArrayList<Answer>)request.getAttribute("visitAnswer"); 
			
			if(listA.isEmpty())
			{
				%>
			  <tbody>
					<tr>
						<td>
							暂无回答
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Answer answer:listA)
			{
				String name=answer.getAnswerContent();
				String time=answer.getAnswerTime();
			
			
			%>
			
			
				<tbody>
					<tr>
						<td>
							回答:<%=name%>
						</td>
						<td>
							回答时间:<%=time%>
						</td>
					</tr>
				</tbody>
				<%
			  }
			}
			
			%>
			</table>
			</c:if>
			<c:if test="${visitTeaPrivate.showFollow==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的关注
						</th>
					</tr>
				</thead>
			<%
			HashMap<String,String> visitFollow=(HashMap<String,String>)request.getAttribute("visitFollow"); 
			if(visitFollow.isEmpty())
			{
			%>
			<tbody>
					<tr>
						<td>
							暂无关注
						</td>
					</tr>
					</tbody>
			<%
			}
			else{			
				Iterator followkeySet = visitFollow.keySet().iterator();
				int followNum = 1;
				
		
			    while(followkeySet.hasNext())
				{
					
					String followRecord = (String)followkeySet.next();
					String followContent=visitFollow.get(followRecord);
			%>	
				<tbody>
					<tr>
						<td>
							关注类型：<%=followRecord %>
						</td>
						<td>
							关注类型：<%=followContent %>
						</td>
					</tr>
					</tbody>
			
			<%	
				}
			}
			%>
			</table>
			</c:if>
			<c:if test="${visitTeaPrivate.showActivity==1}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							我的活动
						</th>
					</tr>
				</thead>
			<%
			ArrayList<Activity> listE=(ArrayList<Activity>)request.getAttribute("visitActivity"); 
			if(listE.isEmpty())
			{
				%>
				<tbody>
					<tr>
						<td>
							暂无活动信息
						</td>
					</tr>
				</tbody>
			   <%
			}
			else
			{
			for(Activity e:listE)
			{
				String title=e.getTitle();
				String time=e.getStartTime();
			
			
			%>
				<tbody>
					<tr>
						<td>
							<%=title%>
						</td>
						<td>
					        发起时间:<%=time%>
						</td>
					</tr>
				</tbody>
				<%
				  } 
			    }
				%>
			</table>
			
		    </c:if>
		    <c:if test="${id!=visitId}">
			<div style="display:" id="judgeTeaDiv">
			
						<label class="control-label">评价老师</label>
						<br>
						<textarea name="judgeTea" id="judgeTea" style="width:400px;height:60px;"></textarea>
						<p class="help-block">
								不超过20字
							</p>
						<div style = "text-align:left;">
						<input type="button" id="loginCheck" value="提交" class="btn btn-default">
			            </div>
						<div style="height:20px"></div>
			</div>
			</c:if>
			
			</div>
			</c:if>
			
			<div style="display:none" id="privateStu">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							设置列表
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
						<div>
			               <input type="checkbox" name="setStu" id="basicStu" <c:if test="${userStuPrivate.showBasic==1}">checked="checked"</c:if>  value="1" />
				           <span>展示基础信息:</span>
				           </div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" name="setStu" id="preStu" <c:if test="${userStuPrivate.showPreference==1}">checked="checked"</c:if> value="2" />
				        <span>展示志愿信息</span>
				        </div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
				             <input type="checkbox" name="setStu" id="topStu" <c:if test="${userStuPrivate.showTopic==1}">checked="checked"</c:if> value="3" />
				             <span>展示我发起的话题</span>
				            </div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
				               <input type="checkbox" name="setStu" id="queStu" <c:if test="${userStuPrivate.showQuestion==1}">checked="checked"</c:if> value="4" />
				               <span>展示我提出的问题</span>
						    </div>
						</td>
					</tr>
					<tr>
						<td>
						    <div>
				             <input type="checkbox" name="setStu" id="ansStu" <c:if test="${userStuPrivate.showAnswer==1}">checked="checked"</c:if> value="5" />
				             <span>展示我的回答</span>
				            </div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
				             <input type="checkbox" name="setStu" id="folStu" <c:if test="${userStuPrivate.showFollow==1}">checked="checked"</c:if> value="6" />
				             <span>展示我的关注</span>
						     <span style="color:#FFFFFF">不展示</span>
				            </div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
				             <input type="checkbox" name="setStu" id="actStu" <c:if test="${userStuPrivate.showActivity==1}">checked="checked"</c:if> value="7" />
				             <span>展示我的活动</span>
				            </div>
						</td>
					</tr>
				</tbody>
			  </table>
			  <form name="form" id="f2" class="form-horizontal templatemo-login-form-2" role="form" 
			  method="post" onSubmit="changePrivate()" enctype="multipart/form-data">
			  <div style = "text-align:right;">
			   <input type="submit" class="btn btn-default" value="修改"/>
			   </div>
			   </form>
			</div>
			
			
			<div style="display:none" id="privateUni">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							设置列表
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
						<div>
			            <input type="checkbox" id="r0" name="setUni" <c:if test="${userUniPrivate.showBasic==1}">checked="checked"</c:if> value="1" />
			            <span>展示基础信息</span>
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setUni" <c:if test="${userUniPrivate.showCredit==1}">checked="checked"</c:if> value="2" />
				        <span>展示审核信息</span>
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setUni" <c:if test="${userUniPrivate.showTopic==1}">checked="checked"</c:if> value="3" />
				        <span>展示我发起的话题</span>
				        </div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setUni" <c:if test="${userUniPrivate.showAnswer==1}">checked="checked"</c:if> value="4" />
				        <span>展示我的回答</span>
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setUni" <c:if test="${userUniPrivate.showFollow==1}">checked="checked"</c:if> value="5" />
				        <span>展示我的关注</span>
						</div>
						</td>
					</tr>
				</tbody>
			  </table>
			  <form name="form" id="f2" class="form-horizontal templatemo-login-form-2" role="form" 
			  method="post" onSubmit="changePrivate()" enctype="multipart/form-data">
			  <div style = "text-align:right;">
			   <input type="submit" class="btn btn-default" value="修改"/>
			   </div>
			   </form>    
			</div>
			
			
			<div style="display:none" id="privateTea">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							设置列表
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
						<div>
			            <input type="checkbox" id="r0" name="setTea" <c:if test="${userTeaPrivate.showBasic==1}">checked="checked"</c:if> value="1" />
			            <span>展示基础信息</span>
				        </div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setTea" <c:if test="${userTeaPrivate.showTopic==1}">checked="checked"</c:if> value="2" />
				        <span>展示我发起的话题</span>
						</div>
				        
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setTea" <c:if test="${userTeaPrivate.showAnswer==1}">checked="checked"</c:if> value="3" />
				        <span>展示我的回答</span>
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setTea" <c:if test="${userTeaPrivate.showFollow==1}">checked="checked"</c:if> value="4" />
				        <span>展示我的关注</span>
						</div>
						</td>
					</tr>
					
					<tr>
						<td>
						<div>
				        <input type="checkbox" id="r0" name="setTea" <c:if test="${userTeaPrivate.showActivity==1}">checked="checked"</c:if> value="5" />
				        <span>展示我的活动</span>
						</div>
						</td>
					</tr>
				   </tbody>
			    </table>
			   <form name="form" id="f2" class="form-horizontal templatemo-login-form-2" role="form" 
			   method="post" action= enctype="multipart/form-data">
			   <div style = "text-align:right;">
			   <input type="submit" class="btn btn-default" value="修改"/>
			   </div>
			   </form>
             </div>
</div>
</div>
</div>

</body>
</html>

