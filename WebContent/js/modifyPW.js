
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
	$('#rnp,#np').keyup(
			function(){
				$('#np_info').empty();
				$('#rnp_info').empty();
				var np=$('#np').val();
				var rnp=$('#rnp').val();	
				var ok=1;
				if(np==''){
					$('#np_info').html('不能为空');
					ok=0;
				}
				if(rnp==''){
					$('#rnp_info').html('不能为空');									
					ok=0;
				}	
				if(np!=rnp){
					$('#np_info').html('两次密码不同');
					$('#rnp_info').html('两次密码不同');
					ok=0;
				}	
				if(np.length<5||np.length>20){
					$('#np_info').html('5-20个字符');
					ok=0;
				}							
				if(ok==1){
					$('#np_info').html('');
				}				
					
			}						
	);
	$('#btnM').click(
			function(){
				$.ajax({
					type:'post',
					contentType:'application/x-www-form-urlencoded',
					url:'/yzwish/userInfo/modifyPW',
					data:{
						op:$('#op').val(),
						np:$('#np').val()
					},	
					beforeSend:function(){
						//处理发送之前的检查事务。
						var op_info=$('#op_info').html();
						var np_info=$('#np_info').html();
					    var rnp_info=$('#rnp_info').html();
						if(op_info.replace(/\s/g,"")==''&&np_info.replace(/\s/g,"")==''&&
							rnp_info.replace(/\s/g,"")==''){
							return true;
						}
						else						
							return false;
					},										
				    success:function(data){
				    	console.log(data[0]);
				    	var res = $.parseJSON(data);									    	
				    	if(res[0].result=='1'){
				    		alert("密码修改成功！返回登录界面");
				    		window.location.href="/yzwish/userInfo/logout";
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
  