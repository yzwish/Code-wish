<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

	<title>登录/注册</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/bootstrap-social.css" rel="stylesheet" type="text/css">	
	<link href="<%=request.getContextPath()%>/css/templatemo_style.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/colrank.css" rel='stylesheet' type='text/css' />
    <link href="<%=request.getContextPath()%>/css/nav.css" rel='stylesheet' type='text/css' />
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <link href="<%=request.getContextPath()%>/css/lbt.css" rel='stylesheet' type='text/css' />
    <script src="<%=request.getContextPath()%>/js/lbt.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.js" type="text/javascript"></script>
	<style type="text/css">
	/*
	div{
	border:1px solid red;
	}
	*/
	.left{
	float:left;	
	}
	.clear{display:block;overflow:hidden;}
	.center-in-center{   
            position: absolute;   
            top: 50%;   
            left: 50%;   
            -webkit-transform: translate(-50%, -50%);   
            -moz-transform: translate(-50%, -50%);   
            -ms-transform: translate(-50%, -50%);   
            -o-transform: translate(-50%, -50%);   
            transform: translate(-50%, -50%);   
     }   
	</style>	
</head>
<body class="templatemo-bg-image-1">

	 <div class="header-top">
	    <div class="inner">
			<div class="siteLogo">
			<a href="<%=request.getContextPath()%>/"></a>
			</div>
            <div class="navT">
                <ul>
                    <li><a href="#">高校排行</a></li>
                    <li><a href="#">专业解析</a></li>
                    <li>
                        <a href="#">学长问答</a> 
                    </li>
                    <li>
                        <a href="#">权威咨询</a> 
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/jsp/transstu.jsp">关于插班生</a> 
                    </li>
                </ul>
            </div>
            
          
            <div class="user fr">
				  <!--  <a href="" class="log login">登录</a>
                  <a href="" class="log signUp" onclick="return false;">登录/注册</a>-->
            </div>
              
        
        </div>
	</div>

	    <div class="container">
	   
		<div class="col-md-12">	
		<%String from=request.getParameter("from"); %>
		<%//request.setAttribute("uri", request.getParameter("uri")); %>
			<form id=f1 action="/yzwish/login/checkSafetyInfo" method="post"
			class="form-horizontal templatemo-login-form-2" role="form" onSubmit="return checkForm()">
					<input type=hidden name="from" id="from" value="<%=from %>"/>
					<div class="col-md-12" 
					style="margin:0 auto;margin-top:70px;width:100%;height:350px;background-color: rgba(13,13,13,0.25);">
						
					
						<div id=login-1 class="col-md-12" style="margin-left:70px;width:400px">
				        <div class="form-group">
				          <div class="col-md-12">
				            <div style="text-align:center;margin-top:20px">
						    <span style="color:#fff;font-size:40px">忘记密码</span>
						    </div>		          	
				           
				            <div class="templatemo-input-icon-container clear"  style="margin-top:20px">
				                <div class="left"  style="margin-right:22px;margin-left:20px"> 
				                <span>用户名</span>
				                </div>
				                <div class="left">
				            	<input type="text" class="form-control" name="id" id="id" placeholder="请输入待找回的用户名">
				                </div>
				            	
				            </div>		            		            		            
				          </div>              
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">          	
				           
				            <div class="templatemo-input-icon-container clear">
				                <div class="left" style="margin-right:10px;margin-left:20px"><span>密保问题</span></div>
				                <div class="left">
				            	<select id="sp" name="sp" class="form-control" style="width:190px">
                                <option value="1">你的母亲名字是</option>

                                </select>
				                </div>
				            	
				            </div>		            		            		            
				          </div>              
				        </div>
				       <div class="form-group">
				          <div class="col-md-12">          	
				           
				            <div class="templatemo-input-icon-container clear">
				                <div class="left" style="margin-right:10px;margin-left:20px"><span>密保答案</span></div>
				                <div class="left">
				            	<input type="text" class="form-control" name="sa" id="sa" placeholder="">
				                </div>
				            	
				            </div>		            		            		            
				          </div>              
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">          	
				           
				            <div class="templatemo-input-icon-container clear">
				                 <button type="submit" style="width:160px;margin-left:100px;text-align:center"
				                 class="btn btn-warning">确认</button>
				                 
				            	
				            </div>		            		            		            
				          </div>              
				        </div>
				        
				        </div>
					</div>
			</form>
			
		
		</div>				 	   		      
	</div>
	<div style="height:170px"></div>
    <jsp:include page="down.jsp"/>	
<script>
function checkForm(){
	if($('#id').val()==''||$('#sa').val()==''){
		alert('请填写信息！');
		return false;
	}
	else
		return true
		
}
window.onload=function(){
	var msg="${requestScope.msg}";
	if(msg!=null&&msg!='')
		alert(msg);
}
</script>    
</body>

</html>