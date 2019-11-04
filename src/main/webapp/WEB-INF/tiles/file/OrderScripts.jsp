<script src="<%= request.getContextPath() %>/resources/commonJs/base/auditHistory.js?${appInfo.version}"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/toolbar.js?${appInfo.version}"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/edit.js?${appInfo.version}"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/file.js?${appInfo.version}"></script>


<script type="text/javascript">
(function($){
	
	$( document ).ready(function() {
		
		var aermeds = ${aermeds};
		var initTab = ${initTab};
		
		orders.edit.newOrderTab(aermeds,initTab);
			
	});

}(jQuery));	
</script>

