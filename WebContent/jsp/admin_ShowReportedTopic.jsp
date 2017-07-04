<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>

</head>
<body>

<Script Language="JavaScript"> 
    function delTOP() 
    { 
    document.form1.action="delTopic_do"; 
    document.form1.submit(); 
    } 
    
    function delTOPBU() 
    { 
    document.form1.action="delTopicBU_do"; 
    document.form1.submit(); 
    }
    
    function topBUer() 
    { 
    document.form1.action="TopBUer_do"; 
    document.form1.submit(); 
    }
    
    function neglectT() 
    { 
    document.form1.action="NeglectT_do"; 
    document.form1.submit(); 
    } 
    </Script> 

	<div class="row">
		<div class="col-md-12">
		        <div id="header"><%@ include file="admin_Menu.jsp"%></div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12" >
		        <ul class="breadcrumb" style="background: transparent; font-weight: bold;">
				<li>
					<a href="<%=request.getContextPath()%>/jsp/admin_Menu.jsp">管理员后台</a> <span class="divider">/</span>
				</li>
				<li class="active">
					话题管理--管理举报话题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=green>${message1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=blue>${message2}</font>
				</li>
			</ul>
		</div>
	</div>

	
	
		<div class="row">
		<form name="form1" action = "" method = "post">	
		<div class="col-md-1">
		</div>
		<div class="col-md-10">
		
					<div class="col-md-12">
			<div class="tabbable" id="tabs-171119" >
				<ul class="nav nav-tabs" >
					<li class="active">
						<a href="#panel-1" data-toggle="tab">举报人-高考生</a>
					</li>
					<li>
						<a href="#panel-2" data-toggle="tab">举报人-老师</a>
					</li>
					<li>
						<a href="#panel-3" data-toggle="tab">举报人-学长学姐</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-1">
						<p>
							
							
			<table class="table table-bordered">
				<thead>
					<tr class="warning">
						<th>
							 
						</th>
						<th>
							举报id
						</th>
						<th>
							话题id
						</th>
						<th>
							话题名称
						</th>
						<th>
							举报理由
						</th>
						<th>
							话题发起人id
						</th>
						<th>
							举报者id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="TrerStuL" items="${TrerStuL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportT" value="${TrerStuL.reportID}"></td>
    			<td>${TrerStuL.reportID}</td>
     			<td>${TrerStuL.reportedID}</td>
     			<td>${TrerStuL.topicName}</td>
   				<td>${TrerStuL.reportReason}</td>
   				<td>${TrerStuL.topicUserId}</td>
    			<td>${TrerStuL.reporterID}</td>
   				<td>${TrerStuL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
					<div class="tab-pane" id="panel-2">
						<p>


			<table class="table table-bordered">
				<thead>
					<tr class="warning">
						<th>
							 
						</th>
						<th>
							举报id
						</th>
						<th>
							话题id
						</th>
						<th>
							话题名称
						</th>
						<th>
							举报理由
						</th>
						<th>
							话题发起人id
						</th>
						<th>
							举报者id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="TrerTeaL" items="${TrerTeaL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportT" value="${TrerTeaL.reportID}"></td>
    			<td>${TrerTeaL.reportID}</td>
     			<td>${TrerTeaL.reportedID}</td>
     			<td>${TrerTeaL.topicName}</td>
   				<td>${TrerTeaL.reportReason}</td>
   				<td>${TrerTeaL.topicUserId}</td>
    			<td>${TrerTeaL.reporterID}</td>
   				<td>${TrerTeaL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>


						</p>
					</div>
					<div class="tab-pane" id="panel-3">
						<p>


			<table class="table table-borderedtable table-bordered">
				<thead>
					<tr class="warning">
						<th>
							 
						</th>
						<th>
							举报id
						</th>
						<th>
							话题id
						</th>
						<th>
							话题名称
						</th>
						<th>
							举报理由
						</th>
						<th>
							话题发起人id
						</th>
						<th>
							举报者id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="TrerSenL" items="${TrerSenL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportT" value="${TrerSenL.reportID}"></td>
    			<td>${TrerSenL.reportID}</td>
     			<td>${TrerSenL.reportedID}</td>
     			<td>${TrerSenL.topicName}</td>
   				<td>${TrerSenL.reportReason}</td>
   				<td>${TrerSenL.topicUserId}</td>
    			<td>${TrerSenL.reporterID}</td>
   				<td>${TrerSenL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
				</div>
			</div>
		</div>
			
			
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-4">
				<br>
		<label>选择封禁时间：</label>
		<select id="BTime" name="BTime">
         <option value="3">3天</option>
         <option value="15">15天</option>
         <option value="30">1个月</option>
         <option value="90">3个月</option>
         <option value="9999">永久封禁</option>
         </select>
         
				</div>
				<div class="col-md-4">
				</div>
			</div>
			<div class="row">
			<br>
				<div class="col-md-3">			 
					<INPUT Type="submit"  Value="删除话题" class="btn btn-default btn-block" onClick="delTOP()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="删除话题并封禁话题发起人" class="btn btn-default btn-block" onClick="delTOPBU()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="封禁举报人" class="btn btn-default btn-block" onClick="topBUer()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="忽略举报" class="btn btn-default btn-block" onClick="neglectT()"> 
				</div>
			</div>
		</div>
		<div class="col-md-1">
		</div>
		</form>
	</div>


</body>
</html>