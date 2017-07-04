
$( function(){ 
var pc_t=new Object();  //省、大学专业
function initProvince(){
	var data;
	$.ajax({
		type:'get',
		contentType:'application/json',
		url:'/yzwish/userInfo/getPList',
		data:'',	
		beforeSend:function(){
			//处理发送之前的检查事务。
			//alert("发送之前！");
		},										
	    success:function(data){
	    	//alert("返回页面");
	    	console.log(data[0]);
	    	var res = $.parseJSON(data);
	    	var optionsServiceId =""; 
	    	for(j=0;j<res.length;j++){		    	
	    		optionsServiceId=optionsServiceId+'<option value="'+
	    			res[j].provinceId+'">'+res[j].provinceName+'</option>';
	    	}
	    	pc_t[1]=optionsServiceId;
	    	$("#vsNameF").html(pc_t[1]);
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

function initUN(){
	var data;
	$.ajax({
		type:'get',
		contentType:'application/json',
		url:'/yzwish/userInfo/getUList',
		data:'',	
		beforeSend:function(){
			//处理发送之前的检查事务。
			//alert("发送之前！");
		},										
	    success:function(data){
	    	//alert("返回页面");
	    	console.log(data[0]);
	    	var res = $.parseJSON(data);
	    	var optionsServiceId =""; 
	    	for(j=0;j<res.length;j++){		    	
	    		optionsServiceId=optionsServiceId+'<option value="'+
	    			res[j].universityId+'">'+res[j].universityName+'</option>';
	    	}
	    	pc_t[2]=optionsServiceId;
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
function initMajor(){
	var data;
	$.ajax({
		type:'get',
		contentType:'application/json',
		url:'/yzwish/userInfo/getMList',
		data:'',	
		beforeSend:function(){
			//处理发送之前的检查事务。
			//alert("发送之前！");
		},										
	    success:function(data){
	    	//alert("返回页面");
	    	console.log(data[0]);
	    	var res = $.parseJSON(data);
	    	var optionsServiceId =""; 
	    	for(j=0;j<res.length;j++){		    	
	    		optionsServiceId=optionsServiceId+'<option value="'+
	    			res[j].majorId+'">'+res[j].majorName+'</option>';
	    	}
	    	pc_t[3]=optionsServiceId;
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
initProvince(); 
initUN(); 
initMajor(); 

(function(){
$("select[name='vsType']").change(function(){  
	var id='vsName'+$(this).attr('id').substr(6,$(this).attr('id').length-1);
	var value=$(this).val();
    $("#"+id).html(pc_t[value]);
});
})();

//初始化各条志愿信息
function init(){
	//遍历每条tr	
	$("#tbody").find("tr").each(function(){

		 var tr=$(this);
		 var id=tr.attr('id');
		 var nameDuty;
		 var rId;
		 if(id!='f'){
			 nameDuty=$('#nameDuty'+id).val();
			 $('#vsType'+id).val(nameDuty);
			 //装入对应列表信息
			 $('#vsName'+id).html(pc_t[nameDuty]);
			 rId=$('#rId'+id).val();
			 $('#vsName'+id).val(rId);
			 
		 }
		 $('#btnD'+id).click(
					function(){
						var vs=new Object();
						vs.vsId=id;
						var info=JSON.stringify(vs);
						$.ajax({
							type:'post',
							contentType:'application/json',
							url:'/yzwish/userInfo/deleteVolunInfo',
							data:info,	
							beforeSend:function(){
							},										
						    success:function(data){
						    	
						    	console.log(data[0]);
						    	var res = $.parseJSON(data);									    	
						    	if(res[0].result==2)									    	
						    		alert("未知错误");
						    	else if(res[0].result==1){
						    		$('#'+id).remove();
						    	}
						    },
							error:function(XMLHttpRequest, textStatus, errorThrown){
								alert("服务器错误！");
						    }
						}
								);
						
					}
							
					);
		 $('#btnM'+id).click(
					function(){
						var vs=new Object();
						vs.vsId=id;
						vs.nameDuty=$('#vsType'+id).val();
						vs.rId=$('#vsName'+id).val();
						var info=JSON.stringify(vs);
						$.ajax({
							type:'post',
							contentType:'application/json',
							url:'/yzwish/userInfo/modifyVolunInfo',
							data:info,	
							beforeSend:function(){
							},										
						    success:function(data){
						    	
						    	console.log(data[0]);
						    	var res = $.parseJSON(data);									    	
						    	if(res[0].result==2){								    	
						    		alert("该信息已经存在");
						    		$('#vsType'+id).val(nameDuty);
						    		$('#vsName'+id).val(rId);
						    	}
						    	else if(res[0].result==1){
						    		alert("修改成功");
						    	}
						    },
							error:function(XMLHttpRequest, textStatus, errorThrown){
								alert("服务器错误！");
						    }
						}
								);
						
					}
							
					);
	  });
	 
}
setTimeout(init,300);
$('#btnF').click(
		function(){
			var vs=new Object();
			vs.nameDuty=$('#vsTypeF').val();
			vs.rId=$('#vsNameF').val();
			var info=JSON.stringify(vs);
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:'/yzwish/userInfo/addVolunInfo',
				data:info,	
				beforeSend:function(){
				},										
			    success:function(data){
			    	
			    	console.log(data[0]);
			    	var res = $.parseJSON(data);									    	
			    	if(res[0].result==2)									    	
			    		alert("已经添加过该信息");
			    	else if(res[0].result==1){
			    		window.location.href="/yzwish/userInfo/showVolunInfo";//跳转链接
			    	}
			    },
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("服务器错误！");
			    }
			}
					);
			
		}
				
		);

});

  