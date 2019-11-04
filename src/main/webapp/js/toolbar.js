(function($){
	$(function(){

		var toolbar =$('#toolbar');
		
		var application = $('#application',toolbar);
		
		application.find('div').html("AerMeds Orders");
		
		application.click(function(){
			window.location.href = appInfo.contextPath+'/AerMeds_File';
		});

	});	
}(jQuery));
