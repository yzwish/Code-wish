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
<script src="<%=request.getContextPath()%>/js/userInfoIndex.js" type="text/javascript"></script>
<style>
/*
div{
border:1px solid red;
}
*/
.clear{display:block;overflow:hidden;}
</style>
</head>
<body>

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		  <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		  <h1 style="font-size:30px" class="text-center">基础信息设置</h1>
          
            <!-- 基础信息设置开始 -->
            <c:if test="${sessionScope.duty!='0'}">
            <form>
            <input type="hidden" value="${sessionScope.duty }" id="duty"/>
            <div style="margin-top:40px">
            
            <div style="margin:0 auto;text-align:center">
         
			<div >				  
				    <div  class="clear">
                    <div style="float:left;margin-left:440px;"><h4>性别&nbsp;&nbsp;</h4></div>
					<div style="float:left">
                        
						<div class="templatemo-input-icon-container">
						        <c:if test="${sessionScope.duty=='1'}">
				            	<select class="form-control" name="sex" id="sex">
				            	</c:if>
				            	<c:if test="${sessionScope.duty=='2'&&requestScope.cs.status==2}">
				            	<select class="form-control" name="sex" id="sex" disabled="disabled">
				            	</c:if>
				            	<c:if test="${sessionScope.duty=='2'&&requestScope.cs.status!=2}">
				            	<select class="form-control" name="sex" id="sex">
				            	</c:if>
				            	<c:if test="${sessionScope.duty=='3'&&requestScope.tea.status==2}">
				            	<select class="form-control" name="sex" id="sex" disabled="disabled">
				            	</c:if>
				            	<c:if test="${sessionScope.duty=='3'&&requestScope.tea.status!=2}">
				            	<select class="form-control" name="sex" id="sex" >
				            	</c:if>
				            	<option value="unknown">请选择</option>
				            	<option value="男">男</option>
				            	<option value="女">女</option>
				            	</select>
				        </div>
				        

					</div>
				    </div>
            </div>	
            <div style="margin-top:20px;">
				<div >				  
				    <div  class="clear">
                    <div style="float:left;margin-left:440px;"><h4>年龄&nbsp;&nbsp;</h4></div>
					<div style="float:left">

						<div class="templatemo-input-icon-container">
				            	<input type="text" class="form-control" maxlength="3" id="age"/>
				        </div>

					</div>
				    </div>
                </div>
            </div>
            <c:if test="${sessionScope.duty=='1'}">
            <div style="margin-top:20px;">
				<div id="provinceCityHS" class="clear">
						<div style="float:left;margin-left:440px;"><h4>所在高中&nbsp;&nbsp;</h4></div>
						<div style="float:left;margin-right:20px">
						<select class="form-control" id="pchs_pc">
						<option value="unknown">无(点击选择省/市)</option>
						</select>
						</div>
						<div style="float:left">
						<select class="form-control" id="pchs_hs" name="hId">
						<option value="unknown">无</option>
						</select>
						</div>
						
				</div>
            </div>   
            </c:if> 
            <div>
                 <h4 id="sbi_info" style="color:red"></h4>
            </div>     
            <div style="margin-top:40px;display:none" id="setbutton">
               <button type="button" class="btn btn-info btn-lg" id="modifyBasicInfo"
               style="margin-right:80px">修改</button>
               <button type="reset" class="btn btn-info btn-lg">重置</button>
            </div>
           
            </div>
            </div>
            </form>
            </c:if>
            
        </div>
     </div>
</div>
<script>
   window.onload=function(){
	   var pc = new Object();  //省/市 高中
	   pc['unknown']='<option value="unknown">无</option>';
	   function initProvinceHS(){
	   	var data;
	   	$.ajax({
	   		type:'get',
	   		contentType:'application/json',
	   		url:'/yzwish/login/getHighSchoolList',
	   		data:'',	
	   		beforeSend:function(){
	   			//处理发送之前的检查事务。
	   			//alert("发送之前！");
	   		},										
	   	    success:function(data){
	   	    	//alert("返回页面");
	   	    	console.log(data[0]);
	   	    	var res = $.parseJSON(data);
	   	    	for(j=0;j<res.length;j++){
	   	    		$("#pchs_pc").append('<option value="'+
	   		    			res[j].provinceId+'">'+res[j].provinceName+'</option>');
	   		    	optionsServiceId =""; 
	   		    	for(i=0;i<res[j].highSchoolList.length;i++){
	   		    		var hsList=res[j].highSchoolList;
	   		    		optionsServiceId=optionsServiceId+"<option value='"+hsList[i].hId+"' >"+hsList[i].hname+"</option>";  	    		
	   		    	}
	   		    	pc[res[j].provinceId]=optionsServiceId;
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

	   var duty="${sessionScope.duty}";
	   if(duty==1)	   
		   initProvinceHS(); 


	   (function(){
	   $("#pchs_pc").change(function(){  
	       var provinceId = $("#pchs_pc").val();  
	       $("#pchs_hs").html(pc[provinceId]);
	   });
	   })();
	   
	   setTimeout(init,500); 
	   function init(){
	   var duty="${sessionScope.duty}";
	   var age;
	   var sex;
	   var hId;
	   var provinceId;
	   if(duty==1){
		   age="${requestScope.hss.age}";
		   sex="${requestScope.hss.sex}";
		   hId="${requestScope.hss.hId}";
		   provinceId="${requestScope.hss.provinceId}";
		   if(provinceId==null||provinceId=='')
			   provinceId='unknown';
		   if(hId==null||hId=='')
			   hId='unknown';
		   $('#pchs_pc').val(provinceId);
		   $("#pchs_hs").html(pc[provinceId]);
		   $("#pchs_hs").val(hId);
	   }
	   else if(duty==2){
		   age="${requestScope.cs.age}";
		   sex="${requestScope.cs.sex}";
	   }
	   else if(duty==3){
		   age="${requestScope.tea.age}";		   
		   sex="${requestScope.tea.sex}";
		   
	   }
	   if(sex==null||sex=='')
		   sex='unknown';
	   if(age==0)
		   age='';
	   $('#age').val(age);
	   $('#sex').val(sex);
	   var setbutton=document.getElementById("setbutton");
	   setbutton.style.display='block';
	   }
   }
</script>
</body>
</html>