<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>  

    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/scripts.js"></script>
  
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>

</head>
<body style="background:url('<%=request.getContextPath()%>/images/Trees.png') no-repeat;"><div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		
		
		
		<h1 class="text-center">
				管理员后台
			</h1>
			
			
			
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="#">预知愿</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
								
								
 						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">网站访问数据监测<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="<%=request.getContextPath()%>/jsp/admin_ShowPageViewBY.jsp">按年查询</a>
								</li>
									<li class="divider">
								</li>
								<li>
									<a href="<%=request.getContextPath()%>/jsp/admin_ShowPageViewBM.jsp">按月查询</a>
								</li>
									<li class="divider">
								</li>
								<li>
									<a href="<%=request.getContextPath()%>/jsp/admin_ShowPageViewBD.jsp">按日查询</a>
								</li>
							</ul>
						</li>
												
						
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户审核<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/showTeacher_do"/>">审核老师</a>
								</li>
									<li class="divider">
								</li>
								<li>
									<a href="<c:url value="/showSenior_do"/>">审核学长学姐</a>
								</li>

							</ul>
						</li>
						
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">账号管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/showBlockedUser_do"/>">显示封禁记录</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="<c:url value="/showReportedUser_do"/>">显示举报记录</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="<%=request.getContextPath()%>/jsp/admin_BlockUser.jsp">封禁用户</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="<%=request.getContextPath()%>/jsp/admin_UnblockUser.jsp">解封用户</a>
								</li>

							</ul>
						</li>
						
						
					<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">话题管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/showTopic_do"/>">审核话题</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="<c:url value="/showReportedTopic_do"/>">管理举报话题</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="<c:url value="/showReportedQuestion_do"/>">管理举报问题</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="<c:url value="/showReportedAnswer_do"/>">管理举报回答</a>
								</li>

							</ul>
						</li>
						
						
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="#">个人中心</a>
						</li>
						<li>
							<a href="#">登出</a>
						</li>
										
						
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
</div>


  

</body></html>