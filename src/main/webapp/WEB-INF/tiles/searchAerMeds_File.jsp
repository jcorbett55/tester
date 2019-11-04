<script type="text/javascript" src="<%= request.getContextPath() %>/resources/commonJs/base/standardSearch.js?${commonInfo.version}"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/searchAerMeds_File.js?${appInfo.version}"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/toolbar.js?${appInfo.version}"></script>

<style>
#application{
   font-weight: bold;
}
</style>


<div id="searchAerMedsFileQueue">
<div id="activeFilter">
	<button type="button" value="ACTIVE">Active</button>
	<button type="button" value="ALL">All</button>
</div>
<table id="table">
	<thead>
		<tr>
			<th name="customerNo">Customer Number</th>
			<th name="customerLastName">Customer Last Name</th>
			<th name="customerFirstName">Customer First Name</th>
			<th name="dos" class="date">DOS</th>
			<th name="completionDate" class="date">Completion Date</th>
			<th name="glCode">GL Code</th>
			<th name="mestaOrderNo">Mesta Order Number</th>
			<th name="salesmanNo">AE Code</th>
		</tr>
	</thead>
	<tbody />
</table>
<input type='hidden' id='reqcontentpath' value='<%= request.getContextPath() %>'>

<div id="filter">
	<input type="text" name="customerNo" />
	<input type="text" name="customerLastName" />
	<input type="text" name="customerFirstName" />
	<input type="text" class="date" name="dos" />
	<input type="text" class="date" name="completionDate" />
	<div><input type="text" name="glCode" /></div>
	<input type="text" name="mestaOrderNo" />
	<input type="text" name="salesmanNo" />
</div>

</div>
<script>
	
	var tileFile = $('#searchAerMedsFileQueue');
	var branchOptions = ${lov.branchOptions};
	tileFile.find('input[name="glCode"]').ahom_autocomplete(branchOptions, { combo: true, addBlank: true });
	
</script>

