<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>

</head>
<body>

<Script Language="JavaScript"> 
    function pass() 
    { 
    document.form1.action="checkTOPPass_do"; 
    document.form1.submit(); 
    } 
     
    function notpass() 
    { 
    document.form1.action="checkTOPNotPass_do"; 
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
					话题管理--审核话题
				</li>
			</ul>
		</div>
	</div>

	
	
		<div class="row">
		<form name="form1" action = "" method = "post">	
		<div class="col-md-1">
		</div>
		<div class="col-md-10">
			<table class="table table-bordered" >
				<thead>
					<tr class="warning">
						<th></th>
						<th>话题编号</th>
						<th>话题名称</th>
						<th>话题简介</th>
						<th>话题头像</th>
						<th>话题Banner</th>
					
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="topic_List" items="${topic_List}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
				<td><input type="checkbox" name="checkTop" value="${topic_List.topicId}"></td>
    			<td>${topic_List.topicId}</td>
     			<td>${topic_List.topicName}</td>
    			<td>${topic_List.topicDiscription}</td>
    			<td><img class="pimg" src="<%=request.getContextPath()%>/${topic_List.topicAvatar}" width="100" height="100"/></td>
    			<td><img class="pimg" src="<%=request.getContextPath()%>/${topic_List.topicBanner}" width="100" height="100"/></td>
        	    </tr>
      </c:forEach>
      
				</tbody>
			</table>
			
			
			<div class="row">
				<div class="col-md-6">
				</div>
				<div class="col-md-6">
								<label>选择不通过理由：</label>
				<select id="Treason" name="Treason">
         <option value="话题名称未通过">话题名称未通过</option>
         <option value="话题头像未通过">话题头像未通过</option>
         <option value="话题banner未通过">话题banner未通过</option>
         </select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					 
					<INPUT Type="submit"  Value="通过 " class="btn btn-default btn-block" onClick="pass()"> 
				</div>
				<div class="col-md-6">
					 
					<INPUT type="submit" Value="不通过 " class="btn btn-default btn-block" onClick="notpass()">
				</div>
			</div>
		</div>
		<div class="col-md-1">
		</div>
		</form>
	</div>


</body>
</html>