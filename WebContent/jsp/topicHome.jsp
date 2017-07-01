<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-2 colmn topic-lists-left">
				<span class="topic-head-title"
					style="display: block; margin: 40px 0 20px 0">热门话题</span>
				<ul class="hot-topic-list list-unstyled">
					<li><a href="#">清华大学</a></li>
					<li><a href="#">北京大学</a></li>
					<li><a href="#">复旦大学</a></li>
					<li><a href="#">上海交通大学</a></li>
					<li><a href="#">浙江大学</a></li>

				</ul>
				<button type="button" class="btn btn-info">查看更多</button>
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
						<div class="recomm-topics-list">
							<a href="#" class="recomm-topics-img"> <img
								src="https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=ada1d39d760ef9235f4abebe7c882055&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Fce2e42a7d933c8953198e931d71373f0830200a4.jpg"
								width="95" height="95" alt="清华大学">
							</a>
							<div class="recomm-topics">
								<p>
									<a href="#" class="recomm-topic-name">清华大学</a>
								</p>
								<p class="recomm-topic-intro">自强不息，厚德载物</p>
								<span class="follow-item">
									<p class=" glyphicon glyphicon-user">关注量999</p> <a href="#">关注</a>
								</span>
							</div>
						</div>
						<div class="recomm-topics-list">
							<a href="#" class="recomm-topics-img"> <img
								src="https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=a5011174541c1fc21c92f07e1bada5b2&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2F728b4710b912c8fc95e9ae6efb039245d688217e.jpg"
								width="95" height="95" alt="北京大学">
							</a>
							<div class="recomm-topics">
								<p>
									<a href="#" class="recomm-topic-name">北京大学</a>
								</p>
								<p class="recomm-topic-intro">爱国进步民主科学</p>
								<span class="follow-item">
									<p class=" glyphicon glyphicon-user">关注量999</p> <a href="#">关注</a>
								</span>
							</div>
						</div>
						<div class="recomm-topics-list">
							<a href="#" class="recomm-topics-img"> <img
								src="https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=5d3c1c44e331f62efa65a68a4d5dd999&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2F8644ebf81a4c510fb0e434a86159252dd42aa564.jpg"
								width="95" height="95" alt="复旦大学">
							</a>
							<div class="recomm-topics">
								<p>
									<a href="#" class="recomm-topic-name">复旦大学</a>
								</p>
								<p class="recomm-topic-intro">博学而笃志，切问而近思</p>
								<span class="follow-item">
									<p class=" glyphicon glyphicon-user">关注量999</p> <a href="#">关注</a>
								</span>
							</div>
						</div>
						<div class="recomm-topics-list">
							<a href="#" class="recomm-topics-img"> <img
								src="https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=e1ba8b1192f3920f82b7c5082c5d8395&wh_rate=null&src=http%3A%2F%2Ftieba.baidu.com%2Ftb%2Fcms%2Fngmis%2Ffile_1415349880335.jpg"
								width="95" height="95" alt="上海交通大学">
							</a>
							<div class="recomm-topics">
								<p>
									<a href="#" class="recomm-topic-name">上海交通大学</a>
								</p>
								<p class="recomm-topic-intro">饮水思源，爱国荣校</p>
								<span class="follow-item">
									<p class=" glyphicon glyphicon-user">关注量999</p> <a href="#">关注</a>
								</span>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
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