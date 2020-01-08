function load_feedback(userId, subjectId){
	$.ajax({
		type: "GET",
		url: "/trainee/view-feedback",
		data: {
			userId : userId,
			subjectId : subjectId
		},
		success: (data)=>{
			console.log(data);
			$("#feedback-content").html(data);
		}
	})
}

function load_feedback_info(userId, subjectId){
	$.ajax({
		type: "GET",
		url: "/trainee/feedback-info",
		dataType : "JSON",
		data: {
			userId : userId,
			subjectId : subjectId
		},
		success: (data)=>{
			console.log(data.feedbackPK.userId);
			$('#userId').val(data.feedbackPK.userId);
			$('#subjectId').val(data.feedbackPK.subjectId);
		}
	})
}

function load_member_info(userId){
	$.ajax({
		type: "GET",
		url: "/trainee/member-info",
		data: {
			userId : userId,
		},
		success: (data)=>{
			console.log(data)
			$("#member-content").html(data)
		}
	})
}