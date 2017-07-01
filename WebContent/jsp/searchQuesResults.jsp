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
<title>搜索结果</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
<link href="../css/topic.css" rel="stylesheet">
</head>

<body>
	<div class="container" style="margin-top: 0">
		<div class="ques-list">
			<h4>搜索结果：</h4>

			<c:choose>
				<c:when test="${searchQuesList == '[]' }">
					<div class="row clearfix"
						style="text-align: center; color: #69ca42; height: 200px; line-height: 200px">
						<h3>绞尽脑汁也没搜索到相关问题⊙﹏⊙|||</h3>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="question" items="${requestScope.searchQuesList }">
						<ul id="ques-list-ul">
							<li>
								<div class="row clearfix">
									<div class="col-md-8 column">
										<span class="answer-count">${question.answerCount }</span> <a
											href="/yzwish/topic/showQA?questionId=${question.questionId }"
											target="_blank" style="margin-left: 50px">${question.quesTitle }</a>
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
						</ul>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>