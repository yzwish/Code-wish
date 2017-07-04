<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大学主页</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3>
				${university.universityName}
			</h3>
			<a href="#">关注</a>
			<a href="#">加入志愿</a>
			
			<table class="table">
				<thead>
				
					<tr>
						<th>
							排名
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
					<tr>
					
						<td>
							${university.rank}
						</td>
						<td>
							${university.provinceName}
						</td>
						<td>
							${university.type}
						</td>
						<td>
						<input type="hidden" name="universityId" value="${universityRankList.universityId}" >
						</td>
					</tr>
					
				</tbody>
			</table>
			<div class="row clearfix">
			    <!-- 左侧导航 -->
				<div class="col-md-2 column">
					<div class="panel-group" id="panel-699894">
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-699894" href="#panel-01">大学简介</a>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-699894" href="#panel-02">招生计划</a>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-699894" href="#panel-03">师资力量</a>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-699894" href="#panel-04">学校评价</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-10 column">
				<!-- 右侧信息展示 -->
				
				
            <div class="tab-content">
                       <% University university = (University)request.getAttribute("university"); 
			              if(university!=null)
			              {
			            	 System.out.println("找到university");
			              
			            %>
					   <div class="tab-pane active" id="panel-01">
					    
						<%
						   String introduction=university.getIntroduction();
						   if(introduction==null)
						   {
						%>
						   <div>暂无大学介绍！</div>
						<%	   
						   }
						   else{
							   System.out.println("找到大学介绍");
						%>
						   
						   <jsp:include page="<%=introduction%>"/>
						<%
						   }
						%>
						
					</div>
					<div class="tab-pane active" id="panel-02">
						<%
						   String enrollmentPlan=university.getEnrollmentPlan();
						   if(enrollmentPlan==null)
						   {
						%>
						   <div>暂无招生计划！</div>
						<%	   
						   }
						   else{
							   System.out.println("找到招生计划");
						%>
						    
						   <jsp:include page="<%=enrollmentPlan%>"/>
						<%
						   }
						%>
						
					</div>
					<div class="tab-pane active" id="panel-03">
						<%
						   String faculty=university.getFaculty();
						   if(faculty==null)
						   {
						%>
						   <div>暂无师资力量</div>
						<%	   
						   }
						   else{
							   System.out.println("找到师资力量");
						%>
						   
						  <jsp:include page="<%=faculty%>"/>
						<%
						   }
						%>
                         
					</div>
					
					<div class="tab-pane active" id="panel-04">
						<%
						String comment=university.getComment();
						  if(comment==null)
						  {
						%>	  
						 <div>暂无大学评价！</div>
						<% 
						  }
						  else
						  {	
							  System.out.println("找到大学评价");
						%>	
						   <jsp:include page="<%=comment%>"/>
						  
                        <% 
								 
						  } 
                         }
                        else
                        {
                        	System.out.println("没有找到该大学信息");
                        
                        }
						%>
					</div>
				</div>
	
				
				
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>