var aerMedsOrders = aerMedsOrders || {};

(function($){
	
	var content = $('#content');
	var template = content.find('#pageTemplate').remove();
		
	aerMedsOrders.newTab = function()
	{	
		var tab = template.clone();

		var masterControl = tab.find('#masterButtons');
		var saveButton = masterControl.find('#save');
		var saveAndCloseButton = masterControl.find('#saveAndClose');
		var closeButton = masterControl.find('#close');

		var tabObject = mainTabs.newTab(tab, 'Hello World');
		content.append(tab);
		
		saveButton.button({icons:{primary:'ui-icon-disk ahom-icon-green'}}).click(function() { alert('TODO'); });
		closeButton.button({icons:{primary:'ui-icon-cancel ahom-icon-red'}}).click(function() { tabObject.remove(); });
		saveAndCloseButton.button({icons:{primary:'ui-icon-disk ahom-icon-green'}}).click(function() { alert('TODO'); tabObject.remove(); });
	};
	
}(jQuery));

