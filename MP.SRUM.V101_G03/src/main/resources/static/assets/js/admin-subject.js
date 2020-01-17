var subId;
function load_subjectDetails(subjectId){
	subId = subjectId;
	$('#subjectDetails').show();
	$.ajax({
		type: "GET",
		url: "/admin/load-subject",
		dataType: "JSON",
		data: {
			subjectId : subjectId
		},
		success: (data)=>{
			$('#subject-name').html(data.name);
			$('#subject-code').html(data.code);
			$('#subject-duration').html(data.duration);
			$('#subject-active').html("");
			if(data.status != 'In Active'){
				$('#subject-active').append('<a class="modal-trigger waves-effect waves-grey btn-flat m-t-xs"'
									+' href="#update-subject-modal" onclick="editModal()">Edit</a> <a'
									+' class="waves-effect waves-red btn-flat m-t-xs modal-trigger"'
									+' href="#update-confirm"'
									+' onclick="get_subject('+ data.id +')">Delete</a>');
			};
			$('#edit-subjectName').attr("value", data.name);
			$('#edit-subjectCode').attr("value", data.code);
			$('#edit-subjectDuration').attr("placeholder", data.duration);
			$('#edit-subjectId').attr("value", data.id);
			$('#edit-subjectStatus').attr("value", data.status);
		}
	});
	$.ajax({
		type: "GET",
		url: "/admin/load-class",
		data: {
			subjectId : subjectId
		},
		success: (data)=>{
			console.log("details");
			$('#details-subject-class').html(data);
		}
	})
}

function editModal(){
	$('#update-subject-modal').openModal();
}

function get_subject(subjectId) {
	$('#update-confirm').openModal();
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
			subjectId : $('#edit-subjectId').val(),
			subjectName : $('#edit-subjectName').val(),
			subjectCode : $('#edit-subjectCode').val(),
			subjectDuration : $('#edit-subjectDuration').val(),
			subjectStatus : $('#edit-subjectStatus').val()
		},
		success: (data)=>{
			Materialize.toast(data.message, 4000);
			$('#subject-duration').html(data.t.duration);
		}
	})
}

function load_feedback(userId){
	console.log(subId);
	$.ajax({
		type: "GET",
		url: "/admin/view-feedback",
		data: {
			userId : userId,
			subjectId : subId
		},
		success: (data)=>{
			console.log(data);
			$("#feedback-content").html(data);
			$('#feedback-modal').openModal();
		}
	})
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