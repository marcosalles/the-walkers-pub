function flash(key, message) {
	var alert = $("#alert-template").clone();
	alert.attr("id", "");
	alert.addClass("alert-"+key);
	alert.html(message);
	$("#alert-template").after(alert);
};
function ajax(url, method, data) {
	return $.ajax(
	{
		url: url,
		data: data,
		type: method
	});	
};
$(function(){
	var templates = $(".template").sort(asc_sort);
	templates.each(function(){
		var template = $(this);
		var id = template.attr("id");
		var url = template.attr("data-url");
		var method = "get";
		var result = ajax(url, method, {});
		result.done(function(data) {
			template.html(data);
		});
		result.fail(function(jqXHR, textStatus, errorThrown) {
			template.html("error loading "+id+".. "+jqXHR.responseText);
		});
	});	
});
function asc_sort(a, b){
  return ($(b).attr("data-sort")) < ($(a).attr("data-sort")) ? 1 : -1;    
}
function dec_sort(a, b){
  return ($(b).attr("data-sort")) > ($(a).attr("data-sort")) ? 1 : -1;    
}