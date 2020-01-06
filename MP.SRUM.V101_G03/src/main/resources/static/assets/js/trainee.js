$(document).ready(function(){
	$.ajax({
		type: "GET",
		url: "/trainee/view-feedback",
		success: (data)=>{
			$("#feedback-content").html(res);
		}
	})
})

function load-feedback(userId, subjectId){
	
}