<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/css/templatemo_style.css" rel="stylesheet" type="text/css">
<title>预知愿——个人中心</title>
<style>
/*
div{
border:1px solid red;
}*/
</style>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		    <div style="margin-bottom:20px">
		    <h1 class="text-center">我的钱包</h1>
		    </div>
			<div class="row clearfix" style="width:1000px;margin:0 auto" >
				<div class="col-md-4 column" style="width:200px">
					<div class="panel-group" id="panel-976353">
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title" data-toggle="collapse" data-parent="#panel-976353" href="#panel-element-497330">虚拟币</a>
							</div>
							<div id="panel-element-497330" class="panel-collapse collapse in">
								<div class="panel-body" style="background:rgba(92,192,222,0.2)">
									<a href="/yzwish/userInfo/showBalance">余额</a>
								</div>
								<div class="panel-body">
									<a href="/yzwish/jsp/deposite.jsp">充值</a>
								</div>
								<div class="panel-body">
									<a href="/yzwish/jsp/transRecoredInfo.jsp">交易记录</a>
								</div>
								<c:if test="${sessionScope.duty=='3' }">
								<div class="panel-body">
									<a href="/yzwish/userInfo/showTodayLimit">提取</a>
								</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8 column" style="margin-left:20px">
					<h3 class="text-left">
						虚拟币账户
					</h3>
					<h3 style="color:rgb(92,192,222);font-size:50px" >
						${requestScope.balance }
						<span style="font-size:15px;color:rgb(92,192,222)">虚拟币</span>
					</h3> 
					<button type="button" id="btn" class="btn btn-lg btn-info" 
					style="font-size:15px;width:170px;padding:10px"
					>虚拟币充值</button>
					
					
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
window.onload=function(){
	$('#btn').click(function(){
		window.location.href="/yzwish/jsp/deposite.jsp";
	});
}					
					
</script>
</html>