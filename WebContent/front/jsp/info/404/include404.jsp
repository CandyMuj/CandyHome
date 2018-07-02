<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>页面不见了...</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/front/plugin/jQuery/jquery-3.3.1.min.js" ></script>
<style type="text/css">
	* {
		margin: 0px;
		padding: 0px;
	}
</style>
</head>
<body>
	<div id="load404"></div>
</body>
<script type="text/javascript">
	// 设置错误页面的个数
	var count = 3;
	var random = Math.floor(count * Math.random());
	// console.info(random);
	
	// 页面根路径
	var rootPath = "${pageContext.request.contextPath }/front/jsp/info/404/";
	var htmls = [
		'404-1.html',
		'404-2.html',
		'404-3.jsp'
	];
	// 开始随机加载错误页面
	$('#load404').load(rootPath + htmls[random]);
	
</script>
</html>