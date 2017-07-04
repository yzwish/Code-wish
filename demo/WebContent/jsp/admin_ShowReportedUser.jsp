<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>

</head>
<body>

<Script Language="JavaScript"> 
    function blockED() 
    { 
    document.form1.action="blockED_do"; 
    document.form1.submit(); 
    } 
     
    function blockER() 
    { 
    document.form1.action="blockER_do"; 
    document.form1.submit(); 
    } 
    
    function neglect() 
    { 
    document.form1.action="neglectU_do"; 
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
					账号管理--显示举报用户
				</li>
			</ul>
		</div>
	</div>

	
	
		<div class="row">
		
		<form name="form1" action = "" method = "post">	
		
		<div class="col-md-1">
		</div>
		<div class="col-md-10">
			
		
		<div class="row" >
		<div class="col-md-12">
			<div class="panel-group" id="panel-680008" style="background: transparent;">
				<div class="panel panel-default" style="background: transparent;">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-680008" href="#panel-element-479177">按举报人分类</a>
					</div>
					<div id="panel-element-479177" class="panel-collapse collapse in">
						<div class="panel-body">
								<div class="row">
								
								
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
							被举报人id
						</th>
						<th>
							举报理由
						</th>
						<th>
							举报人id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="rerStuL" items="${rerStuL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportU" value="${rerStuL.reportID}"></td>
    			<td>${rerStuL.reportID}</td>
     			<td>${rerStuL.reportedID}</td>
   				<td>${rerStuL.reportReason}</td>
    			<td>${rerStuL.reporterID}</td>
   				<td>${rerStuL.reportTime}</td>
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
							被举报人id
						</th>
						<th>
							举报理由
						</th>
						<th>
							举报人id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="rerTeaL" items="${rerTeaL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportU" value="${rerTeaL.reportID}"></td>
    			<td>${rerTeaL.reportID}</td>
     			<td>${rerTeaL.reportedID}</td>
   				<td>${rerTeaL.reportReason}</td>
    			<td>${rerTeaL.reporterID}</td>
   				<td>${rerTeaL.reportTime}</td>
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
							被举报人id
						</th>
						<th>
							举报理由
						</th>
						<th>
							举报人id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="rerSenL" items="${rerSenL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportU" value="${rerSenL.reportID}"></td>
    			<td>${rerSenL.reportID}</td>
     			<td>${rerSenL.reportedID}</td>
   				<td>${rerSenL.reportReason}</td>
    			<td>${rerSenL.reporterID}</td>
   				<td>${rerSenL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="background: transparent;">
					<div class="panel-heading">
						 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-680008" href="#panel-element-516288">按被举报人分类</a>
					</div>
					<div id="panel-element-516288" class="panel-collapse collapse">
						<div class="panel-body">
							

		<div class="col-md-12">
			<div class="tabbable" id="tabs-171119">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-4" data-toggle="tab">被举报人-高考生</a>
					</li>
					<li>
						<a href="#panel-5" data-toggle="tab">被举报人-老师</a>
					</li>
					<li>
						<a href="#panel-6" data-toggle="tab">被举报人-学长学姐</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-4">
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
							被举报人id
						</th>
						<th>
							举报理由
						</th>
						<th>
							举报人id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="redStuL" items="${redStuL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportU" value="${redStuL.reportID}"></td>
    			<td>${redStuL.reportID}</td>
     			<td>${redStuL.reportedID}</td>
   				<td>${redStuL.reportReason}</td>
    			<td>${redStuL.reporterID}</td>
   				<td>${redStuL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
					<div class="tab-pane" id="panel-5">
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
							被举报人id
						</th>
						<th>
							举报理由
						</th>
						<th>
							举报人id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="redTeaL" items="${redTeaL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportU" value="${redTeaL.reportID}"></td>
    			<td>${redTeaL.reportID}</td>
     			<td>${redTeaL.reportedID}</td>
   				<td>${redTeaL.reportReason}</td>
    			<td>${redTeaL.reporterID}</td>
   				<td>${redTeaL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
					<div class="tab-pane" id="panel-6">
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
							被举报人id
						</th>
						<th>
							举报理由
						</th>
						<th>
							举报人id
						</th>
						<th>
							举报时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="redSenL" items="${redSenL}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
	  			<td><input type="checkbox" name="reportU" value="${redSenL.reportID}"></td>
    			<td>${redSenL.reportID}</td>
     			<td>${redSenL.reportedID}</td>
   				<td>${redSenL.reportReason}</td>
    			<td>${redSenL.reporterID}</td>
   				<td>${redSenL.reportTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>




						</p>
					</div>
				</div>
			</div>
		</div>
	</div>


						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
			<div class="row">
			<label>选择封禁时间：</label>
		<select id="BTime" name="BTime"><option value="3"> </option>
         <option value="3">3天</option>
         <option value="15">15天</option>
         <option value="30">1个月</option>
         <option value="90">3个月</option>
         <option value="9999">永久封禁</option>
         </select>
			</div>
		<br>
		
				<div class="row">
				<div class="col-md-4">
					 
					<INPUT Type="submit"  Value="封禁被举报用户" class="btn btn-default btn-block" onClick="blockED()"> 
				
				</div>
				<div class="col-md-4">
					 <INPUT Type="submit"  Value="封禁举报用户" class="btn btn-default btn-block" onClick="blockER()"> 
				</div>
				<div class="col-md-4">
					 <INPUT Type="submit"  Value="忽略举报" class="btn btn-default btn-block" onClick="neglect()"> 
				</div>
			</div>
	</div>
		
		
			
		<div class="col-md-1">
		</div>
		</form>
		</div>
		



</body>
</html>