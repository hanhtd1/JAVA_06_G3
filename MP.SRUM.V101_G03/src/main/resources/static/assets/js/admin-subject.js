function load_subjectDetails(subjectId){
	$.ajax({
		type: "GET",
		url: "/admin/class-by-subject",
		data: {
			subjectId : subjectId
		},
		success: (data)=>{
			$('#subjectDetails').html(data)
		}
	})
}

function load_subjects(){
	$.ajax({
		type: "POST",
		url: "/admin/add-subject",
		dataType: "JSON",
		data: {
			subjectName : $('#subjectName').val(),
			subjectCode : $('#code').val(),
			subjectDuration : $('#duration').val()
		},
		success: (data)=>{
			var subject = data.t;
			if(subject === null){
				Materialize.toast(data.message, 4000)
			}else{
				Materialize.toast(data.message, 4000);
//				$('#listSubjects').append('<li>\n'
//									+'<a href="" class="waves-effect waves-light"> \n'
//									+'	<h5 class="f-s-19">' + subject.name +'</h5>'
//									+'	<p> \n'
//									+ subject.code
//									+'	</p> \n'
//									+'</a> \n'
//									+'</li>')
			}
		}
	})
}

function load_Classes(){
	var status = $('#class-status').val();
	$.ajax({
		type: "GET",
		url : "/admin/search",
		data:{
			subjectStatus: status
		},
		success: (data)=>{
			console.log(data);
			$('#list-subject').html(data)
		}
	})
}

function edit_subjects(){
	$.ajax({
		type: "POST",
		url: "/admin/edit-subject",
		dataType: "JSON",
		data: {
			subjectId : $('#subjectId').val(),
			subjectName : $('#subjectName').val(),
			subjectCode : $('#code').val(),
			subjectDuration : $('#duration').val(),
			subjectStatus : $('#subjectStatus').val()
		},
		success: (data)=>{
			Materialize.toast(data.message, 4000);
			$('#duration-id').html(data.t.duration);
		}
	})
}

function load_feedback(userId, subjectId){
	$.ajax({
		type: "GET",
		url: "/admin/view-feedback",
		data: {
			userId : userId,
			subjectId : subjectId
		},
		success: (data)=>{
			$("#feedback-content").html(data);
		}
	})
}



var subId;
function get_subject(subjectId) {
	subId = subjectId;
}

function del_subject() {
	$.ajax({
		type: "DELETE",
		url : "/admin/del-subject",
		data : {
			subjectId : subId
		},
		success: (data)=>{
			console.log('del success')
		}
	})
}