$(function () {
	$('.list_out').click(function () {
		localStorage.clear();
		location.href = 'Login.html'
	})

	$('.startDate').change(function () {
		$('.endDate').prop('min', this.value);
	})
	$('.endDate').change(function () {
		$('.startDate').prop('max', this.value);
	})

})