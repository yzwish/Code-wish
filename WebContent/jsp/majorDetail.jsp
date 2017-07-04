<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.model.Major" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>专业解析</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>


</head>
<body>


<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h2>
				专业解析
			</h2> 
			
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div> <button type="submit" class="btn btn-default">搜索专业</button>
					</form>
					
				</div>
				
			</nav>
			选择分类
			<div class="btn-group">
				 <button class="btn btn-default">默认</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li>
						 <a href="#">操作</a>
					</li>
				</ul>
			</div>
			选择专业
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
			
		</div>
	</div>
	</div>
	<br>
	<br>
	
	<div class="container">
	<div class="row clearfix">
	    <!-- 左侧 -->
	   
		<div class="col-md-3 column">
			<div class="panel-group" id="panel-102348">
			
			
			  <% 
			HashMap<String,ArrayList> classMajor=(HashMap<String,ArrayList>)request.getAttribute("classMajor"); 
			System.out.println("后台专业大类数据完成！");
			//如果无大类信息
			if(classMajor.isEmpty())
			{
			%>
			<div>暂无专业大类信息可查！</div>
			<%
			}
			else{			
				Iterator classes = classMajor.keySet().iterator();
				int classNum = 1;
				
			    while(classes.hasNext())
				{
					
					String classify = (String)classes.next();
			%>		
			    <!-- 组 -->
			    <div class="panel panel-default">
			        <!-- 大类 -->
				 	<div class="panel-heading">
			<%
			        if(classNum==1)			
			        {
			%>      
					<a class="panel-title" data-toggle="collapse" data-parent="#panel-main" 
					 href="#panel-element-<%=classNum%>"><%=classify%></a>
			<%			
			        }
			        else
			        {
			%> 
			        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-main" 
					 href="#panel-element-<%=classNum%>"><%=classify %></a>
			<%       	
			        }
			%>						 
					</div>
			<%
			        if(classNum==1)
			        {
			%>      <!-- 大类下分专业 -->
					<div id="panel-element-<%=classNum%>" class="panel-collapse in">
			<%			
			        }
			        else
			        {
			%> 
			        <div id="panel-element-<%=classNum%>" class="panel-collapse collapse">
			<%       	
			        }
			%>	
					   <div class="panel-body">
					       <table>
			<%
			       //得到专业类的专业列表
			        ArrayList<String> majorList=classMajor.get(classify);
			        System.out.println("开始遍历大类下的专业");
			        for(String majorname:majorList)
			        {
				       
				        %>
			              <form action="/yzwish/major/getMajor.do">
				         <input type="submit" name="majorName" value=<%=majorname%> />
				         </form>
				         <br>
						  
			            <% 
			            System.out.println(majorname);
			         }
      
			%>			 
			               </table>   	
					  </div>
					  </div>	   
					
			<%	     classNum++;
					
				      }
				    }	
			
			%>				
			  
			      
			      
				  </div>
				</div>
				</div>
			</div>  
		
		<!-- 右侧 -->
		<div class="col-md-9 column">
		   
		<div class="tab-content">
	    <% Major major = (Major)request.getAttribute("major"); 
              if(major!=null)
              {
            	 System.out.println("找到major");
              
            %>
            <h3><%=major.getMajorName()%></h3>
            <div class="tab-content">
					<div class="tab-pane active" id="panel-01">
						<%
						   String info=major.getMajorDiscription();
						   if(info==null)
						   {
						%>
						   <div>暂无该专业介绍！</div>
						<%	   
						   }
						   else{
							   System.out.println("找到专业介绍");
						%>
						   
						   <jsp:include page="<%=major.getMajorDiscription()%>"/>
						<%
						   }
						%>
						
					</div>
					<div class="tab-pane active" id="panel-02">
						<%
						   String mainCourses=major.getCoreCourses();
						   if(mainCourses==null)
						   {
						%>
						   <div>暂无该专业核心课程！</div>
						<%	   
						   }
						   else{
							   System.out.println("找到核心课程");
						%>
						    
						   <jsp:include page="<%=major.getCoreCourses()%>"/>
						<%
						   }
						%>
						
					</div>
					<div class="tab-pane active" id="panel-03">
						<!-- 就业前景方向-->
						<%
						   String forward=major.getEmployment() ;
						   if(forward==null)
						   {
						%>
						   <div>暂无就业前景方向</div>
						<%	   
						   }
						   else{
							   System.out.println("找到就业前景方向");
						%>
						   
						  <jsp:include page="<%=forward%>"/>
						<%
						   }
						%>
                         
					</div>
					
					<div class="tab-pane active" id="panel-04">
					<!-- 专业排名 -->
						<%
						  String majorRank = major.getRank();
						  if(majorRank==null)
						  {
						%>	  
						 <div>暂无该专业大学排行信息！</div>
						<% 
						  }
						  else
						  {	
							  System.out.println("找到排行信息");
						%>	
						   <jsp:include page="<%=majorRank%>"/>
						  
                        <% 
								 
						  } 
                         }
                        else
                        {
                        	System.out.println("没有找到该专业信息");
                        
                        }
						%>
					</div>
				</div>
		</div>
      </div>
   </div>
</div>   

</body>
</html>
