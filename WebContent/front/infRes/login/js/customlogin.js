// 数据校验 login phonelogin regist
function checkData() {
	var boo = false;
	var JsonData = {};

	if (operation == loginEnum) {
		var login = $('input[name="login"]').val();
		var pwd = $('input[name="pwd"]').val();
		var code = $('input[name="code"]').val();
		if (login == '') {
			ErroAlert('请输入您的账号');
		} else if (pwd == '') {
			ErroAlert('请输入密码');
		} else if (code == '' || code.length != 4) {
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

	} else {
		ErroAlert("类型错误:check[" + operation + "]");
	}

	JsonData.boo = boo;

	return JsonData;
}
