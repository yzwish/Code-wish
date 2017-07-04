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
<title>话题首页</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/topic.css" rel="stylesheet">

</head>

<body id="fix-modal-open">
	<div class="container" style="min-height:600px;">
		<div class="row clearfix">
			<div class="col-md-2 colmn topic-lists-left">
				<span class="topic-head-title"
					style="display: block; margin: 40px 0 20px 0">热门话题</span>
				<ul class="hot-topic-list list-unstyled">
					<c:if test="${hotTopicList !='[]' }">
					<c:forEach var="topic" items="${requestScope.hotTopicList }">
					<li><a href="/yzwish/topic/showTopic?topicId=${topic.topicId }" target="_blank">${topic.topicName }</a></li>
					
					</c:forEach>
					</c:if>
					

				</ul>
			
			</div>
			<div class="col-md-10 column">

				<div class="recomm-topics-header">
					<div class="row clearfix">
						<div class="col-md-2 column">
							<span class="topic-head-title">推荐话题</span>
						</div>
						<div class="col-md-6 column">
							<input type="search" placeholder="搜索话题" id="search-topic" oninput="searchTopic()" onpropertychange="searchTopic()"
								style="display: inline-block; border: 1px solid #999; width: 100%; height: 30px; line-height: 30px" />
							<div id="search-res-div">
							
													
								<ul id="search-res-list">
								</ul>
					
							</div>
						</div>
						<div class="col-md-2 column"
							style="margin-left: 0; padding-left: 0;">

							<button type="button" class="btn btn-primary" onclick="searchTopic()">
								<span class=" glyphicon glyphicon-search">搜索话题</span>
							</button>
						</div>
						<div class="col-md-2 column">
							<a id="modal-createTopic" href="#modal-container-createTopic"
								role="button" class="btn  btn-success glyphicon glyphicon-plus"
								data-toggle="modal" style="font-size: 16px;">发起话题</a>
						</div>
						<div class="modal fade" id="modal-container-createTopic"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
							style="margin-top: 60px;">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">×</button>
										<h4 class="modal-title" id="myModalLabel">发起话题</h4>
									</div>
									<div class="modal-body">

										<div class="row clearfix">
											<div class="col-md-12 column">
												<form class="form-horizontal" role="form"
													action="/yzwish/topic/createTopic" method="POST" onsubmit="return checkPostTopic()"
													enctype="multipart/form-data">
													<div class="form-group">
														<label for="topicName" class="col-md-2 control-label">话题名称</label>
														<div class="col-md-10">
															<input type="text" class="form-control" id="topicName"
																name="topicName" />
																<p id="topicName-hint" style="color:red;"></p>
														</div>
													</div>
													<div class="form-group">
														<label for="topicDiscription"
															class="col-md-2 control-label">话题简介</label>
														<div class="col-md-10">
															<input type="text" class="form-control"
																id="topicDiscription" name="topicDiscription" />
																<p id="topicDiscription-hint" style="color:red;"></p>
														</div>
													</div>
													<div class="form-group">
														<label for="topicBanner" class="col-md-2 control-label">话题海报</label>
														<div class="col-md-10">
															<div id="topic-banner-div">
																<img id="topic-banner-show" width="100%" height="120px" />
															</div>
															<input type="file" class="form-control" id="bannerPic"
																name="bannerPic" style="border: none" />
																<p id="topicBanner-hint" style="color:red;"></p>
														</div>
													</div>
													<div class="form-group">
														<label for="topicAvatar" class="col-md-2 control-label">话题头像</label>
														<div class="col-md-10">
															<div id="topic-avatar-div">
																<img id="topic-avatar-show" width="100px" height="100px" />
															</div>
															<input type="file" class="form-control" id="avatarPic"
																name="avatarPic" style="border: none" />
																<p id="topicAvatar-hint" style="color:red;"></p>
														</div>
													</div>
													<div class="modal-footer">
														<button type="reset" class="btn btn-default"
															data-dismiss="modal">关闭</button>
														<button type="submit" class="btn btn-primary">保存</button>
													</div>

												</form>
											</div>
										</div>

									</div>

								</div>

							</div>

						</div>




					</div>
					<div class="right-sec clearfix">
						<c:if test="${recommtTopicList!=null }">
						<c:forEach var="topic" items="${requestScope.recommtTopicList }">
						<div class="recomm-topics-list">
					
												<a href="/yzwish/topic/showTopic?topicId=${topic.topicId }" target="_blank" class="recomm-topics-img"> <img
								src="${topic.topicAvatar }"
								width="95" height="95" alt="${topic.topicName }">
							</a>
							<div class="recomm-topics">
								<p>
									<a href="/yzwish/topic/showTopic?topicId=${topic.topicId }" target="_blank" class="recomm-topic-name">${topic.topicName }</a>
								</p>
								<p class="recomm-topic-intro">${topic.topicDiscription }</p>
								<span class="follow-item">
									<p class=" glyphicon glyphicon-user">关注量${topic.followNumber }</p> 
								</span>
							</div>
						</div>
					</c:forEach>
						
						</c:if>
					
						
						

					</div>
				</div>
			</div>
		</div>
		</div>
		<jsp:include page="down.jsp" />
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/uploadPreview.js" type="text/javascript"></script>
		<script src="../js/topic.js" type="text/javascript"></script>
		<script>
			$(document).ready(function() {
				new uploadPreview({
					UpBtn : "bannerPic",
					DivShow : "topic-banner-div",
					ImgShow : "topic-banner-show"
				});
				new uploadPreview({
					UpBtn : "avatarPic",
					DivShow : "topic-avatar-div",
					ImgShow : "topic-avatar-show"
				});
			})
		</script>
</body>

</html>