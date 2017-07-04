<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title></title>

</head>
<body>

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
					账号管理--显示封禁用户
				</li>
			</ul>
		</div>
	</div>

	
	
		<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-10">
		
		
					<div class="tabbable" id="tabs-467262">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-795371" data-toggle="tab">封禁高考生</a>
					</li>
					<li >
						<a href="#panel-625461" data-toggle="tab">封禁老师</a>
					</li>
					<li >
						<a href="#panel-525461" data-toggle="tab">封禁学长学姐</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-795371">
						<p>
							
								
				<table class="table table-bordered">
				<thead>
					<tr class="warning">
						<th>
							用户id
						</th>
						<th>
							封禁理由
						</th>
						<th>
							封禁天数
						</th>
						<th>
							封禁时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="blockedStu_List" items="${blockedStu_List}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
    			<td>${blockedStu_List.privateUserId}</td>
     			<td>${blockedStu_List.closureReason}</td>
    			<td>${blockedStu_List.closureDay}</td>
   				<td>${blockedStu_List.closureTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>
			
					
						</p>
					</div>
					<div class="tab-pane" id="panel-625461">
						<p>
							
							
				<table class="table table-bordered">
				<thead>
					<tr class="warning">
						<th>
							用户id
						</th>
						<th>
							封禁理由
						</th>
						<th>
							封禁天数
						</th>
						<th>
							封禁时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="blockedTea_List" items="${blockedTea_List}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
    			<td>${blockedTea_List.privateUserId}</td>
     			<td>${blockedTea_List.closureReason}</td>
    			<td>${blockedTea_List.closureDay}</td>
   				<td>${blockedTea_List.closureTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
					<div class="tab-pane " id="panel-525461">
						<p>
							
							
				<table class="table table-bordered">
				<thead>
					<tr class="warning">
						<th>
							用户id
						</th>
						<th>
							封禁理由
						</th>
						<th>
							封禁天数
						</th>
						<th>
							封禁时间
						</th>
					</tr>
				</thead>
				<tbody>
					
		<c:forEach var="blockedSen_List" items="${blockedSen_List}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
    			<td>${blockedSen_List.privateUserId}</td>
     			<td>${blockedSen_List.closureReason}</td>
    			<td>${blockedSen_List.closureDay}</td>
   				<td>${blockedSen_List.closureTime}</td>
        	 </tr>
      </c:forEach>
						
				</tbody>
				</table>



						</p>
					</div>
				</div>
			</div>
		
			
			
			
			
		</div>
		<div class="col-md-1">
		</div>
	</div>





</body>
</html>