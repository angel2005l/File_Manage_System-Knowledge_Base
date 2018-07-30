function ajaxPost(url,data,isAsync){
	var result = null;
	$.ajax({
		url:url,
		async:isAsync,
		type:'post',
		data:data,
		success:function(data){
			result = data;
		},
		error:function(){
			return ;
		}
	})
	return result;
}

function ajaxGet(url,isAsync){
	var result = null;
	$.ajax({
		url:url,
		async:isAsync,
		type:'get',
		success:function(data){
			result = data;
		},
		error:function(){
			return ;
		}
	})
	return result;
}