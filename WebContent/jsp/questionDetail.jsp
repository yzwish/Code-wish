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
<title>问题主页</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet">
<link href="../css/topic.css" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						var id = "${sessionScope.id}";
						if (id == "") {
							var fi1 = '<button type="button" class="btn btn-primary"'
									+ 'style="margin-top: 20px;"  onclick="followQuestion()">关注问题</button>';
							$("#followQuesItem").html(fi1);
						} else {

							//查询用户是否关注了该问题
							var questionId = $("#questionId").val();
							var userId = id;
							var follow = new Object();
							follow.followedId = questionId;
							follow.followUserId = userId;
							follow.followType = 1;
							var info = JSON.stringify(follow);
							$
									.ajax({
										type : "POST",
										contentType : 'application/json',
										url : "/yzwish/topic/checkFollow",
										data : info,
										success : function(data, status) {
											if (data == "error") {
												var fi = '<button type="button" class="btn btn-primary"'
														+ 'style="margin-top: 20px;"  onclick="followQuestion()">关注问题</button>';
												$("#followQuesItem").html(fi);
											} else {
												var followQuesItem = '<button type="button" class="btn fa fa-check" disabled style="margin-top:20px;">'
														+ '已关注</button>';
												$("#followQuesItem").html(
														followQuesItem);

											}
										},
										error : function() {
											alert("查询出错");
										}
									});
						}

					});
</script>
</head>

<body id="fix-modal-open">
	<div class="container" style="margin-top: 0">
		<div class="question-header">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<h3>
						<a href="/yzwish/topic/showTopic?topicId=${question.topicId }">${topicName }</a>
					</h3>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-2 column">
					<img src="../topicImages/avatar.jpg" width="100" height="100">
					<p>${question.userId }</p>
				</div>
				<div class="col-md-7 column">
					<h3>${question.quesTitle }</h3>
					<p>${question.quesContent }</p>
				</div>
				<div class="col-md-3 column">
					<span id="followQuesItem"> </span>
					<div style="margin-top: 60px;">
						<span>${question.questionCreateTime }</span> <a id="modal-report"
							href="#modal-container-report" 
							data-toggle="modal" style="margin-left: 40px;">举报</a>
					</div>



					<div class="modal fade" id="modal-container-report" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 60px;">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h3 class="modal-title" id="myModalLabel">举报</h3>
								</div>
								<div class="modal-body" style="margin-left:50px;">
									<h4>请选择举报理由</h4>

									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios1" value="广告类" checked>广告类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios2" value="政治有害类"> 政治有害类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios3" value="暴恐类"> 暴恐类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios4" value="淫秽色情类"> 淫秽色情类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios5" value="赌博类"> 赌博类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios6" value="诈骗类" > 诈骗类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="reportReasons"
											id="optionsRadios7" value="其他有害类" > 其他有害类
										</label>
									</div>
									


								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" onclick="reportQuestion()">提交</button>
								</div>
							</div>

						</div>

					</div>
				



				</div>
			</div>
		</div>

		<div class="row clearfix">
			<c:if test="${not empty requestScope.answerList }">

				<c:forEach var="answer" items="${requestScope.answerList }"
					varStatus="status">
					<c:set var="colorIndex" value="${status.index %8}" />


					<div class="answer-item"
						style='background-color:${colors[colorIndex] }'>
						<div class="answer-username">
						
							<a href="#"> ${answer.answerUserId } </a>
							 <span>
								
							<a id='modal-report-ans-${answer.answerId }' href='#modal-container-report-ans-${answer.answerId }'  
							 role="button" class="btn btn-small btn-default report-btn" data-toggle="modal" >举报</a>
							</span>
						</div>
						
						

					<div class="modal fade answer-report-modal" id='modal-container-report-ans-${answer.answerId }' role="dialog"
						aria-labelledby='myModalLabel-${answer.answerId }' aria-hidden="true" style="margin-top: 60px;">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h3 class="modal-title" id='myModalLabel-${answer.answerId }'>举报</h3>
								</div>
								<div class="modal-body" style="margin-left:50px;">
									<h4>请选择举报理由</h4>

									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="广告类" checked>广告类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="政治有害类"> 政治有害类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="暴恐类"> 暴恐类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="淫秽色情类"> 淫秽色情类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="赌博类"> 赌博类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="诈骗类" > 诈骗类
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name='reportReasons-${answer.answerId }'
											 value="其他有害类" > 其他有害类
										</label>
									</div>
									


								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
										<input type="text" id='answerId-${answer.answerId }'
						value="${answer.answerId }" style="display:none" />
									<button type="button" class="btn btn-primary" onclick="reportAnswer(this)">提交</button>
								</div>
							</div>

						</div>

					</div>
						
						
						
						
						
						
						<div class="answer-content">
							<h2>${answer.answerContent }</h2>
						</div>
						<div class="answer-zan">
							<span> ${answer.answerTime }</span> <a class="btn btn-default"
								href="javascript:void();" onclick="approvalAns(${answer.answerId })">
								<i id="approvalNum-${answer.answerId }"  class="fa fa-thumbs-o-up fa-large">${answer.answerApprovalNum }</i></a>
						</div>
					</div>

				</c:forEach>
			</c:if>


		</div>

		<div class="row clearfix" style="margin-top: 50px;">
			<div class="col-md-6 col-md-offset-6 column">
				<button type="button" class="btn btn-info btn-lg">查看更多</button>
			</div>
		</div>
		<div class="row clearfix ques-detail-comm">
			<div class="col-md-2 column">
				<c:choose>
					<c:when test="${not empty sessionScope.id}">
						<img src="../topicImages/avatar.jpg" width="100" height="100">

						<p>${sessionScope.id}</p>
					</c:when>
					<c:otherwise>
						<h4 style="margin: 40px">请先登录</h4>
					</c:otherwise>
				</c:choose>

			</div>
			<div class="col-md-10 column">
				<form action="/yzwish/topic/answerQuestion" method="POST"
					onsubmit="return answerQuestion()">
					<input type="text" id="questionId" name="questionId"
						value="${question.questionId }" style="visibility: hidden" /> <input
						type="text" id="answerUserId" name="answerUserId"
						value="${sessionScope.id }" style="visibility: hidden" />



					<textarea id="answerContent" name="answerContent"
						class="ques-comm-txt"></textarea>
					<p id="answer-hint" style="color: red"></p>
					<div>
						<button type="submit" class="btn btn-primary" style="float: right">评论</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/topic.js"></script>
</body>

</html>