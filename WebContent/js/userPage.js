





$( function(){
$('#addComment').click(
								function(){
									$('#msg').empty();//清空消息
									alert("提交表单事件！");
									var comment=new Object();
									var account=new Object();
									comment=
									account.id=$("#id1").val();
									account.password=$("#password1").val();
									var info=JSON.stringify(account);
									$.ajax({
										type:'post',
										contentType:'application/json',
										url:'/yzwish/jsp/',
										data:info,	
										beforeSend:function(){
											//处理发送之前的检查事务。
											//alert("发送之前！");
											//检查用户名或密码为空
											if($('#id1').val()==''||$('#password1').val()==''){
												alert("用户名或密码不能为空！");
												return false;
											}
										},										 
									    success:function(data){
									    	alert("返回页面");
									    	var msg=document.getElementById("msg");
									    	console.log(data[0]);
									    	var res = $.parseJSON(data);									    	
									    	if(res[0].msg=='0')									    	
									    		msg.innerHTML='用户名或密码错误';//跳出验证错误信息
									    	//用户名密码验证成功
									    	else if(res[0].msg=='1'){
									    		if(res[0].from!=null){
									    			window.location.href="/yzwish/jsp/index.jsp?from="+res[0].from;//跳转链接
									    		}
									    		else{
									    			window.location.href="/yzwish/jsp/index.jsp?from=null";//跳转链接
									    		}
									    	}
									    	else if(res[0].msg=='3'){
									    		msg.innerHTML='服务器错误';//跳出验证错误信息
									    	}
									    	
									    },
										error:function(XMLHttpRequest, textStatus, errorThrown){
											alert("进入error！");
											alert(XMLHttpRequest.status);
							                alert(XMLHttpRequest.readyState);
							                alert(textStatus);
									    }
									}
											);
									
								}
										
								);
});