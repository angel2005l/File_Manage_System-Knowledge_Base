$(function() {
			$("html").css("background-image",
					"url('assets/img/login_bg_xh_kb.jpg')");
		});

		var accountIpunt = $("#userName");
		var pwdInput = $("#password");
		$(function() {
			accountIpunt.keydown(function(e) {
				if (13 == e.keyCode) {
					pwdInput.focus();
				}
			});
			pwdInput.keydown(function(e) {
				if (e.keyCode == 13) {
					login();
				}
			});
		});

		function login() {
			if (check()) {
				$.ajax({
					url : 'user/login.do',
					type : 'post',
					data : {
						"user_code" : $("#userName").val(),
						"user_password" : $("#password").val()
					},
					dataType : "json",
					success : function(result) {
						if (result.code == 0) {
							window.location.href = "pro/index.do";
						} else {
							alert(result.msg);
							location.reload();
						}
					},
					error : function() {
						alert("服务未响应")
					}

				});
			}
		}

		function check() {
			var userAccount = accountIpunt.val();
			var userPwd = pwdInput.val();
			if ('' == userAccount) {
				alert("请输入用户名")
				accountIpunt.focus();
				return false;
			} else if ('' == userPwd) {
				alert("请输入密码");
				pwdInput.focus();
				return false;
			}
			return true;
		}