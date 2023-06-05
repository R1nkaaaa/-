$(function () {
	let user = localStorage.getItem('user');
	if (user) {
		user = JSON.parse(user);
		$('.email').val(user.Email);
		$('.name').val(user.FirstName + user.LastName);
	}

	$('.submit').click(function () {
		let password = $('.newPassword').val();
		let newPasswordAgain = $('.newPasswordAgain').val();
		let length = password.length;
		if (length < 8 || length > 16) {
			return alert("请输入符合要求的密码");
		}
		if (password !== newPasswordAgain) {
			return alert('两次输入密码不一致，请重新输入');
		}
		let userId = user.UserId;
		$.ajax({
			url: 'http://localhost:8080/SunshineAirlines/updatePassword',
			type: 'post',
			data: `userId=${userId}&password=${password}`,
			success: function (res) {
				let { flag, data } = JSON.parse(res);
				if (flag == 'success') {
					localStorage.clear();
					location.href = 'Login.html'
				} else {
					alert(data)
				}
			}
		})
	})

	$('.cancel').click(function () {
		$('.newPassword').val('');
		$('.newPasswordAgain').val('');
	})
})