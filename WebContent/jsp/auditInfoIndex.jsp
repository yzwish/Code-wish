<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预知愿——个人中心</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/css/templatemo_style.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/uploadPreview.js" type="text/javascript"></script>
<style>
/*
div{
border:1px solid red;
*/

.clear{display:block;overflow:hidden;}
</style>
</head>
<body>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		  <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		    <h1 style="font-size:30px" class="text-center">审核信息设置</h1>

            <!-- 审核信息设置开始 -->

            <form onSubmit="return checkEmpty2()"  enctype="multipart/form-data"
             action="/yzwish/userInfo/setAuditInfo" name="form2" method="post">
            <input type="hidden" value="${sessionScope.duty }" id="duty"/>
            <div style="margin-top:20px">
            
            <div style="margin:0 auto;text-align:center">
         
			<div >				  
				    <div  class="clear">
				    <c:if test="${sessionScope.duty=='2'}">
                    <div style="float:left;margin-left:440px;"><h4>所在大学专业&nbsp;&nbsp;</h4></div>
					</c:if>
					<c:if test="${sessionScope.duty=='3'}">
                    <div style="float:left;margin-left:440px;"><h4>所在大学&nbsp;&nbsp;</h4></div>
					</c:if>
					<div style="float:left">
                        
						<div class="templatemo-input-icon-container">
						        <c:if test="${sessionScope.duty=='2'}">
				            	<div id="provinceUM">
						            
						            <select class="form-control" id="pum_p">
						            <option value="unknown" selected="selected">请选择省/市</option>
						            </select>
						        <div style="height:20px"></div>
						
						        <div style="display:none" id="pumm">
						            <select class="form-control" id="pum_u" name="universityId">
						            </select>						
						        <div style="height:20px"></div>
						            <select class="form-control" id="pum_m" name="majorId">
						            </select>
						        <div style="height:20px"></div>
						        </div>
						        <div>
						        <span id="pum_info" style="color:red;"></span>
						        </div>
						        <div style="height:20px"></div>
						        
						        </div>
						        			
				            	</c:if>
				            	<c:if test="${sessionScope.duty=='3'}">
				            	<div id="provinceCollege">
						             <select class="form-control" id="pc_p">
						             </select>
						        <div style="height:20px"></div>
						             <select class="form-control" id="pc_c" name="ui">
						             </select>
						        <div style="height:20px"></div>
						        </div>						        
				            	</c:if>
				            	
						</div>
				    </div>
					</div>
			</div>
			<div >				  
				    <div  class="clear">
		
				    <c:if test="${sessionScope.duty=='2'}">                   
                        <div style="float:left;margin-left:440px;margin-right:35px"><h4>入学年份&nbsp;&nbsp;</h4></div>
					    <div style="float:left">
					      <div class="templatemo-input-icon-container">
					         <div id="input3">
						      
						      <input type="text" class="form-control" id="sYear" maxlength="4" name="syear"/>
						      <div style="height:20px"></div>
					         </div>
					      </div>
					    </div>
					    <div style="float:left">
					    <span id="syear_info" style="color:red;"></span>
					    </div>
					</c:if>
					<c:if test="${sessionScope.duty=='3'}">
                    <div style="float:left;margin-left:440px"><h4>研究方向&nbsp;&nbsp;</h4></div>
					     <div style="float:left">
					        <div class="templatemo-input-icon-container">
					         <div id="input5">
						     
						     <input type="text" class="form-control" name="resDirection"
						     id="resDirection" maxlength="10"/>
						     <div style="height:20px"></div>
					         </div>
					        </div>
					     </div>
					     <div style="float:left">
					    <span id="rd_info" style="color:red;"></span>
					    </div>
					</c:if>
					<c:if test="${sessionScope.duty=='3'}">
                    <div style="float:left;margin-left:440px;margin-right:35px;margin-right:35px"><h4>职位&nbsp;&nbsp;</h4></div>
					<div style="float:left">
					<div class="templatemo-input-icon-container">
					<div  id="input4">
					
						
						<input type="text" class="form-control" id="position" name="position"
						maxlength="10" placeholder="例如：讲师"/>
						<div style="height:20px"></div>
					
					</div>
					</div>
					</div>
					 <div style="float:left">
					    <span id="position_info" style="color:red;"></span>
					    </div>
					</c:if>
					<c:if test="${sessionScope.duty=='2'}">
					<div style="float:left;margin-left:440px;margin-right:35px"><h4>真实姓名&nbsp;&nbsp;</h4></div>
					</c:if>
					<c:if test="${sessionScope.duty=='3'}">
					<div style="float:left;margin-left:440px"><h4>真实姓名&nbsp;&nbsp;</h4></div>
					</c:if>
					<div style="float:left">
					<div class="templatemo-input-icon-container">
					    <div  id="input6">
						
						<input type="text" class="form-control" id="realName" name="realName"
						 maxlength="10"/>
						<div style="height:20px"></div>
					    </div>
					</div>
					</div>
					 <div style="float:left">
					    <span id="rn_info" style="color:red;"></span>
					    </div>
					<div style="float:left;margin-left:440px"><h4>审核图片&nbsp;&nbsp;</h4></div>
				    <c:if test="${requestScope.status==3}">
					<div style="float:left;">
					    <div class="templatemo-input-icon-container">
					    <div id="input7">
						<span id="picHint"></span>
						
                        <div id="imgdiv">
                        <c:if test="${requestScope.duty==2}">
                        <img id="imgShow" src="/yzwish/${requestScope.cs.picSrc}"  
                        width="200px" height="120px"/>
                         </c:if>
                         <c:if test="${requestScope.duty==3}">
                        <img id="imgShow" src="/yzwish/${requestScope.tea.picSrc}"  
                        width="200px" height="120px"/>
                         </c:if>
                        
                        </div>
                       
                        <input type="file" id="up_img" name="pic"/>
						</div>
					    </div>
					</div>
				    </c:if>
					<c:if test="${requestScope.status==1||requestScope.status==2}">
					<div style="float:left">
					    <div class="templatemo-input-icon-container">
					    <div style="width:100%;height:100%;" >
					        <c:if test="${requestScope.duty==2}">
							<img src="/yzwish/${requestScope.cs.picSrc}" width="120px" height="100px"/>
							</c:if>
							<c:if test="${requestScope.duty==3}">
							<img src="/yzwish/${requestScope.tea.picSrc}" width="120px" height="100px"/>
							</c:if>
						</div>
					    </div>
					</div>
					</c:if>
					<div style="float:left;width:100%"></div>
					<c:if test="${requestScope.duty==3}">
					<div style="float:left;margin-left:440px"><h4>审核状态&nbsp;&nbsp;</h4></div>
					</c:if>
					<c:if test="${requestScope.duty==2}">
					<div style="float:left;margin-left:440px;margin-right:35px"><h4>审核状态&nbsp;&nbsp;</h4></div>
					</c:if>
					<div style="float:left">
					<div class="templatemo-input-icon-container">
					<c:if test="${requestScope.status==1 }">
	                    <h4 style="color:red">审核通过</h4>
					</c:if>
					<c:if test="${requestScope.status==2 }">
	                    <h4 style="color:red">正在审核中</h4>
					</c:if>
					<c:if test="${requestScope.status==3 }">
	                    <h4 style="color:red">审核未通过</h4>
					</c:if>
					</div>
					</div>
				
					<div style="float:left;margin-left:440px">
					    <div class="templatemo-input-icon-container">
					      <div>
                           <h4 id="sbi_info" style="color:red"></h4>
                          </div>     
                          <div style="margin-top:40px;display:none" id="setbutton">
                            <button type="submit" class="btn btn-info btn-lg" id="modifyAuditInfo"
                             style="margin-right:80px">提交申请</button>
                            <button type="reset" class="btn btn-info btn-lg" id="reset">重置</button>
                          </div>
					    </div>
					</div>
				    
				    </div>
				  
			</div>
            
            </div>
            </div>
            
            </form>
        </div>
   </div>
