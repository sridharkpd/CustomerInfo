$(document).bind('contextmenu', function (e) { e.preventDefault(); });


$(document).ready(function() {
	// Data Picker Initialization
		 $(".datepicker").datepicker({
        format: "yy/mm",
    viewMode: "months", 
    minViewMode: "months",
startDate: new Date()
      });


$(document).on('keypress','.limit-number', function(e) {
		if (e.which != 8 && e.which != 0
				&& ((e.which < 48 && e.which != 46) || e.which > 57)) {
			return false;
		}
	});

}); 
