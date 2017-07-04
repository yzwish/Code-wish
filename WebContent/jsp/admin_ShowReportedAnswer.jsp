<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>

</head>
<body>

<Script Language="JavaScript"> 
    function delANS() 
    { 
    document.form1.action="delAnswer_do"; 
    document.form1.submit(); 
    } 
    
    function delANSBU()
    { 
    document.form1.action="delAnswerBU_do"; 
    document.form1.submit(); 
    }
    
    function ansBUer() 
    { 
    document.form1.action="AnsBUer_do"; 
    document.form1.submit(); 
    }
    
    function neglectA() 
    { 
    document.form1.action="NeglectA_do"; 
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
					话题管理--管理举报回答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=green>${message1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=blue>${message2}</font>
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
							回答id
						</th>
						<th>
							回答内容
						</th>
						<th>
							举报理由
						</th>
						<th>
							回答者id
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
					
		<c:forEach var="ArerStuL" items="${ArerStuL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportA" value="${ArerStuL.reportID}"></td>
    			<td>${ArerStuL.reportID}</td>
     			<td>${ArerStuL.reportedID}</td>
     			<td>${ArerStuL.topicName}</td>
   				<td>${ArerStuL.reportReason}</td>
   				<td>${ArerStuL.topicUserId}</td>
    			<td>${ArerStuL.reporterID}</td>
   				<td>${ArerStuL.reportTime}</td>
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
							回答id
						</th>
						<th>
							回答内容
						</th>
						<th>
							举报理由
						</th>
						<th>
							回答者id
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
					
		<c:forEach var="ArerTeaL" items="${ArerTeaL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportA" value="${ArerTeaL.reportID}"></td>
    			<td>${ArerTeaL.reportID}</td>
     			<td>${ArerTeaL.reportedID}</td>
     			<td>${ArerTeaL.topicName}</td>
   				<td>${ArerTeaL.reportReason}</td>
   				<td>${ArerTeaL.topicUserId}</td>
    			<td>${ArerTeaL.reporterID}</td>
   				<td>${ArerTeaL.reportTime}</td>
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
							回答id
						</th>
						<th>
							回答内容
						</th>
						<th>
							举报理由
						</th>
						<th>
							回答者id
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
					
		<c:forEach var="ArerSenL" items="${ArerSenL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportA" value="${ArerSenL.reportID}"></td>
    			<td>${ArerSenL.reportID}</td>
     			<td>${ArerSenL.reportedID}</td>
     			<td>${ArerSenL.topicName}</td>
   				<td>${ArerSenL.reportReason}</td>
   				<td>${ArerSenL.topicUserId}</td>
    			<td>${ArerSenL.reporterID}</td>
   				<td>${ArerSenL.reportTime}</td>
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
					<INPUT Type="submit"  Value="删除回答" class="btn btn-default btn-block" onClick="delANS()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="删除回答并封禁回答者" class="btn btn-default btn-block" onClick="delANSBU()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="封禁举报人" class="btn btn-default btn-block" onClick="ansBUer()"> 
				</div>
				<div class="col-md-3">
					 <INPUT Type="submit"  Value="忽略举报" class="btn btn-default btn-block" onClick="neglectA()"> 
				</div>
			</div>
		</div>
		<div class="col-md-1">
		</div>
		</form>
	</div>


</body>
</html>