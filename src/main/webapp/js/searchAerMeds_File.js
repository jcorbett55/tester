var aermeds = aermeds || {};
aermeds.search = aermeds.search || {};
aermeds.queue = aermeds.queue || {}; 

(function($){
	$(function(){
		var tileFile = $('#searchAerMedsFileQueue');
		
		var salesmanMap = {};
		tileFile.find('input[name="salesmanNo"]').autocomplete({
			autoFocus: true,
			minLength: ahom.form.MIN_AUTOCOMPLETE_CHARS,
			source: function(request, response) {
				var name = this.element.prop('name');
				var	url = '/Order_Entry/orderDetail/api/salesmanOptions/'; 
				var glcode = "0161";
				ahom.util.ajax(url + glcode, {
					data: {
						input: request.term,
						external: 'true'
					},
					json: false,
					type: 'GET',
					cache: true,
					message: null,
					errorTitle: 'AerMeds Order',
					error: function(msg) { response(); },
					success: function(data) {
						var display = [];
						for(var index in data) { salesmanMap[data[index]] = index; display.push(data[index]); }
						response(display);
					}
				});
			},
			change: function(event, ui) {
				if (this.value.trim() == '') return false;
				else if (!salesmanMap[this.value]) this.value = '';//aermeds.search.salesmanNo;
				return false;
			}
			
		});
	
		var searchTab = commonUI.search.newSearchTab(tileFile, appInfo.contextPath+'/search/AerMedsFileFilter', {
			title: 'AerMeds Orders Search',
			  //Executed before call is made to server.
			beforeFilter: function(filter) {
				var salesNo = filter.salesmanNo;
				var res_array = salesNo.trim().split(/\s+/);
				for(var i=0;i<res_array.length;i++){
					filter.salesmanNo = parseInt(res_array[0]);
				}
				return filter;
			},
			tableDisplay: function(index, data, prebuild, nameMap) {
				prebuild[nameMap.salesmanNo] = data.salesmanNo+' - '+data.salesmanNameDSP;
				prebuild[nameMap.glCode] = data.glCode+' - '+data.facilityNameDSP;
				if(data.salesmanNo != null){
					prebuild[nameMap.salesmanNo] = data.salesmanNo+' - '+data.salesmanNameDSP;
				}else{
					prebuild[nameMap.salesmanNo] = "";
				}
					
				
				return prebuild; 
				
			}, // Function used to display custom data in table.
			onClick: function(rowData, event, table) {
				ahom.browser.open(appInfo.contextPath+'/order/'+rowData.id);
			}
		});
		
		aermeds.queue.refresh = function() {
			if (searchTab.table.isFiltering())
				console.log('Already filtering, no action taken');
			else
				searchTab.table.dataTableFilter('Updating');
		};

	});

}(jQuery));
