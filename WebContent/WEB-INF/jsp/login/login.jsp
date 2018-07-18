<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>[CandyHome]欢迎您</title>
<link href="${pageContext.request.contextPath }/front/infRes/login/css/default.css" rel="stylesheet" type="text/css" />
<!--必要样式-->
<link href="${pageContext.request.contextPath }/front/infRes/login/css/styles.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/front/infRes/login/css/demo.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/front/infRes/login/css/loaders.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath }/front/infRes/login/layui/css/layui.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/front/plugin/jQuery/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/front/infRes/login/js/jquery-ui.min.js" type="text/javascript"></script>
<script src='${pageContext.request.contextPath }/front/infRes/login/js/stopExecutionOnTimeout.js?t=1' type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/front/infRes/login/layui/layui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/front/infRes/login/js/Particleground.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/front/infRes/login/js/Treatment.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/front/infRes/login/js/jquery.mockjax.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath }/front/infRes/login/css/customlogin.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/front/infRes/login/js/customlogin.js" type="text/javascript"></script>
</head>
<body>
	<div class='login'>
		<div class='login_title'>
			<span id="loginForm" data-value="login">登录</span> 
			<span id="phoneloginForm" data-value="phonelogin">通过手机登录</span> 
			<span id="registForm" data-value="regist">注册</span>
		</div>
		<%-- 使用密码登录表单 --%>
		<div class='login_fields'>
			<div class='login_fields__user'>
				<div class='icon'>
					<img alt="" src='${pageContext.request.contextPath }/front/infRes/login/img/user_icon_copy.png'>
				</div>
				<input name="login" placeholder='用户名/手机/邮箱' maxlength="16" type='text' autocomplete="off" value="" />
				<div class='validation'>
					<img alt="" src='${pageContext.request.contextPath }/front/infRes/login/img/tick.png'>
				</div>
			</div>
			<div class='login_fields__password'>
				<div class='icon'>
					<img alt="" src='${pageContext.request.contextPath }/front/infRes/login/img/lock_icon_copy.png'>
				</div>
				<input name="pwd" placeholder='密码' maxlength="16" type='text' autocomplete="off">
				<div class='validation'>
					<img alt="" src='${pageContext.request.contextPath }/front/infRes/login/img/tick.png'>
				</div>
			</div>
			<div class='login_fields__password'>
				<div class='icon'>
					<img alt="" src='${pageContext.request.contextPath }/front/infRes/login/img/key.png'>
				</div>
				<input name="code" placeholder='验证码' maxlength="4" type='text' name="ValidateNum" autocomplete="off">
				<div class='validation' style="opacity: 1; right: -5px; top: -3px;">
					<canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
				</div>
			</div>
			<div class='login_fields__submit'>
				<input type='button' value='登录'>
			</div>
		</div>
		<%-- 使用手机登录表单 --%>
		<div class="phonelogin_fields">
		
		</div>
		
		<%-- 注册表单 --%>
		<div class="regist_fields">
			
		</div>
		
		<div class='success'></div>
		<div class='disclaimer'>
			<p>
				如有问题请:&nbsp;<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=514ff66569566b149ce159e0fdf28d9cb5acb06243e8054f509ad3c6ea146728"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="嘤嘤嘤" title="嘤嘤嘤"></a>
			</p>
		</div>
	</div>
	<div class='authent'>
		<div class="loader" style="height: 44px; width: 44px; margin-left: 28px;">
			<div class="loader-inner ball-clip-rotate-multiple">
				<div></div>
				<div></div>
				<div></div>
			</div>
		</div>
		<p>认证中...</p>
	</div>
	<div class="OverWindows"></div>
