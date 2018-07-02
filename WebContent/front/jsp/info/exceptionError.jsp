<%-- 参考：http://www.17sucai.com/pins/19744.html --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>有趣的错误...</title>
<link href="${pageContext.request.contextPath }/front/css/info/exceptionError.css" rel="stylesheet" />
<style type="text/css">
/* 隐藏 按钮 后续添加*/
.padding-big {
	visibility: hidden;
}

.error-message {
	height: 42em;
	margin: 1em 20em 0em 15.5em;
	/*background-color: red;*/
	overflow-x: hidden;
	overflow-y: auto;
	letter-spacing: 2px;
	word-wrap: break-word;
	word-break: break-all;
}

.bxnk-img {
	position: absolute;
	left: 90em;
	top: 35em;
}
</style>
</head>

<body>
	<div class="container" style="margin-top: 8%;">
		<div class="panel margin-big-top">
			<div class="text-center">
				<br>
				<h2 class="padding-top">
					<stong>卧槽！好像出了什么不得了的错误！</stong>
				</h2>
				<div class="">
					<div class="float-left">
						<img src="${pageContext.request.contextPath }/front/img/info/exceptionError/ds-1.gif">
						<div class="alert">呀！好像出错了 yooo！</div>
					</div>
					<div class="float-right">
						<img src="${pageContext.request.contextPath }/front/img/info/exceptionError/ds-2.png" width="260">
					</div>
				</div>
				<div class="padding-big">
					<a href="javascript:;" class="button bg-yellow">返回首页</a> 
					<a href="javascript:;" class="button">保证不打死管理员</a>
				</div>
			</div>
		</div>
		<img class="bxnk-img" src="${pageContext.request.contextPath }/front/img/info/exceptionError/bxnkan.jpg" />
		<div class="error-message">
			${message }
		</div>
	</div>
</body>
</html>
