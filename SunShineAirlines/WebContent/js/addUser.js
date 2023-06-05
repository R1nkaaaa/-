$(function () {
	$('.cancel').click(function () {
		location.href = 'UserManagement.html';
	})

	$('.submit').click(function () {
		let email = $('.email').val();
		let firstName = $('.firstName').val();
		let lastName = $('.lastName').val();
		let gender = $('.genderMale').prop('checked') ? "M" : "F";
		let dateOfBirth = $('.dateOfBirth').val();
		let phone = $('.phone').val();
		let photo = encodeURIComponent($('.photo').prop('src'));
		let address = $('.address').val();
		let roleId = $('.roleUser').prop('checked') ? 1 : 2;

		$.ajax({
			url: 'http://localhost:8080/SunshineAirlines/addUser',
			type: 'post',
			data: `email=${email}&firstName=${firstName}&lastName=${lastName}&gender=${gender}&dateOfBirth=${dateOfBirth}&phone=${phone}&photo=${photo}&address=${address}&roleId=${roleId}`,
			success: function (res) {
				let { flag, data } = JSON.parse(res);
				if (flag == 'success') {
					location.href = 'UserManagement.html';
				} else {
					alert(data);
				}
			}
		})
	})

	$('.upload-input').change(function (e) {
		let file = e.target.files[0];
		let fr = new FileReader();
		fr.onload = function (e) {
			$('.photo').prop('src', e.target.result);
		}
		fr.readAsDataURL(file);
	})
})