</body>
<script type="text/javascript">
	var canGetCookie = 0; // 是否支持存储Cookie 0 不支持 1 支持
	var ajaxmockjax = 1; // 是否启用虚拟Ajax的请求响应 0 不启用  1 启用
	// 默认账号密码
	var truelogin = "1";
	var truepwd = "1";

	var CodeVal = 0;
	Code();
	function Code() {
		if (canGetCookie == 1) {
			createCode("AdminCode");
			var AdminCode = getCookieValue("AdminCode");
			showCheck(AdminCode);
		} else {
			showCheck(createCode(""));
		}
	}
	function showCheck(a) {
		CodeVal = a;
		var c = document.getElementById("myCanvas");
		var ctx = c.getContext("2d");
		ctx.clearRect(0, 0, 1000, 1000);
		ctx.font = "80px 'Hiragino Sans GB'";
		ctx.fillStyle = "#E8DFE8";
		ctx.fillText(a, 0, 100);
	}
	$(document).keypress(function(e) {
		// 回车键事件 
		if (e.which == 13) {
			$('input[type="button"]').click();
		}
	});
	//粒子背景特效
	$('body').particleground({
		dotColor : '#E8DFE8',
		lineColor : '#133b88'
	});

	$('input[name="pwd"]').focus(function() {
		$(this).attr('type', 'password');
	});

	$('input[type="text"]').focus(function() {
		$(this).prev().animate({
			'opacity' : '1'
		}, 200);
	});
	$('input[type="text"],input[type="password"]').blur(function() {
		$(this).prev().animate({
			'opacity' : '.5'
		}, 200);
	});
	// 设置后方数据正确时的勾动画
	$('input[name="login"],input[name="pwd"]').keyup(function() {
		var Len = $(this).val().length;
		if (!$(this).val() == '' && Len >= 5) {
			showAndHiddenOk(this, true);
		} else {
			showAndHiddenOk(this, false);
		}
	});

	var open = 0;

	var fullscreen = function() {
		elem = document.body;
		if (elem.webkitRequestFullScreen) {
			elem.webkitRequestFullScreen();
		} else if (elem.mozRequestFullScreen) {
			elem.mozRequestFullScreen();
		} else if (elem.requestFullScreen) {
			elem.requestFullscreen();
		} else {
			//浏览器不支持全屏API或已被禁用  
		}
	}

	// 虚拟Ajax的请求响应
	if (ajaxmockjax == 1) {
		$.mockjax({
			url : 'Ajax/Login',
			status : 200,
			responseTime : 50,
			responseText : {
				"Status" : "ok",
				"Text" : "登陆成功<br /><br />欢迎回来"
			}
		});
		$.mockjax({
			url : 'Ajax/LoginFalse',
			status : 200,
			responseTime : 50,
			responseText : {
				"Status" : "Erro",
				"Erro" : "账号名或密码或验证码有误"
			}
		});
	}

	// 显示认证动画
	function showAuth() {
		$('.login').addClass('test'); //倾斜特效
		setTimeout(function() {
			$('.login').addClass('testtwo'); //平移特效
		}, 300);
		setTimeout(function() {
			$('.authent').show().animate({
				right : -320
			}, {
				easing : 'easeOutQuint',
				duration : 600,
				queue : false
			});
			$('.authent').animate({
				opacity : 1
			}, {
				duration : 200,
				queue : false
			}).addClass('visible');
		}, 500);
	}

	// 隐藏认证动画
	function hiddenAuth() {
		setTimeout(function() {
			$('.authent').show().animate({
				right : 90
			}, {
				easing : 'easeOutQuint',
				duration : 600,
				queue : false
			});
			$('.authent').animate({
				opacity : 0
			}, {
				duration : 200,
				queue : false
			}).addClass('visible');
			$('.login').removeClass('testtwo'); //平移特效
		}, 2000);
	}

	// 显示校验正常的动画勾
	function showAndHiddenOk(dom, isOk) {
		alert($(this).val())
		if (isOk) {
			$(dom).next().animate({
				'opacity' : '1',
				'right' : '30'
			}, 200);
		} else {
			$(dom).next().animate({
				'opacity' : '0',
				'right' : '20'
			}, 200);
		}
	}

	// 点击操作类型后执行的操作
	function clickOperation() {
		if (operation == loginEnum) {

		} else if (operation == phoneloginEnum) {

		} else if (operation == registEnum) {

		} else {
			ErroAlert("类型错误[" + operation + "]");
		}
	}

	/* --- 下方进行事件绑定 --- */
	var operation = $("#loginForm").attr("data-value"); // 全局参数，记录当前的操作 枚举值：login phone regist
	// 定义操作枚举值
	var loginEnum = $("#loginForm").attr("data-value"); // 登录
	var phoneloginEnum = $("#phoneloginForm").attr("data-value"); // 通过手机登录
	var registEnum = $("#registForm").attr("data-value"); // 注册

	layui.use('layer', function() {
		// 登录事件
		// 非空验证
		$('input[type="button"]').click(function() {
			// 数据校验
			var reData = checkData();
			if (reData.boo) {
				// 认证中..
				// fullscreen();
				showAuth();

				// 登陆
				var JsonData = reData.data;
				// var url = reData.url;
				// 此处做为ajax内部判断
				var url = "";
				if (JsonData.login == truelogin && JsonData.pwd == truepwd) {
					url = "Ajax/Login";
				} else {
					url = "Ajax/LoginFalse";
				}

				AjaxPost(url, JsonData, function() {
					//ajax加载中
				}, function(data) {
					//ajax返回 
					//认证完成
					hiddenAuth();

					setTimeout(function() {
						$('.authent').hide();
						$('.login').removeClass('test');
						if (data.Status == 'ok') {
							//登录成功
							$('.login div').fadeOut(100);
							$('.success').fadeIn(1000);
							$('.success').html(data.Text);
							//跳转操作

						} else {
							AjaxErro(data);
						}
					}, 2400);
				})
			}
		})
	})

	$(function() {
		// 操作类型事件绑定
		$(".login_title").children().each(function() {
			$(this).click(function() {
				operation = $(this).attr("data-value");
				$(this).css("cursor", "default");
				$(this).css("color", "#F5FFFA");
				$(this).css("font-weight", "bold");

				$(this).siblings().each(function() {
					$(this).css("cursor", "pointer");
					$(this).css("color", "#D3D7F7");
					$(this).css("font-weight", 'normal');
				})
				clickOperation();
			})
		})
	})
</script>
</html>
