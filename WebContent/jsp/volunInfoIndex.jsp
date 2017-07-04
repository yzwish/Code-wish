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
<script src="<%=request.getContextPath()%>/js/volunInfoIndex.js" type="text/javascript"></script>
<style>
/*
div{
border:1px solid red;
}*/
tr th{
border:20px solid rgba(0,0,0,0);
text-align:center;
}
tr td{
border:20px solid rgba(0,0,0,0);
}
tr td select{
text-align:center;
}
.clear{display:block;overflow:hidden;}
</style>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		    <div><jsp:include page="/jsp/userInfoNav.jsp"/></div>
		    <div style="margin:0 auto;width:100%" class="text-center">
			<h1 class="text-center" style="margin-top:50px;font-size:30px">
				志愿信息
			</h1>

			<table style="margin:0 auto;margin-top:20px" >
				<thead style="margin-top:10px">
					<tr>
						<th style="font-size:17px">
							类型
						</th>
						<th style="font-size:17px">
							名称
						</th>
						<th colspan="2" style="font-size:17px">
							操作
						</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${requestScope.vsList}" var="vs">
         
                        <tr id="${vs.vsId }">
                        <td>
                        <input type="hidden" id="nameDuty${vs.vsId }" value="${vs.nameDuty}"/>
                        <select class="form-control" style="width:100px" 
                        name="vsType" id="vsType${vs.vsId}" >
                        <option value="1">
                                                              省/市
                        </option>
                        <option value="2">
                                                              大学
                        </option>
                        <option value="3">
                                                              专业
                        </option>
                        </select>
                        </td>
                   
                        <td>
                        <input type="hidden" id="rId${vs.vsId }" value="${vs.rId}"/>
                        <select class="form-control" style="width:300px"
                        name="vsName" id="vsName${vs.vsId}" >
                        </select>
                        </td>
                 
                        <td colspan="2">
                        <button type="button" id="btnM${vs.vsId}"
                        class="btn btn-default btn-info">修改</button>
                        <button type="button" id="btnD${vs.vsId}"
                         class="btn btn-default btn-info">删除</button>
                        </td>                      
                        </tr>
                        
                    </c:forEach>
                    <tr id="f">
                        <td>
                        <select class="form-control" style="width:100px" 
                        name="vsType" id="vsTypeF">
                        <option value="1">
                                                              省/市
                        </option>
                        <option value="2">
                                                              大学
                        </option>
                        <option value="3">
                                                              专业
                        </option>
                        </select>
                        </td>
                        <td>
                        <select class="form-control" style="width:300px"
                        name="vsName"  id="vsNameF">
                        </select>
                        </td>
                        <td colspan="2" >
                        <button type="button" id="btnF"
                        class="btn btn-default btn-info">添加</button>
                        
                        </td>
                    </tr>
				</tbody>
			</table>
			
			</div>
		</div>
	</div>
</div>
</body>
</html>