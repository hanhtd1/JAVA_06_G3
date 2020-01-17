
function setId(id) {
	$.get({
		url: "/trainer/grade/" + id,
		success: (res)=>{
			$("#grade-modal").html(res);
			$("#grade-modal").openModal();
		}
	});
}

function getFeedback(id) {
	$.get({
		url: "/trainer/subject/feedback?subjectId="+id,
		success: (res)=>{
			$("#view-feedback-modal").html(res);
			$("#view-feedback-modal").openModal();
		}
	});
}

