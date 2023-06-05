$(function () {
	$.ajax({
		url: 'http://localhost:8080/SunshineAirlines/getCityNames',
		type: 'post',
		success: function (res) {
			let { flag, data } = JSON.parse(res);
			if (flag == 'success') {
				let html = "";
				for (let city of data) {
					html += `<option value="${city.CityName}">${city.CityName}</option>`
				}
				$('.fromCity').html(html);
				$('.toCity').html(html);
			}
		}
	})

	$('.changeicon').click(function () {
		let fromCity = $('.fromCity').val();
		$('.fromCity').val($('.toCity').val());
		$('.toCity').val(fromCity);
	})

	$('.searchBtn').click(getScheduleList);
})

function getScheduleList() {
	let fromCity = $('.fromCity').val();
	let toCity = $('.toCity').val();
	let startDate = $('.startDate').val();
	let endDate = $('.endDate').val();
	$.ajax({
		url: 'http://localhost:8080/SunshineAirlines/getSchedule',
		type: 'post',
		data: `fromCity=${fromCity}&toCity=${toCity}&startDate=${startDate}&endDate=${endDate}`,
		success: function (res) {
			let { flag, data } = JSON.parse(res);
			if (flag == 'success') {
				let html = "";
				for (let item of data) {
					let status = item.Status == "Confirmed" ? "cancel" : "confirm";
					let updateStatus = item.Status == "Confirmed" ? "Canceled" : "Confirmed";
					html +=
						`<tr>
							<td>${item.Date.substring(0, 10)}</td>
							<td>${item.Time.substring(0, 5)}</td>
							<td>${item.DepartCityName}/${item.DepartureAirportIATA}</td>
							<td>${item.ArriveCityName}/${item.ArrivalAirportIATA}</td>
							<td>${item.Name}</td>
							<td>${item.EconomyPrice}</td>
							<td>${item.FlightNumber}</td>
							<td>${item.Gate}</td>
							<td>${item.Status}</td>
							<td><input type='button' value='${status}' onclick='update(${item.ScheduleId},"${updateStatus}")' /></td>
						</tr>`
				}
				$('.resultList').html(html);
				$('.resultList tr:odd').addClass('tdcolor');
				$('.resultList tr:even').addClass('tdcolor1');
			}
		}
	})
}

function update(id, status) {
	$.ajax({
		url: 'http://localhost:8080/SunshineAirlines/updateSchedule',
		type: 'post',
		data: `scheduleId=${id}&status=${status}`,
		success: function (res) {
			let { flag, data } = JSON.parse(res);
			if (flag == 'success') {
				getScheduleList();
			} else {
				alert(data)
			}
		}
	})
}