<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/css/templatemo_style.css" rel="stylesheet" type="text/css">
<title>预知愿——个人中心</title>
<style>

div{
border:1px solid rgba(0,0,0,0);
}
#rightpart div{
float:left;
margin-right:17px;
}
.clear{display:block;overflow:hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		    <div style="margin-bottom:20px">
		    <h1 class="text-center">我的钱包</h1>
		    </div>
			<div class="row clearfix" style="width:1000px;margin:0 auto" >
				<div class="col-md-4 column" style="width:200px">
					<div class="panel-group" id="panel-976353">
						<div class="panel panel-default">
							<div class="panel-heading">
								 <a class="panel-title" data-toggle="collapse" data-parent="#panel-976353" href="#panel-element-497330">虚拟币</a>
							</div>
							<div id="panel-element-497330" class="panel-collapse collapse in">
								<div class="panel-body">
									<a href="/yzwish/userInfo/showBalance">余额</a>
								</div>
								<div class="panel-body">
									<a href="/yzwish/jsp/deposite.jsp">充值</a>
								</div>
								<div class="panel-body">
									<a href="/yzwish/jsp/transRecoredInfo.jsp">交易记录</a>
								</div>
								<c:if test="${sessionScope.duty=='3' }">
								<div class="panel-body"  style="background:rgba(92,192,222,0.2)">
									<a href="/yzwish/userInfo/showTodayLimit">提取</a>
								</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8 column" style="margin-left:20px">
					<div>
					<h3 class="text-left">
						提取方式
					</h3>
					<input type="radio" checked="checked"/>
					<span style="font-size:15px" >支付宝</span>
					
					</div>
					
					<div id="rightpart" class="clear">
					<h3 class="text-left">
						提取数量
					</h3>
					<div>
					<input type="radio" checked="checked" id="rmb50"
					name="virtualNum" onclick="changeMoney()"/>
					<span style="font-size:15px">500虚拟币</span>
					</div>
					<div>
					<input type="radio" name="virtualNum" id="rmb100" onclick="changeMoney()"/>
					<span style="font-size:15px">1000虚拟币</span>
					</div>
					<div>
					<input type="radio" name="virtualNum" id="rmb500" onclick="changeMoney()"/>
					<span style="font-size:15px">5000虚拟币</span>
					</div>
					<div class="clear" style="margin-top:10px;margin-bottom:10px">
					<div style="float:left">
					<input type="radio" name="virtualNum" id="rmbsolo" onclick="changeMoney()"/>
					<span style="font-size:15px">自定义</span>
					</div>
					
					<div style="float:left">
					<input class="form-control" type="text" maxlength="10" id="rmbsolonum"
					style="width:150px"/>					
					</div>
					<div style="float:left">
					<span style="font-size:15px">虚拟币</span>
					</div>
					<div style="float:left">
					<span style="font-size:20px;color:red" id="solo_info"></span>
					</div>
				    
				    </div>
					</div>
					
					<div style="text-align:center">
					<h4 style="font-size:20px">			
							<strong>请确认：</strong> 	
							提取<span id="rmb_info">5.0</span>	
							 元，您将消耗
                            <span id="yzb_info">50</span>	    
                                                                                虚拟币
                    </h4> 
                    <h4 style="font-size:20px">			
							<strong>今日已提取：</strong> 	
							<input type="hidden" value="${requestScope.today_draw }" id="today_draw"/>
							<span id="dp_info">${requestScope.today_draw }虚拟币（上限：10,000虚拟币）</span>	
                    </h4> 
                    
                    <div style="margin-top:20px">
					<button type="button" id="btn" class="btn btn-lg btn-info" 
					style="font-size:15px;width:170px;padding:10px"
					>虚拟币提取</button>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
function changeMoney(){
	
	var rmb50=document.getElementById('rmb50');
	var rmb100=document.getElementById('rmb100');
	var rmb500=document.getElementById('rmb500');
	var rmbsolo=document.getElementById('rmbsolo');
	var rmb_info=document.getElementById('rmb_info');
	var yzb_info=document.getElementById('yzb_info');
	var re=/^\d*$/;
	rmb_info.innerHTML='';
	yzb_info.innerHTML='';
	if(rmb50.checked){
		rmb_info.innerHTML='5.0';
		yzb_info.innerHTML='500';
	}
	else if(rmb100.checked){
		rmb_info.innerHTML='10.0';
		yzb_info.innerHTML='1000';
	}
	else if(rmb500.checked){
		rmb_info.innerHTML='50.0';
		yzb_info.innerHTML='5000';
	}
	else if(rmbsolo.checked){
		if($('#solo_info').html()=='请输入数值'||$('#rmbsolonum').val()==''){
			$('#solo_info').html()=='请输入数值';
			rmb_info.innerHTML='0.0';
			yzb_info.innerHTML='0';
		}
		else{
			var str=$('#rmbsolonum').val();
			if(!re.test(str)){
				$('#solo_info').html('请输入整数');
				rmb_info.innerHTML='0.0';
				yzb_info.innerHTML='0';
			}
			else{
				rmb_info.innerHTML=$('#rmbsolonum').val()/100;
				yzb_info.innerHTML=$('#rmbsolonum').val();
			}
			
		}
		
	}
	$('#rmbsolonum').keyup(
	
			function(){
				$('#solo_info').html('');
				if($('#rmbsolonum').val()==''){
					$('#solo_info').html('请输入数值');
				}
				else{
					var str=$('#rmbsolonum').val();
					if(!re.test(str)){
						$('#solo_info').html('请输入整数');
						rmb_info.innerHTML='0.0';
						yzb_info.innerHTML='0';
					}
					else{
						rmb_info.innerHTML=$('#rmbsolonum').val()/100;
						yzb_info.innerHTML=$('#rmbsolonum').val();
					}
				}
			}
	)
}

window.onload=function(){
	$('#btn').click(
	
			
			function(){
				var num=$('#yzb_info').html();
				var today_draw=$('#today_draw').val();
				$.ajax({
					type:'post',
					contentType:'application/x-www-form-urlencoded',
					url:'/yzwish/userInfo/draw',
					data:{
						num:num
					},	
					beforeSend:function(){
						//处理发送之前的检查事务。
						if(num*1<=0){
							alert("请提取大于0的虚拟币数额！");
							return false;
						}
						else if((num*1+today_draw*1)>10000){
							alert("今日提取已达到上限！");
							return false;
						}
					},										
				    success:function(data){
				    	var res = $.parseJSON(data);									    	
				    	if(res[0].result==1)									    	
				    		alert("提取成功！");
				    	else if(res[0].result==2){
				    		alert("余额不足！");
				    	}
				    	else if(res[0].result==3){
				    		alert("服务器错误！");
				    	}				    	
				    },
					error:function(XMLHttpRequest, textStatus, errorThrown){
						//alert("进入error！");
						alert(XMLHttpRequest.status);
		                alert(XMLHttpRequest.readyState);
		                alert(textStatus);
				    }
				}
						);
			}
	);
}
</script>
</html>