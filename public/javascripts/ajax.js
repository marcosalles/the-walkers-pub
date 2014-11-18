function ajax(url, method, data, callback) {
	$.ajax(
	{
		url: url,
		data: data,
		type: method
		/*success: mostraConfirmacao,
		error: mostraErro*/
	});	
	if(callback !== undefined){
		callback();
	}
};