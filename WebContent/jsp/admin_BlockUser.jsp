<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title></title>

</head>
<body>
<Script Language="JavaScript"> 
    function bu() 
    { 
    document.form1.action="<c:url value="/blockUser_do"/>"; 
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
					账号管理--封禁用户
				</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
	
	
	
	<div class="row">
	<form name="form1" action = "" method = "post">	
		<div class="col-md-1">
		</div>
		<div class="col-md-5">
		<label>输入用户id：</label> <input class="form-control" type="text" name="user_id" ><br><br>
		<label>选择理由：</label>&nbsp;&nbsp;&nbsp;&nbsp;
		<select  id="Breason" name="Breason">
         <option value="刷屏">刷屏</option>
         <option value="信息不属实">信息不属实</option>
         </select><br><br>
         			<div class="row">
		&nbsp;&nbsp;&nbsp;<label>选择封禁时间：</label>
		<select id="BTime" name="BTime">
         <option value="3">3天</option>
         <option value="15">15天</option>
         <option value="30">1个月</option>
         <option value="90">3个月</option>
         <option value="9999">永久封禁</option>
         </select>
			</div>
					<INPUT Type="submit"  Value="封禁 " class="btn btn-default btn-block" onClick="bu()">
		</div>
		<div class="col-md-5">
		<br>
		<br>
		<font style="font-weight: bold;" color=red>${message}</font>
		</div>
		<div class="col-md-1">
		</div>
		</form>
	</div>
	
</div>


</body>
</html>