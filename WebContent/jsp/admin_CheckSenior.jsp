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
    document.form1.action="checkSPass_do"; 
    document.form1.submit(); 
    } 
     
    function notpass() 
    { 
    document.form1.action="checkSNotPass_do"; 
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
					用户审核--学长学姐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=green>${message1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;" color=blue>${message2}</font>
				</li>
			</ul>
		</div>
	</div>

	
	
		<div class="row">
		<div class="col-md-1">
		</div>
		
		
		<form name="form1" action = "" method = "post">	
		<div class="col-md-10">
			<table class="table">
				<thead>
					<tr class="warning">
						<th>
							 
						</th>
						<th>
							用户id 
						</th>
						<th>
							真实姓名
						</th>
						<th>
							省
						</th>
						<th>
							大学
						</th>
						<th>
							专业
						</th>
						<th>
							入学年份
						</th>
						<th>
							审核图片
						</th>
					</tr>
				</thead>
				<tbody>
				
				
				 <c:forEach var="senior_List" items="${senior_List}" varStatus="status">
	  	<c:if test="${status.count%2==0}"><tr class="active">
	  	</c:if>
	  	<c:if test="${status.count%2!=0}"><tr class="success">
	  	</c:if>
				<td><input type="checkbox" name="checkSen" value="${senior_List.id}"></td>
    			<td>${senior_List.id}</td>
     			<td>${senior_List.realName}</td>
    			<td>${senior_List.provinceName}</td>
   				<td>${senior_List.universityName}</td>
       			<td>${senior_List.majorName}</td>
				<td>${senior_List.syear}</td>
        	    <td><img class="pimg" src="<%=request.getContextPath()%>/${senior_List.picSrc}" width="100" height="100"/></td>
        	 </tr>
      </c:forEach>
      
      
      				</tbody>
			</table>
			
			
			<div class="row">
				<div class="col-md-6">
				</div>
				<div class="col-md-6">
								<label>选择不通过理由：</label>
				<select id="Sreason" name="Sreason">
         <option value="信息不属实">信息不属实</option>
         <option value="照片不清晰">照片不清晰</option>
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