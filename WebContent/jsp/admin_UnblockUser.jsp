<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					账号管理--解封用户
				</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
	<form name="form1" action ="<c:url value="/unblockUser_do"/>" method = "post">	
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-5">
		<label>输入用户id：</label> <input class="form-control" type="text" name="user_id" ><br><br>
		
         			<INPUT Type="submit"  Value="解封" class="btn btn-default btn-block">
		</div>
		<div class="col-md-5">
		<br>
		<br>
		<font style="font-weight: bold;" color=red>${message}</font>
		</div>
		<div class="col-md-1">
		</div>
	</div>
	</form>
</div>


</body>
</html>