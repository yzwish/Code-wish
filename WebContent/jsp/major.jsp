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
		   
		<div class="majorsyintro">
                <h2>热门专业解析</h2>
                <h4>
                    我们为您带来：
                </h4>
                <h3>专业介绍、核心课程、就业前景、专业院校排名
                </h3>
                <h4>
                    全面的专业解析，为您的职业规划保驾护航
                </h4>
                <p>
                    您想知道的，这儿都有。
                </p>
                <p class="gjdk">
                    赶紧点开左侧来浏览吧~
                </p>
                <span>声明：数据来源于教务部官方数据和高校本科培养计划及部分网络资源</span>
            </div>
            
      </div>
   </div>
</div>   

</body>
</html>
	  
		
		