// 数据校验 login phonelogin regist
function checkData() {
	var boo = false;
	var JsonData = {};

	if (operation == loginEnum) {
		var login = $('.login_fields_cus input[name="login"]').val();
		var pwd = $('.login_fields_cus input[name="pwd"]').val();
		var code = $('.login_fields_cus input[name="code"]').val();
		if (!login) {
			ErroAlert('请输入您的账号/手机/邮箱');
		} else if (!pwd) {
			ErroAlert('请输入密码');
		} else if (!code || code.length != 4) {
			ErroAlert('输入验证码');
		} else if (code.toUpperCase() != CodeVal.toUpperCase()) {
			ErroAlert('验证码错误');
		} else {
			boo = true;
			var data = {
				url : "",
				data : {
					login : login,
					pwd : pwd
				}
			};
			JsonData = data;
		}
	} else if (operation == phoneloginEnum) {

	} else if (operation == registEnum) {
		var login = $('.regist_fields input[name="login"]').val();
		var pwd = $('.regist_fields input[name="pwd"]').val();
		var phonenum = $('.regist_fields input[name="phonenum"]').val();
		var phonedateNum = $('.regist_fields input[name="phonedateNum"]').val();
		if (!login) {
			ErroAlert('请输入您的账号/手机/邮箱');
		} else if (!pwd) {
			ErroAlert('请输入密码');
		} else if (!phonedateNum || phonedateNum.length != 6) {
			ErroAlert('输入验证码');
		} else if (validateCode(phonedateNum)) {
			ErroAlert('验证码错误');
		} else {
			boo = true;
			var data = {
				url : "",
				data : {
					login : login,
					pwd : pwd
				}
			};
			JsonData = data;
		}
	} else {
		ErroAlert("类型错误:check[" + operation + "]");
	}

	JsonData.boo = boo;

	return JsonData;
}

// 验证码校验方法
function validateCode(valicode){
	var boo = false;
	var codeuuid = "${codeuuid }";
	if(!codeuuid){
		ErroAlert('本次请求无效，请刷新页面后重新操作!');
	} else {
		AjaxPost("${pageContext.request.contextPath}/login/active.cc", 
				{codeuuid: codeuuid,valicode: valicode,checkType:"phone"}, 
				function() {
					//ajax加载中
				}, function(data) {
					
				})
	}
	return boo;
}
