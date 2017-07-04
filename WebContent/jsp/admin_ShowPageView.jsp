<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

	<script type="text/javascript">
  var xmlHttp;
  function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
  }  

  function validate() {
    createXMLHttpRequest();
    var time = document.getElementById("time");
    var url = "validation.do?time=" + escape(time.value);
    xmlHttp.open("GET", url, true);
    xmlHttp.onreadystatechange = handleStateChange;    
    xmlHttp.send(null);
  }

  function handleStateChange() {
    if(xmlHttp.readyState == 4){
      if(xmlHttp.status == 200){
        var message = xmlHttp.responseXML.
          getElementsByTagName("message")[0].firstChild.data;
        var messageArea = document.getElementById("results");
        messageArea.innerHTML = "<p>" + message + "</p>";
      }
    }
  }
 </script>
	
</head>
<body>

	<div class="row">
		<div class="col-md-12">
		        <div id="header"><%@ include file="admin_Menu.jsp"%></div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12" >
		        <ul class="breadcrumb" style="background: transparent; font-weight: bold;">
				<li>
					<a href="<%=request.getContextPath()%>/jsp/admin_Menu.jsp">管理员后台</a> <span class="divider">/</span>
				</li>
				<li class="active">
					网站访问数据监测--按年查询
				</li>
			</ul>
		</div>
	</div>
	
	<div class="container-fluid">
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-5">

        <fieldset>
			<div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">选择时间段：</label>
                <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" name="time" size="16" type="text" value="" readonly >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" name="x" value="1">
				<input type="hidden" id="dtp_input2" value="" /><br/>
            </div>
        </fieldset>
       <INPUT Type="submit"  Value="查询 " class="btn btn-default btn-block" onclick="validate();">
    
		</div>
		<div class="col-md-5">
		<label>访问量：</label><div id="results" ></div>
		</div>
		<div class="col-md-1">
		</div>
	</div>
	</div>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script type="text/javascript">

$('.form_date').datetimepicker({
    language:  'zh-CN',
    format: 'yyyy',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 4, //这里就设置了默认视图为年视图
    minView: 4, //设置最小视图为年视图
    forceParse: 0,
    endDate: new Date()
});

</script>

</body>
</html>