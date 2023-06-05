var obj = {}
$(function () {

	$('.step-backward').click(function () {
		if (obj.startPage == 1) {
			alert("已经是第一页");
		} else {
			obj.startPage = 1;
			getUserList(obj)
		}
	})

	$('.step-forward').click(function () {
		if (obj.startPage == obj.totalPage) {
			alert("已经是最后一页");
		} else {
			obj.startPage = obj.totalPage;
			getUserList(obj)
		}
	})

	$('.chevron-left').click(function () {
		if (obj.startPage == 1) {
			alert("已经是第一页");
		} else {
			obj.startPage--;
			getUserList(obj)
		}
	})

	$('.chevron-right').click(function () {
		if (obj.startPage == obj.totalPage) {
			alert("已经是最后一页");
		} else {
			obj.startPage++;
			getUserList(obj)
		}
	})


	$('.searchBtn').click(function () {
		obj.roleId = $('.roleId').val();
		obj.userName = $('.userName').val();
		obj.startPage = 1;
		getUserList(obj)
	})

	$('.pageSelect').change(function () {
		obj.startPage = this.value;
		getUserList(obj)
	})

})

function getUserList({ roleId = 0, userName = "", startPage = 1 } = obj) {
	$.ajax({
		url: 'http://localhost:8080/SunshineAirlines/userList',
		type: 'post',
		data: `roleId=${roleId}&name=${userName}&startPage=${startPage}&pageSize=10`,
		success: function (res) {
			let { data, flag, page } = JSON.parse(res);
			if (flag == 'success') {
				let html = "";
				for (let user of data) {
					html +=
						`<tr>
							<td>${user.Email}</td>
							<td>${user.FirstName} ${user.LastName}</td>
							<td>${user.Gender == "M" ? "Male" : "Female"}</td>
							<td>${user.DateOfBirth}</td>
							<td>${user.Phone}</td>
							<td>${user.RoleId == 1 ? "Office User" : "Administrator"}</td>
							<td><input class='editUser' style='width: 80px;  font-size: 16px;' type='button' value='Edit' /></td>
						</tr>`
				}

				let total = page.total;
				obj.totalPage = total % 10 == 0 ? total / 10 : parseInt(total / 10) + 1;
				$('.pageNum').text(obj.totalPage)
				$('.totals').text(total);
				let i = 1;
				let optionHtml = "";
				while (i <= obj.totalPage) {
					optionHtml += i == obj.startPage ? `<option value=${i} selected>${i}</option>` : `<option value=${i}>${i}</option>`;
					i++;
				}
				$('.pageSelect').html(optionHtml);

				$('.resultList').html(html);
				$('.resultList tr:odd').addClass('tdcolor');
				$('.resultList tr:even').addClass('tdcolor1');
			}
		}
	})
}
