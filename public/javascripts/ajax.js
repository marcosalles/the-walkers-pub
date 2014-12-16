function flash(key, message) {
	var alert = $("#alert-template").clone();
	alert.attr("id", "");
	alert.addClass("alert-"+key);
	alert.html(message);
	$(".flash-sink").html(alert);
};
function ajax(url, method, data) {
	return $.ajax({
		url: url,
		method: method,
		data: data
	});	
};
function asc_sort(a, b){
  return ($(b).attr("data-sort")) < ($(a).attr("data-sort")) ? 1 : -1;    
}
function dec_sort(a, b){
  return ($(b).attr("data-sort")) > ($(a).attr("data-sort")) ? 1 : -1;    
}