</div>
<script>
   window.onload=function(){
	   var pc_t=new Object();  //省/市 大学
	   pc_t['unknown']='';
	   function initProvinceCollege(){
			var data;
			$.ajax({
				type:'get',
				contentType:'application/json',
				url:'/yzwish/login/getCollegeList',
				data:'',	
				beforeSend:function(){
					//处理发送之前的检查事务。
					//alert("发送之前！");
				},										
			    success:function(data){
			    	//alert("返回页面");
			    	console.log(data[0]);
			    	var res = $.parseJSON(data);
			    	var count=1;
			    	for(j=0;j<res.length;j++){
			    		$("#pc_p").append('<option value="'+
				    			res[j].provinceId+'">'+res[j].provinceName+'</option>');
			    		$("#pum_p").append('<option value="'+
				    			res[j].provinceId+'">'+res[j].provinceName+'</option>');
				    	optionsServiceId =""; 
				    	for(i=0;i<res[j].cList.length;i++){
				    		var cList=res[j].cList;
				    		optionsServiceId=optionsServiceId+"<option value='"+cList[i].universityId+"' >"+cList[i].universityname+"</option>";  	    		
				    	}
				    	pc_t[res[j].provinceId]=optionsServiceId;
				    	if(count==1){		    	
				    		$("#pc_c").html(optionsServiceId);
				    	}
				    	count++;
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
		function getMajor(){
			var data;
			var university=new Object();
			university.universityId=$("#pum_u").val();
			var info=JSON.stringify(university);
			$.ajax({
				type:'post',				
				async: false, 
				contentType:'application/json',
				url:'/yzwish/login/getMajorList',
				data:info,	
				beforeSend:function(){
					//处理发送之前的检查事务。
					//alert("发送之前！");
				},										
			    success:function(data){
			    	//alert("返回页面");
			    	console.log(data[0]);
			    	var res = $.parseJSON(data);
			    	$('#pum_m').html('');
			    	$("#pumm").css("display",'block');
			    	if(res==null||res.length==0){
			    		$('#pum_m').append("<option value='unknown'>抱歉暂未添加该大学开设专业</option>");
			    	}
			    	else{
			    		for(j=0;j<res.length;j++){	
				    		$('#pum_m').append("<option value='"+res[j].majorId+"'>"+res[j].majorName+"</option>");
				    	}
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
	   initProvinceCollege();
	   $('#position').keyup(
				function(){
					$('#position_info').empty();
					if($('#position').val()==''){
						$('#position_info').html('不能为空');
					}
					else{
						$('#position_info').html('');
					}
				}						
		);
		$('#resDirection').keyup(
				function(){
					$('#rd_info').empty();
					if($('#resDirection').val()==''){
						$('#rd_info').html('不能为空');
					}
					else{
						$('#rd_info').html('');
					}
				}						
		);
		$('#realName').keyup(
				function(){
					$('#rn_info').empty();
					if($('#realName').val()==''){
						$('#rn_info').html('不能为空');
					}
					else{
						$('#rn_info').html('');
					}
				}						
		);
		(function(){
			$("#pum_p,#pc_p").change(function(){  
			    var provinceId = $("#pc_p").val();  
			    var provinceId2 = $("#pum_p").val();
			    $("#pc_c").html(pc_t[provinceId]);
			    $("#pum_u").html(pc_t[provinceId2]);
			    $("#pum_info").html('');
			    if(provinceId2!='unknown'){
			    	 getMajor();
			    }
			    else{
			    	$("#pumm").css("display",'none');
			        $("#pum_info").html('不能为空');
			    }
			   
			   
			});
			$("#pum_u").change(function(){  	   
			    getMajor();	  
			    $("#pum_info").html('');
			});
	   })();
	   setTimeout(init,300); 
	   function init(){
	      
	   var status="${requestScope.status}";
	   var duty="${requestScope.duty}";
	
	   var sYear;
	   var position;
	   var realName;
	   var resDirection;
	   var provinceId;
	   var universityId;
	   var majorId;
	   if(duty==2){
		   sYear="${requestScope.cs.sYear}";
		   provinceId="${requestScope.cs.provinceId}";
		   universityId="${requestScope.cs.universityId}";
		   majorId="${requestScope.cs.majorId}";
		   realName="${requestScope.cs.realName}";
		   if(provinceId==null||provinceId=='')
			   provinceId='unknown';
		   if(universityId==null||universityId=='')
			   universityId='unknown';
		   if(majorId==null||majorId=='')
			   majorId='unknown';
		   $('#pum_p').val(provinceId);		  
		   $("#pum_u").html(pc_t[provinceId]);
		   $("#pum_u").val(universityId);
		   getMajor();
		   $("#pum_m").val(majorId);
		   $("#pumm").css('display','block');
		   $('#sYear').val(sYear);
	   }
	   else if(duty==3){
		   position="${requestScope.tea.position}";
		   resDirection="${requestScope.tea.resDirection}";
		   provinceId="${requestScope.tea.provinceId}";
		   universityId="${requestScope.tea.universityId}";
		   realName="${requestScope.tea.realName}";
		   if(provinceId==null||provinceId=='')
			   provinceId='unknown';
		   if(universityId==null||universityId=='')
			   universityId='unknown';
		   $('#pc_p').val(provinceId);
		   $("#pc_c").html(pc_t[provinceId]);
		   $("#pc_c").val(universityId);
		   $('#position').val(position);
		   $('#resDirection').val(resDirection);
	   }
	   $('#realName').val(realName);
	   
	   if(status!=3){
		   if(duty==2){
			   var pum_p=document.getElementById('pum_p');
			   var pum_u=document.getElementById('pum_u');
			   var pum_m=document.getElementById('pum_m');
			   pum_p.disabled=true;
			   pum_u.disabled=true;
			   pum_m.disabled=true; 
			   $("#sYear").attr("readOnly",true); 
			   }
		   else if(duty==3){
			   var pc_p=document.getElementById('pc_p');
			   var pc_c=document.getElementById('pc_c');
			   pc_p.disabled=true;
			   pc_c.disabled=true;
			   $("#position").attr("readOnly",true); 
			   $("#resDirection").attr("readOnly",true); 
		   }
		   $("#realName").attr("readOnly",true); 
		   $("#modifyAuditInfo").css("display",'none');
		   $("#reset").css("display",'none');
	   }
	   
	   var setbutton=document.getElementById("setbutton");
	   setbutton.style.display='block';
	   }
   }
		   function checkEmpty1(){
				var ok=1;
		    	if($('#id_info').html()!='该用户名可以注册'){
		    		ok=0;
		    	}
		    	else if($('#password_info').html()!=''){
		    		ok=0;
		    	}
		    	else if($('#password2_info').html()!=''){
		    		ok=0;
		    	}
		    	else if($('#sa_info').html()!=''){
		    		ok=0;
		    	}
		    	if(ok!=1){
		    		alert("有信息缺失！");
		    		return false;
		    	}
			}
   function checkEmpty2()  
	{  			
	   
        var duty="${requestScope.duty}";
        if(duty==2)
	    {
        	
	    	var provinceId2 = $("#pum_p").val();
	    	if($('#pum_m').val()=='unknown'||$('#pum_m').val()==null){
	 	    	if(provinceId2!='unknown')	 	    	
	 	    		$("#pum_info").html('专业必须选择');	
	 	    }
	 	    else{
	 	    	if(provinceId2!='unknown')	    	
	 	    		$("#pum_info").html('');	
	 	    }
	    	if($('#pum_info').html()!=''||$('#syear_info').html()!=''||
	    			$('#rn_info').html()!='')
	    	{
	    		alert("请完善信息以便审核！");
	    		return false;
	    	}
	    	
	    	 var imgShow=document.getElementById("imgShow");//预览图片	 
			 var file=document.getElementById("up_img");
			 var filevalue=document.form2.pic.value;
			    if(filevalue =="")
			  {
			    	alert("请上传图片以待审核！");
			    	return false;
			    }
			    else{
			    	 var fileSize=file.files[0].size;//文件大小
				    //alert(fileSize);
			    	if(!yanzheng(fileSize))//超过限制大小
			    		return false;
			    }
	    }
	    else if(duty==3)
	    {
	    	if($('#position_info').html()!=''||$('#rd_info').html()!=''||
	    			$('#rn_info').html()!='')
	    	{
	    		alert("请完善信息以便审核！");
	    		return false;
	    	}
	    	var imgShow=document.getElementById("imgShow");//预览图片
		    
			 var file=document.getElementById("up_img");
			 var filevalue=document.form2.pic.value;
			    if(filevalue =="")
			  {
			    	alert("请上传图片以待审核！");
			    	return false;
			    }
			    else{
			    	 var fileSize=file.files[0].size;//文件大小
				     //alert(fileSize);
			    	if(!yanzheng(fileSize))//超过限制大小
			    		return false;
			    }
	    	
	    }

	    //alert($('#pum_u').val());
	    return true;  
	}
   new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });	
 //上传图片大小限制
   function yanzheng(fileSize) 
 {    
        if(fileSize>1024*1024*1)//大小大于1M
       {
           alert("图片不得大于1M！");
           return false;
          }
        else if(fileSize==0){
       	 alert("请上传审核图片");
	         return false;
        }
        else
       	 return true;
 } 
   $('#sYear').keyup(
			function(){
				$('#syear_info').empty();
				var re =  /^[0-9]*$/g;//数字组合
				var date=new Date;
				var year=date.getFullYear(); 
				if($('#sYear').val().length==0){
					$('#syear_info').html('不能为空');
				}
				else if((!re.test($('#sYear').val()))){
					$('#syear_info').html('请输入四位整数');
				}
				else if($('#sYear').val().length<4){
					$('#syear_info').html('请输入四位整数');
				}
				else if($('#sYear').val()<1901||$('#sYear').val()>year.toString()){
					$('#syear_info').html('年份范围:1901-'+year.toString());
				}
				else{
					$('#syear_info').html('');
				}
			}						
	);
	$('#position').keyup(
			function(){
				$('#position_info').empty();
				if($('#position').val()==''){
					$('#position_info').html('不能为空');
				}
				else{
					$('#position_info').html('');
				}
			}						
	);
	$('#resDirection').keyup(
			function(){
				$('#rd_info').empty();
				if($('#resDirection').val()==''){
					$('#rd_info').html('不能为空');
				}
				else{
					$('#rd_info').html('');
				}
			}						
	);
	$('#realName').keyup(
			function(){
				$('#rn_info').empty();
				if($('#realName').val()==''){
					$('#rn_info').html('不能为空');
				}
				else{
					$('#rn_info').html('');
				}
			}						
	);  
</script>
</body>
</html>