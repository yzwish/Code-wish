<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>

</head>
<body>

<Script Language="JavaScript"> 
    function delQUS() 
    { 
    document.form1.action="delQuestion_do"; 
    document.form1.submit(); 
    } 
    
    function delQUSBU()
    { 
    document.form1.action="delQuestionBU_do"; 
    document.form1.submit(); 
    }
    
    function queBUer() 
    { 
    document.form1.action="QueBUer_do"; 
    document.form1.submit(); 
    }
    
    function neglectQ() 
    { 
    document.form1.action="NeglectQ_do"; 
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
					话题管理--管理举报问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=green>${message1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=blue>${message2}</font>
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
							问题id
						</th>
						<th>
							问题名称
						</th>
						<th>
							举报理由
						</th>
						<th>
							问题提问者id
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
					
		<c:forEach var="QrerStuL" items="${QrerStuL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportQ" value="${QrerStuL.reportID}"></td>
    			<td>${QrerStuL.reportID}</td>
     			<td>${QrerStuL.reportedID}</td>
     			<td>${QrerStuL.topicName}</td>
   				<td>${QrerStuL.reportReason}</td>
   				<td>${QrerStuL.topicUserId}</td>
    			<td>${QrerStuL.reporterID}</td>
   				<td>${QrerStuL.reportTime}</td>
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
							问题id
						</th>
						<th>
							问题名称
						</th>
						<th>
							举报理由
						</th>
						<th>
							问题提问者id
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
					
		<c:forEach var="QrerTeaL" items="${QrerTeaL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportQ" value="${QrerTeaL.reportID}"></td>
    			<td>${QrerTeaL.reportID}</td>
     			<td>${QrerTeaL.reportedID}</td>
     			<td>${QrerTeaL.topicName}</td>
   				<td>${QrerTeaL.reportReason}</td>
   				<td>${QrerTeaL.topicUserId}</td>
    			<td>${QrerTeaL.reporterID}</td>
   				<td>${QrerTeaL.reportTime}</td>
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
							问题id
						</th>
						<th>
							问题名称
						</th>
						<th>
							举报理由
						</th>
						<th>
							问题提问者id
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
					
		<c:forEach var="QrerSenL" items="${QrerSenL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportQ" value="${QrerSenL.reportID}"></td>
    			<td>${QrerSenL.reportID}</td>
     			<td>${QrerSenL.reportedID}</td>
     			<td>${QrerSenL.topicName}</td>
   				<td>${QrerSenL.reportReason}</td>
   				<td>${QrerSenL.topicUserId}</td>
    			<td>${QrerSenL.reporterID}</td>
   				<td>${QrerSenL.reportTime}</td>
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
					<INPUT Type="submit"  Value="删除问题" class="btn btn-default btn-block" onClick="delQUS()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="删除问题并封禁问题发起人" class="btn btn-default btn-block" onClick="delQUSBU()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="封禁举报人" class="btn btn-default btn-block" onClick="queBUer()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="忽略举报" class="btn btn-default btn-block" onClick="neglectQ()"> 
				</div>
			</div>
		</div>
		<div class="col-md-1">
		</div>
		</form>
	</div>


</body>
</html>