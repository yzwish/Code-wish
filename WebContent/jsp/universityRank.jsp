<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高校风云榜</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3>
				大学排行
			</h3> 
			
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div> <button type="submit" class="btn btn-default">搜索大学</button>
					</form>
					
				</div>
				
			</nav>
			选择省份
			<div class="btn-group">
			     <from></from>
				 <button class="btn btn-default">默认</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li>
						 <a href="#">操作</a>
					</li>
				</ul>
			</div>
			选择类型
			<div class="btn-group">
				 <button class="btn btn-default">默认</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li>
						 <a href="#">操作</a>
					</li>
				</ul>
			</div>
			排序方式
			<div class="btn-group">
				 <button class="btn btn-default">默认</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li>
						 <a href="#">按排名</a>
					</li>
					<li>
						 <a href="#">按关注度</a>
					</li>
					<li class="divider">
					</li>
					<li>
						 <a href="#">默认</a>
					</li>
				</ul>
			</div>
			<table class="table">
				<thead>
				
					<tr>
						<th>
							排名
						</th>
						<th>
							学校
						</th>
						<th>
							属地
						</th>
						<th>
							类型
						</th>
					</tr>
				
				</thead>
				<tbody>
				 <c:forEach var="universityRankList" items="${universityRankList}">
					<tr>
					
						 <td>
							${universityRankList.rank}
						 </td>
						 <td>
                         <form action ="/yzwish/university/getUniversityDetail.do" method = "post">
                         ${universityRankList.universityName}
                         <input type="submit" value="进入主页" >
                         <input type="hidden" name="universityId" value="${universityRankList.universityId}" >
                         </form>
                        </td>
						<td>
							${universityRankList.provinceName}
						</td>
						<td>
							${universityRankList.type}
						</td>
						<td>
						<input type="hidden" name="universityId" value="${universityRankList.universityId}" >
						</td>
					</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>
	</div>
</div>

</body>
</html>