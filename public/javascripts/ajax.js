function ajax(url, method, data) {
	return $.ajax(
	{
		url: url,
		data: data,
		type: method
	});	
};