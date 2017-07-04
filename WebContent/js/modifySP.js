
$( function(){ 

	$('#op').keyup(
			function(){
				$('#op_info').empty();
				var op=$('#op').val();
				if(op==''){
					$('#op_info').html('不能为空');
				}
				else{
					$('#op_info').html('');
				}
			}						
	);
	$('#sa').keyup(
			function(){
				$('#sa_info').empty();
				var op=$('#sa').val();
				if(op==''){
					$('#sa_info').html('不能为空');
				}
				else{
					$('#sa_info').html('');
				}			
					
			}						
	);
	$('#btnM').click(
			function(){
				$.ajax({
					type:'post',
					contentType:'application/x-www-form-urlencoded',
					url:'/yzwish/userInfo/modifySP',
					data:{
						op:$('#op').val(),
						sp:$('#sp').val(),
						sa:$('#sa').val()
					},	
					beforeSend:function(){
						//处理发送之前的检查事务。
						var op_info=$('#op_info').html();
						var sa_info=$('#sa_info').html();
						if(op_info.replace(/\s/g,"")==''&&sa_info.replace(/\s/g,"")==''){
							return true;
						}
						else						
							return false;
					},										
				    success:function(data){
				    	console.log(data[0]);
				    	var res = $.parseJSON(data);									    	
				    	if(res[0].result=='1'){
				    		alert("修改密保问题成功！请牢记你的密保问题及答案");
				    	}							    			
				    	else if(res[0].result=='2')									    	
				    		$('#op_info').html('原密码错误');
				    	else if(res[0].result=='3')									    	
				    		alert("服务器错误");
				    	
				    },
					error:function(XMLHttpRequest, textStatus, errorThrown){
						//alert("进入error！");
						alert("服务器错误");
				    }
				}
						);
				
			}
					
			);
});
  