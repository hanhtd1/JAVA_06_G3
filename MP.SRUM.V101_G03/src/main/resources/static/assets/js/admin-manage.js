var currentTrainee;
function loadTrainees(){
	$("#list_trainees").html("");
	$.get({
		url: "/admin/get-trainees",
		data: {
			keyword: $("#find-trainee").val(),
			status: $("#trainee-status").val()
		},
		success: (resp)=>{
			resp.map((res)=>{
				if(res.className==null){
					this.showTraineeList(res, "Not in any class yet!");
				} else {
					this.showTraineeList(res, res.className);
				}
			})
		}
	})
	return false;
}
function showTraineeList(res, className){
	$("#list_trainees").append("<li>\n" +
			"<a href=\"#!\" onclick=\"loadTraineeInfo("+res.id+")\">\n" +
			"<h5 class=\"f-s-19\">"+res.name+"</h5>\n" +
			"<p><i class=\"tiny material-icons\">cake</i> "+res.birthDay+"</p>\n" +
			"<p><i class=\"tiny material-icons\">email</i> "+res.email+"</p>\n" +
			"<p><i class=\"tiny material-icons\">school</i> "+className+"</p>\n" +
			"</a>\n" +
			"</li>"
	);
}

function loadTraineeInfo(id){
	$("#scores").html("")
	$("#trainee-information").show();
	this.currentTrainee = id;
	$.get({
		url: "/admin/trainee-info",
		data: {
			id: id
		},
		success: (resp)=>{
			$(".traineeId").val(id);
			$("#trainee-name").html(resp.firstName + " " +resp.lastName)
			$("#trainee-email").html(resp.email)
			$("#trainee-birthday").html(resp.birthDay)
			$("#trainee-phone").html(resp.phone)
			$("#trainee-account").html(resp.account)
		}
	});
	$.get({
		url: "/admin/trainee-score",
		data: {
			id: id
		},
		success: (resp)=>{
			let feedbackBtn;
			resp.map((res)=>{
				if(res.practice==null || res.theory==null){
					feedbackBtn="";
				} else {
					feedbackBtn="<a onclick='openModal("+res.traineeId+", "+res.subjectId+")' \" class=\"modal-trigger waves-effect waves-blue btn m-t-xs\"\n href=\"#!\">View feedback</a>";
				}
				this.showScoreList(res,feedbackBtn);
			})
		}
	});
}
function showScoreList(res, feedbackBtn){
	$("#scores").append("<tr>\n" +
		"<td>"+res.subjectName+"</td>\n" +
		"<td>"+res.theory+"</td>\n" +
		"<td>"+res.practice+"</td>\n" +
		"<td>"+res.status+"</td>\n" +
		"<td>"+feedbackBtn+"</td>\n" +
		"</tr>")
}

function openModal(){
	$("#feedback-modal").show();
}

$("#exit-feedback").click(()=>{
	$("#feedback-modal").hide();
})

function editTraineeInfo(){
	$.get({
		url: "/admin/trainee-info",
		data: {
			id: this.currentTrainee
		},
		success: (resp)=>{
			$("#update_first_name").val(resp.firstName)
			$("#update_last_name").val(resp.lastName)
			$("#update_email").val(resp.email)
			$("#update_phone").val(resp.phone)
			$("#update_birthday").val(resp.birthDay)
			$("#update_account").val(resp.account)
			$('#update_gender').val(resp.gender)
		}
	});
}

function addTrainee(){
	let createForm = {}
	createForm["firstName"]=$("#add_first_name").val()
	createForm["lastName"]=$("#add_last_name").val()
	createForm["phone"]=$("#add_phone").val()
	createForm["email"]=$("#add_email").val()
	createForm["account"]=$("#add_account").val()
	createForm["birthDay"] = $('#add_birthday').val()
	createForm["role"]=$('#add_role').val()
	createForm["gender"]=$('#add_gender').val()
	$.post({
		url: "/admin/create-user",
        contentType: "application/json",
        data: JSON.stringify(createForm),
		success: (resp)=>{
			Materialize.toast(resp, 4000)
		},
		error: (resp)=>{
			Materialize.toast(resp.responseText, 4000)
		}
	})
	return false;
}

function updateTrainee(){
	let updateForm = {}
	updateForm["id"]=$("#update_id").val()
	updateForm["firstName"]=$("#update_first_name").val()
	updateForm["lastName"]=$("#update_last_name").val()
	updateForm["phone"]=$("#update_phone").val()
	updateForm["email"]=$("#update_email").val()
	updateForm["account"]=$("#update_account").val()
	updateForm["birthDay"] = $('#update_birthday').val()
	updateForm["gender"]=$('#update_gender').val()
	$.post({
		url: "/admin/update-user",
        contentType: "application/json",
        data: JSON.stringify(updateForm),
		success: (resp)=>{
			Materialize.toast(resp, 4000)
		},
		error: (resp)=>{
			Materialize.toast(resp.responseText, 4000)
		}
	})
	return false;
}

function generateAccountAndEmail(){
	$.get({
		url: "/admin/generate-account",
		data: {
			firstName: $("#add_first_name").val(),
			lastName: $("#add_last_name").val()
		},
		success: (resp)=>{
			$("#add_account").val(resp);
			$("#add_email").val(resp+"@fsoft.com.vn");
		}
	})
}

function changeTraineeStatus(){
	$.get({
		url: "/admin/update-status",
		data: {
			id: $("#statusTraineeId").val(),
			status: $("#traineeStatus").val()
		},
		success: (resp)=>{
			Materialize.toast(resp, 4000)
		}
	})
}