$(function () {
	$('.stat').click(function () {
		let startDate = $('.startDate').val();
		let endDate = $('.endDate').val();
		$.ajax({
			url: 'http://localhost:8080/SunshineAirlines/getTicketStatistics',
			type: 'post',
			data: `startDate=${startDate}&endDate=${endDate}`,
			success: function (res) {
				let { flag, data } = JSON.parse(res);
				if (flag == 'success') {
					let html = "";
					for (let item of data) {
						html +=
							`<tr>
								<td>${item.Month}</td>
								<td>${item.FlightsAmount}</td>
								<td>${item.TicketsAmount}</td>
								<td>${item.TicketsRevenue}</td>
							</tr>`
					}
					$('.resultList').html(html);
					$('.resultList tr:odd').addClass('tdcolor');
					$('.resultList tr:even').addClass('tdcolor1');
				}
			}
		})
	})
})