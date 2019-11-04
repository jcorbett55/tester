var orders = orders || {};
orders.edit = orders.edit || {};


(function($){
	
	orders.edit.getOrderObject = function(aermeds){
		return aermeds.aermedsOrder;
	};
	
	orders.edit.getSecurityObject = function(aermeds){
		return aermeds.aerMedsSecurity;
	};
	
	orders.edit.getId = function(aermeds){
		return aermeds.aermedsOrder.id;
	};

	orders.edit.checkForUpdates = function(aermeds){
		console.debug('checkForUpdates');
		
		var scan = function(data) {
			if (!data){
				return; 
			}
			if (!$.isArray(data)){
				data = [data];
			}
			var update = false;
			
			$.each(data, function(i, obj) {
				if (obj.actionCode != ahom.form.NOCHANGE)
					return !(update = true);
			});
			return update;
		};
		
		return scan(aermeds.aermedsOrder);
	};

	orders.edit.newTab = function(pageTab, aermeds, pageMap){
		
		var pageJQ = pageTab.pageJQ;
		this.pageJQ = pageJQ;
		
		var form = pageJQ.find('#form');
		var historyButton = pageJQ.find('#auditHistory');
		
		form.find('input').addClass('ui-widget-content ui-corner-all').change(function(event){
			if (event.setForm) return;
			
			aermeds.aermedsOrder.actionCode = ahom.form.UPDATE;
		});
		
		var salesmanMap = {};
		form.find('input[name="salesmanNo"]').autocomplete({
			autoFocus: true,
			minLength: ahom.form.MIN_AUTOCOMPLETE_CHARS,
			source: function(request, response) {
				var name = this.element.prop('name');
				var glcode = (aermeds.aermedsOrder.glCode).toString();
				var newglcode = glcode.substring(0,4);
				var	url = '/Order_Entry/orderDetail/api/salesmanOptions/'; 
				ahom.util.ajax(url + newglcode, {
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
				else if (this.name == 'salesmanNo' && !salesmanMap[this.value]) this.value = aermeds.aermedsOrder.salesmanNo;
				return false;
			}
			
		});
		
		this.saveData = function() {
			
			ahom.form.getFormData(form,aermeds.aermedsOrder);
			if (salesmanMap[aermeds.aermedsOrder.salesmanNo]) aermeds.aermedsOrder.salesmanId = salesmanMap[aermeds.aermedsOrder.salesmanNo];
			else aermeds.aermedsOrder.salesmanId = null;
			var salesNo = aermeds.aermedsOrder.salesmanNo;
			var res_array = salesNo.trim().split(/\s+/);
				aermeds.aermedsOrder.salesmanNo = parseInt(res_array[0]);
				aermeds.aermedsOrder.salesmanNameDisp= res_array[2]+" "+res_array[3];
			
			var glCode = aermeds.aermedsOrder.glCode;
			var res_array = glCode.trim().split(/\s+/);
				aermeds.aermedsOrder.glCode = (res_array[0]);
				aermeds.aermedsOrder.facilityNameDSP= res_array[2]+" "+res_array[3];
			
			
		};
		

		this.loadData = function(aermeds_new){
			
			aermeds = aermeds_new;
			
			aermeds.aermedsOrder.glCode = aermeds.aermedsOrder.glCode+" - "+aermeds.aermedsOrder.facilityNameDSP;
			
			if((aermeds.aermedsOrder.salesmanNo != null) && (aermeds.aermedsOrder.salesmanNameDisp != null) ){
			aermeds.aermedsOrder.salesmanNo = aermeds.aermedsOrder.salesmanNo+" - "+aermeds.aermedsOrder.salesmanNameDisp;}
			else{aermeds.aermedsOrder.salesmanNo = null;}
			ahom.form.setFormData(aermeds.aermedsOrder,form);
			
		};
		
		this.loadData(aermeds);

		historyButton.button({icons:{primary:'ui-icon-clipboard ahom-icon-blue'}}).click(function() {
			commonUI.auditHistory('OE_AERMEDS_ORDERS',aermeds.aermedsOrder.id);
		});
		
	};
	
}(jQuery));