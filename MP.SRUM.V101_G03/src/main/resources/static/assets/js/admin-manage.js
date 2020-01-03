function loadTraineeInfo(id){
	$.get({
		url: "/admin/trainee-info",
		data: {
			id: id
		},
		success: (resp)=>{
			console.log(resp)
			//$("#trainee-name").html(resp.firstName)
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
