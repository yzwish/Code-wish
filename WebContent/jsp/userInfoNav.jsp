<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/message.css" media="screen" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/message.js"></script>
<style>
body{

}
</style>
</head>

  
			<nav class="navbar navbar-default navbar-inverse" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> 
					 <a class="navbar-brand" href="/yzwish">预知愿</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
	                    <c:if test="${sessionScope.duty!='0' }">
						<li>
							 <a href="<%=request.getContextPath()%>/userInfo/showBasicInfo">基础信息设置</a>
						</li>
						
						</c:if>
						
						<c:if test="${sessionScope.duty=='2'||sessionScope.duty=='3' }">
						<li>
							 <a href="<%=request.getContextPath()%>/userInfo/showAuditInfo">审核信息设置</a>
						</li>
						</c:if>
						
						<c:if test="${sessionScope.duty=='1'}">
						<li>
							 <a href="<%=request.getContextPath()%>/userInfo/showVolunInfo">完善信息</a>
						</li>
						</c:if>
						
						<li>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">账号安全管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="/yzwish/jsp/modifyPW.jsp">修改密码</a>
								</li>
								<li>
									 <a href="/yzwish/jsp/modifySP.jsp">修改密保问题</a>
								</li>
							</ul>
						</li>
						<c:if test="${sessionScope.duty!='0' }">
						<li>
							 <a href="/yzwish/userInfo/showBalance">我的钱包</a>
						</li>
						<li>
							 <a href="/yzwish/userInfo/postPage">系统通知</a>
						</li>
						</c:if>
						
						<c:if test="${sessionScope.duty=='0' }">
						<li>
							 <a href="#">管理中心</a>
						</li>
						</c:if>
						
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li>
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						    ${sessionScope.id} 
						    <c:if test="${requestScope.unReadMsgNum!=0}">
						    <span class="unread">${requestScope.unReadMsgNum }</span>
						    </c:if>
						    </a>
							<ul class="dropdown-menu">
								<li>
								     <c:if test="${requestScope.unReadMsgNum==0}">
									 <a href="/yzwish/userInfo/showBasicInfo">个人中心</a>
									 </c:if>
									 <c:if test="${requestScope.unReadMsgNum!=0  }">
									
									 <div style="float:left;margin-left:20px">
									 <a href="/yzwish/userInfo/postPage" >
									 个人中心
									 </a>
									 </div>
									 <div style="float:left">
									 <span class="unread">${requestScope.unReadMsgNum }</span>
									 </div>
									 
									 </c:if>
								</li>
								<li>
									 <a href="#">个人主页</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							 <a href="<%=request.getContextPath()%>/login/logout">退出</a>							
						</li>
					</ul>
				</div>
				
			</nav>

</html>