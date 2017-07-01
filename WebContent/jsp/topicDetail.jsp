<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="up.jsp" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>${topic.topicName }</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
<link href="../css/topic.css" rel="stylesheet">
</head>

<body>
	<div class="container" style="margin-top:0">
		<div class="row clearfix">
			<div class="col-md-12 column topic-detail-banner">
				<img src="${topic.topicBanner }" alt="banner" height="280" />
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-4 column ">
				<div class="topic-avatar">
					<img src="${topic.topicAvatar }" width="150" height="150" />
				</div>
				<span id="topicName" class="topic-name">${topic.topicName }</span>
			</div>
			<div class="follow-topic col-md-2 column">
				<button type="button" class="btn btn-primary"
					style="font-size: 16px;">
					<span class=" glyphicon glyphicon-plus">关注</span>
				</button>
			</div>
			<div class="col-md-3 column" style="height: 50px; line-height: 50px;">
				关注量：${topic.followNumber }
				<button type="button" class="btn btn-small"
					style="margin-left: 50px;">举报</button>
			</div>
			<div class="col-md-3 column" style="height: 50px; line-height: 50px;">
				<input type="search" placeholder="搜索问题"
					style="display: inline-block; border: 1px solid #999; width: 200px; height: 30px; line-height: 30px" />
				<button type="button" class="btn btn-primary">
					<span class=" glyphicon glyphicon-search"></span>
				</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${quesList == '[]' }">
				<div class="row clearfix"
					style="text-align: center; color: #69ca42; height: 200px; line-height: 200px">
					<h3>暂未有人向该话题提问哦~试试发帖吧~</h3>
				</div>
			</c:when>
			<c:otherwise>


				<div class="ques-list">
					<ul id="ques-list-ul">
						<c:forEach var="question" items="${requestScope.quesList }">

							<li>
								<div class="row clearfix">
									<div class="col-md-8 column">
										<a href="#" target="_blank" style="margin-left:50px">${question.quesTitle }</a>
									</div>
									<div class="col-md-2 column">
										<span> <i class=" glyphicon glyphicon-user"></i> <span>
												<a href="#" target="_blank">${question.userId }</a>
										</span>
										</span>
									</div>
									<div class="col-md-2 column">
										<span> ${question.lastAnswerTime } </span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="post-new-ques">
			<form action="/yzwish/topic/createQuestion" method="POST"
				onsubmit="return createQuestion()">
				<input type="text" name="topicId" value="${topic.topicId }"
					style="visibility: hidden" /> <input type="text" name="userId"
					value="${sessionScope.id }" style="visibility: hidden" />

				<div class="row clearfix">
					<div class="col-md-12 column">
						<i class="fa fa-edit fa-large" style="color: #ff6500"></i> <span>发表新帖</span>
					</div>
				</div>
				<div class="row clearfix">
					<div class="col-md-12 column">
						<input id="questionTitle" name="quesTitle" type="text"
							placeholder="请填写标题" />
						<p id="quesTitleHint" style="color: red"></p>
					</div>
				</div>
				<div class="row clearfix">
					<div class="col-md-12 column">
						<textarea id="questionContent" name="quesContent"></textarea>
						<p id="quesContHint" style="color: red"></p>
					</div>
					<div class="col-md-12 column">
						<button type="submit" class="btn btn-primary" style="float: right">发表</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/topic.js"></script>
</body>

</html>