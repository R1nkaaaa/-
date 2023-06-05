$(function () {
	let user = localStorage.getItem('user');
	if (user) {
		user = JSON.parse(user);
		if (user.loginTime) {
			let loginTime = new Date(user.loginTime);
			let endTime = loginTime.setDate(loginTime.getDate() + 7);
			let now = new Date();
			if (now.getTime() < endTime) {
				jump(user.RoleId)
			}
		}
	}

	$('.loginbutton').click(function () {
		let email = $('.email').val();
		let password = $('.password').val();
		$.ajax({
			url: 'http://localhost:8080/SunshineAirlines/login',
			type: 'post',
			data: `email=${email}&password=${password}`,
			success: function (res) {
				let { flag, data } = JSON.parse(res);
				if (flag == 'success') {
					if ($('.is7day').prop('checked')) {
						data.loginTime = new Date();
					}
					localStorage.setItem('user', JSON.stringify(data));
					jump(data.RoleId);
				} else {
					$('.alertInfo').text(data);
				}
			}
		})
	})
})

function jump(roleId) {
	roleId == 1 ? location.href = 'ModifyPassword.html' : location.href = 'FlightScheduleManagement.html';
}
