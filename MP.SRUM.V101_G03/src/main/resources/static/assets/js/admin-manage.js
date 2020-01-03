function loadTraineeInfo(id){
	$("#trainee-information").show();
	$.get({
		url: "/admin/trainee-info",
		data: {
			id: id
		},
		success: (resp)=>{
			$("#trainee-name").html(resp.firstName)
			$("#trainee-email").html(resp.email)
			$("#trainee-birthday").html(resp.birthDay)
			$("#trainee-phone").html(resp.phone)
			$("#trainee-account").html(resp.account)
		}
	});
	$.get({
		url: "/trainee-score",
		data: {
			id: id
		},
		success: (resp)=>{
			
		}
	});
}
