var orders = orders || {};
orders.edit = orders.edit || {};

(function($){
	$( document ).ready(function() {
		
		
		var content = $('#content');
		var template = content.find('#pageTemplate').remove();
		var tab = template.clone();
	
			orders.edit.saveOrder = function(aermeds, callback){
			
			var action =  orders.edit.getOrderObject(aermeds).actionCode;
			var msg = 'Saving Order';
			ahom.debug.logJson('Before Save', aermeds);
			ahom.util.ajax(appInfo.contextPath+'/save', {
				data:aermeds,
				message: msg,
				errorTitle: 'Order Save Error',
				success: function(data) {
					ahom.debug.logJson('After Save', data);
					callback(data);
				}
			});
			
			
		};
		

		orders.edit.newOrderTab = function(aermeds, formData,initTab){

			var lazyLoad = true;
			ahom.debug.logJson('Load AerMeds', aermeds);
		
			var pages = {};
			
			var masterControl = tab.find('#masterButtons');
			var saveButton = masterControl.find('#save');
			var closeButton = masterControl.find('#close');
			var saveAndCloseButton = masterControl.find('#saveAndClose');
			
			var sideTabDiv = tab.find('#sideTabs');
			
			var customerLast =  tab.find("input[name='customerNameDisp.last']");
			var customerFirst =  tab.find("input[name='customerNameDisp.first']");
			var dos =  tab.find("input[name='dos']");
			var completionDate =  tab.find("input[name='completionDate']");
			var glCode =  tab.find("input[name='glCode']");
			var mestaOrderNo =  tab.find("input[name='mestaOrderNo']");
			var customerNo = tab.find("input[name='customerNo']");
			
			
			var tabObject = mainTabs.newTab(tab, 'Order',{
				
				onClose: function(windowEvent) {
					if (!aermeds) return true;
					if (ahom.util.checkForUpdates(aermeds)){
						var listName =  aermeds.aermedsOrder.id+" "+aermeds.aermedsOrder.customerNameDisp.last+" "+aermeds.aermedsOrder.customerNameDisp.first;
						if (windowEvent || !confirm(listName + ' may have unsaved changes.\nAre you sure you want to close?')){
							return false;
							}
					}
					//Beyond this point tab WILL close.
					return true;
					
				},
				onKeyboard: function(event) {
					var key = event.keyCode;
					var vTab = null;
					switch(key) {
					case 191:
						if (event.ctrl) ahom.dialog.showMessage('Shortcut keys', '\
								<table class="labelvaluetable ahom-helpMenu-hotKey">\
								<tr><th>Ctrl-?</th><td>Help (This Menu)</td></tr>\
								<tr><th>Ctrl-S</th><td>Save Orders</td></tr>\
								<tr><th>Esc</th><td>Close Window</td></tr>\
								</table>\
						');
						return false;
					case 83:
						if (event.ctrl) {
							saveButton.click();
							return false;
						}
						break;
					case 27:
						closeButton.click();
						return false;
					}
				}
				
				
			});
			
	
			content.append(tab);
			var verticalTabs = new ahom.tab.tabspace(sideTabDiv, { classList: ['ui-corner-left'] });
			
			var loadData = function(aermeds_new){
				
				aermeds = aermeds_new;
				
				var securityObject = orders.edit.getSecurityObject(aermeds);
				
				
				 if (securityObject.id < ahom.form.EDITABLE) {
					saveButton.hide();
					saveAndCloseButton.hide();
				} else {
					saveButton.show();
					saveAndCloseButton.show();
				}
				
				//Reload tabs.
				$.each(pages, function(index, page) { 
					page.loadData(aermeds);
				});	
			};
			
			var saveData = function(closeAfterSave, callback){
				if (!$.isFunction(callback)) callback = $.noop;
				$.each(pages, function(i, page){ 
					page.saveData(); 
				});
				
				orders.edit.saveOrder (aermeds, function(savedOrder){
					
					commonUI.sideTabs.clearAppInfo(tab);
					loadData(savedOrder);
					
					var errorLevel = ahom.util.errorLevel(savedOrder, { target: tabObject.tab.find('span:not(.ui-icon)'), deepSearch: true });

					if (errorLevel <= ahom.form.WARN) {
							try { if (ahom.util.oCheck(window, 'opener.aermeds.queue.refresh')) window.opener.aermeds.queue.refresh(); }
							catch(e) { /*Error refreshing the queue.*/ }}
					
					switch(errorLevel) {
						case ahom.form.OK:
							commonUI.sideTabs.addAppInfo(tab, 'Save was successful', {level: errorLevel});
							if (closeAfterSave) tabObject.remove();
							break;
							
						case ahom.form.WARN:
							commonUI.sideTabs.addAppInfo(tab, 'Save was successful, but returned with warnings', {level: errorLevel});
							if (closeAfterSave) {
								if (confirm('Order Saved, but with warnings.\n Close anyway?')) {
									tabObject.remove();
								}else{
								}
							}
							break;
						default:
						case ahom.form.FAIL:
							commonUI.sideTabs.addAppInfo(tab, 'Order was not saved, due to errors.', {level: errorLevel});
							$.each(data.errorList.general, function(i, err) { 
								commonUI.sideTabs.addAppInfo(tab, err.message, {level: errorLevel, timeout: null}); 
							});

							break;
					}
					
				});
				
			};
			

		
			//Setup pages.
			tab.find('.subPage').each(function() {
				var that = this;
				var page = $(that);
				var vertTab = verticalTabs.newTab(page, page.attr('display'));
				var loadFunction = function(){
					pages[0] = new orders.edit.newTab(vertTab,aermeds,pages);
				};
				
				if (lazyLoad){
					vertTab.tab.one('click', function() { 
						loadFunction(); 
					});
				}
				else{
					loadFunction();
				}

			});
			
			
			loadData(aermeds);
			
			//Setup master buttons
			saveButton.button({icons:{primary:'ui-icon-disk ahom-icon-green'}}).click(function() {
				saveData(false);
			});
			
			closeButton.button({icons:{primary:'ui-icon-cancel ahom-icon-red'}}).click(function() {
				tabObject.remove();
			});
			
			saveAndCloseButton.button({icons:{primary:'ui-icon-disk ahom-icon-green'}}).click(function() {
				saveData(true);
			});
			
			customerNo.prop('disabled', true);
			customerFirst.prop('disabled', true);
			customerLast.prop('disabled', true);
			dos.prop('disabled', true);
			completionDate.prop('disabled', true);
			glCode.prop('disabled', true);
			mestaOrderNo.prop('disabled', true);
			
			//Set active side tab.
			if ($('#' + initTab + '_tab', sideTabDiv).click().length == 0)  //Change to this tab, if found.
				$('li:first', sideTabDiv).click();  //Start with first tab.

			
		};
		
	});
	
}(jQuery));