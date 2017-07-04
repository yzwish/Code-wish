
$( function(){ 


$('#modifyBasicInfo').click(
		function(){
			$('#sbi_info').html('');//清空消息
			//alert("提交表单事件！");
			var duty=$("#duty").val();
			var info='';
			if(duty=='1'){
				var hss=new Object();
				hss.sex=$('#sex').val();
				hss.age=$('#age').val();
				hss.hId=$('#pchs_hs').val();
				info=JSON.stringify(hss);
			}
			else if(duty=='2'||duty=='3'){
				var user=new Object();
				user.sex=$('#sex').val();
				user.age=$('#age').val();
				info=JSON.stringify(user);
			}
			
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:'/yzwish/userInfo/setBasicInfo',
				data:info,	
				beforeSend:function(){
					//处理发送之前的检查事务。
					//alert("发送之前！");
					if(duty=='2'||duty=='3'){
						if($('#sex').val()=='unknown'){
							$('#sbi_info').html('请选择性别');
						return false;
						}
					}
					return true;
				},										
			    success:function(data){
			    	//alert("返回页面");
			    	var msg=document.getElementById("sbi_info");
			    	console.log(data[0]);
			    	var res = $.parseJSON(data);									    	
			    	if(res[0].msg=='1')									    	
			    		msg.innerHTML='修改成功';
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
});
  