var currentClass;
var currentUser;
//=========================================================================
// Trainee @Author TrangDM2
function loadTrainees(){
	$("#list_trainees").html("");
	$.get({
		url: "/admin/get-users",
		data: {
			keyword: $("#find-trainee").val(),
			status: $("#trainee-status").val(),
			role: 1
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
	});
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
	this.currentUser = id;
	$.get({
		url: "/admin/user-info",
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
//unexpected
function openModal(traineeId, subjectId){
	$.get({
		url: '/admin/get-user-feedback',
		data: {
			userId: traineeId,
			subjectId: subjectId
		},
		success: resp => {
			if(resp.content===undefined){
				$('#feedback_content').html('Content not found!');
			} else {
				$('#feedback_content').html('<p>'+resp.content+'</p>');
			}
		}
	});
	$("#feedback-modal").openModal();
}
//unexpected
function editTraineeInfo(){
	$.get({
		url: "/admin/user-info",
		data: {
			id: this.currentUser
		},
		success: (resp)=>{
			$("#update_first_name").val(resp.firstName);
			$("#update_last_name").val(resp.lastName);
			$("#update_email").val(resp.email);
			$("#update_phone").val(resp.phone);
			$("#update_birthday").val(resp.birthDay);
			$("#update_account").val(resp.account);
			$('#update_gender').val(resp.gender);
		}
	});
}
function addTrainee(){
	let form = document.getElementById("add_trainee_form");
	addUser(form)
	loadTrainees();
	return false;
}
function updateTrainee(){
	let form = document.getElementById('update_trainee_form');
	updateUser(form);
	return false;
}
function changeTraineeStatus(){
	let e = $("#traineeStatus").val();
	updateUserStatus(e);
}

//=========================================================================
// CLass @Author TrangDM2
function loadClasses(){
	$("#list_classes").html("");
	$.get({
		url: "/admin/get-classes",
		data: {
			keyword: $("#find-class").val(),
			status: $("#class-status").val()
		},
		success: (resp)=>{
			resp.map((res)=>{
				this.showClassList(res)
			})
		}
	});
	return false;
}

function showClassList(res){
	$("#list_classes").append("<li>\n" +
		"<a  href=\"#!\" onclick=\"loadClassDetail("+res.id+")\" >\n" +
		"<h5 class=\"f-s-19\">"+res.name+"</h5>\n" +
		"<span>\n" +
		"<i class=\"tiny material-icons\">person_pin</i> <span>"+res.userList.length+"</span> Members\n" +
		"</span>\n" +
		"</a>\n" +
		"</li>");
}

function loadClassDetail(id){
	this.currentClass=id;
	$("#list_trainees").html("");
	$("#class-detail").show();
	$.get({
		url: "/admin/class-detail",
		data: {
			id: id
		},
		success: (resp)=> {
			$("#date-today").html(new Date().getDate() + "/" + new Date().getUTCMonth() + 1 + "/" + new Date().getFullYear());
			$("#class_info_name").html(resp.name);
			$("#class_trainees_number").html(resp.userList.length);
			$("#class_openDate").html(resp.openDate);
			$("#class_category").html(resp.category);
			$("#old-status").val(resp.status);
			loadClassListTraineeToAttendance(resp.userList);
			loadClassListTraineeToClassDetail(resp.userList);
			loadClassListTraineeToUpdateMark(resp.userList);
			loadSubjects();
			loadAllSubjectsExist();
			loadAddedSubjects();
		}
	})
}

function loadTraineesToAddToClass() {
	$('#list-trainees-to-add').html("");
	$.get({
		url: '/admin/load-add-trainees',
		data: {
			id: this.currentClass,
			keyword: $('#find-trainee').val()
		},
		success: (resp)=>{
			loadListTraineeToAddToClass(resp);
		}
	})
}

function addTraineeToClass(id) {
	$.get({
		url: '/admin/add-trainee-toclass',
		data: {
			traineeId: id,
			classId: this.currentClass
		},
		success: (resp)=>{
			loadClasses();
			loadClassDetail(this.currentClass);
			loadTraineesToAddToClass();
			Materialize.toast(resp, 4000);
		}
	})
}

function loadListTraineeToAddToClass(trainees) {
	trainees.map(trainee=>{
		$('#list-trainees-to-add').append("<tr>\n" +
			"<td>"+trainee.name+"</td>\n" +
			"<td>"+trainee.account+"</td>\n" +
			"<td>"+trainee.phone+"</td>\n" +
			"<td>\n" +
			"<button onclick='addTraineeToClass("+trainee.id+")' class=\"btn waves-effect blue p-h-xs\">\n" +
			"<i class=\"material-icons\">add</i>\n" +
			"</button>\n" +
			"</td>\n" +
			"</tr>")
	})
}

function loadClassListTraineeToClassDetail(trainees){
	$("#list_trainees").html("");
	trainees.map((trainee)=>{
		if(trainee.role==="ROLE_TRAINEE"){
			$("#list_trainees").append("<tr>\n" +
				"<td>"+trainee.account+"</td>\n" +
				"<td>"+trainee.firstName+" "+trainee.lastName+"</td>\n" +
				"<td>"+trainee.birthDay+"</td>\n" +
				"<td>"+trainee.phone+"</td>\n" +
				"<td>"+trainee.status+"</td>\n" +
				"<td><a onclick=\"loadClassTraineeInfo("+trainee.id+")\" class=\"waves-effect waves-light btn blue modal-trigger p-h-xs trainee-info\"\n" +
				"href=\"#trainee-info\">\n" +
				"<i class=\"material-icons\">perm_identity</i>\n" +
				"</a>\n" +
				"<a onclick=\"removeTraineeFromClass("+trainee.id+")\" class=\"waves-effect waves-light btn red p-h-xs\">\n" +
				"<i class=\"material-icons\">clear</i></a>\n" +
				"</td>\n" +
				"</tr>");
		}
	})
}

function loadClassListTraineeToUpdateMark(trainees){
	$("#list-trainees-marks").html("");
	trainees.map((trainee)=>{
		if(trainee.role==="ROLE_TRAINEE") {
			$("#list-trainees-marks").append("<tr class='trainee-list'>\n" +
				"<td>" + trainee.account + "</td>\n" +
				"<td>" + trainee.firstName + " " + trainee.lastName + "</td>\n" +
				"<td>" + trainee.phone + "</td>\n" +
				"<td><input type=\"hidden\" name='userId' value='"+trainee.id+"' class=\"validate\"><input type=\"number\" max='10' name='theory' class=\"validate\"></td>\n" +
				"<td><input type=\"number\" name='practice' max='10' class=\"validate\"></td>\n" +
				"</tr>");
		}
	})
}

function MarkForm2JsonMapper(form){
	let formData = form.querySelectorAll("input, select, textarea");
	let obj={};
	formData.forEach(e=>{
		let name = e.name;
		let value = e.value;
		if(name){
			obj[name]=value;
			obj["subjectId"]=$("input[name=subjectIdToMarks]:checked").val();
		}
	});
	return obj;
}

function markGenerateFormArray(elements) {
	let listElement=[];
	elements.forEach(form=>{
		listElement.push(MarkForm2JsonMapper(form));
	});
	return listElement;
}

function submitMarks() {
	let marks = document.getElementById("list-trainees-marks");
	let elements = marks.querySelectorAll(".trainee-list");
	$.post({
		url: '/admin/update-marks',
		data: JSON.stringify(markGenerateFormArray(elements)),
		contentType: "application/json",
		success: (resp)=>{
			swal("Success!", resp, "success")
		},
		error: (error)=>{
			Materialize.toast(error.responseText, 4000);
		}
	});
}

function loadAllSubjectsExist() {
	$.get({
		url: '/admin/load-all-subjects',
		success: (resp)=>{
			loadClassListSubjectToAddSubject(resp);
		}
	})
}

function loadAddedSubjects() {
	$.get({
		url: '/admin/load-added-subjects',
		data: {
			clazzId: this.currentClass
		},
		success: (resp)=>{
			loadClassListAddedSubjects(resp);
		}
	})
}

function loadSubjects() {
	$.get({
		url: '/admin/load-added-subjects',
		data: {
			clazzId: this.currentClass
		},
		success: (resp)=>{
			loadClassListSubjectToUpdateMark(resp);
		}
	})
}

function loadClassListSubjectToAddSubject(subjects){
	$("#subjects_for_add").html("");
	subjects.map((subject)=>{
		$("#subjects_for_add").append("<tr>\n" +
			"<td>"+subject.name+"</td>\n" +
			"<td>"+subject.code+"</td>\n" +
			"<td>"+subject.duration+"</td>\n" +
			"<td>\n" +
			"<button onclick='addSubjectToClass("+subject.id+")' class=\"btn waves-effect blue p-h-xs\">\n" +
			"<i class=\"material-icons\">add</i>\n" +
			"</button>\n" +
			"</td>\n" +
			"</tr>");
	})
}

function loadClassListAddedSubjects(subjects){
	$("#added_subjects").html("");
	subjects.map((subject)=>{
		$("#added_subjects").append("<tr>\n" +
			"<td>"+subject.name+"</td>\n" +
			"<td>"+subject.code+"</td>\n" +
			"<td>"+subject.duration+"</td>\n" +
			"<td>\n" +
			"<button onclick='removeSubjectFromClass("+subject.id+")' class=\"btn waves-effect orange p-h-xs\">\n" +
			"<i class=\"material-icons\">remove</i>\n" +
			"</button>\n" +
			"</td>\n" +
			"</tr>");
	})
}

function removeSubjectFromClass(id) {
	swal({
			title: "Are you sure?",
			text: "Your will delete this subject from class!",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Yes, delete it!",
			closeOnConfirm: false
		}, () => {
			$.get({
				url: '/admin/remove-subject-fromclass',
				data: {
					clazzId: this.currentClass,
					subjectId: id
				},
				success: resp=>{
					loadAllSubjectsExist();
					loadAddedSubjects();
					swal("Deleted!", resp,"success");
				}
			});
		});

}

function addSubjectToClass(id) {
	console.log(id);
	console.log(this.currentClass);
	$.get({
		url: '/admin/add-subject-to-class',
		data: {
			subjectId: id,
			classId: this.currentClass
		},
		success: (resp)=>{
			Materialize.toast(resp, 4000);
			loadClassDetail(this.currentClass);
		},
		error: (error)=>{
			Materialize.toast(error, 4000);
		}

	})
}

function loadClassListSubjectToUpdateMark(subjects){
	$("#filter-subjects").html("");
	subjects.map((subject)=>{
		$("#filter-subjects").append("<div>\n" +
			"<input id=\"subject"+subject.id+"\" value='"+subject.id+"' name=\"subjectIdToMarks\" type=\"radio\" class=\"validate subjectIdToMarks\" required>\n" +
			"<label for=\"subject"+subject.id+"\">"+subject.name+"</label>\n" +
			"</div>");
	})
}

function loadClassListTraineeToAttendance(trainees){
	$('#attendance-trainees').html("");
	trainees.map((trainee)=>{

		if(trainee.role==="ROLE_TRAINEE") {
			$('#attendance-trainees').append("<tr class='attendance-form'>" +
				"<td>" + trainee.account + "</td>\n" +
				"<td>" + trainee.firstName + " " + trainee.lastName + "</td>\n" +
				"<td>" + trainee.phone + "</td>\n" +
				"<td><input type=\"hidden\" name='userId' value='" + trainee.id + "' class=\"validate\">" +
				"<select name='type' style='display: block'>\n" +
				"<option value=\"\" selected>Choose</option>\n" +
				"<option value=\"Absent\">Absent</option>\n" +
				"<option value=\"Present\">Present</option>\n" +
				"<option value=\"Late\">Late</option>\n" +
				"</select>" +
				"</td>\n" +
				"<td><input type=\"text\" name='note' class=\"validate\"></td></tr>");
		}
	})
}

function loadClassTraineeInfo(id){
	$('#trainee-info').openModal();
	loadTraineeInfo(id);
}

function removeTraineeFromClass(id){
	swal({
		title: "Are you sure?",
		text: "Your will delete this trainee from class!",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "Yes, delete it!",
		closeOnConfirm: false
	}, () => {
		$.get({
			url: '/admin/remove-trainee',
			data: {
				traineeId: id,
				classId: this.currentClass
			},
			success: (resp)=>{
				loadClasses();
				loadClassDetail(this.currentClass);
				swal('Deleted', resp, 'success');
			}
		});
	});
}

function updateClassStatus() {
	$.get({
		url: "/admin/update-classstatus",
		data: {
			id: this.currentClass,
			status: $('#update-class-status').val()
		},
		success: (resp)=>{
			swal("Success!", resp,"success");
			loadClassDetail(this.currentClass);
			loadClasses();
		}
	})
}

function addClass(){
	let form = document.getElementById("add_class_form");
	$.post({
		url: "/admin/create-class",
		data: JSON.stringify(Form2JsonMapper(form)),
		contentType: "application/json",
		success: (resp)=>{
			Materialize.toast(resp, 4000);
			loadClasses();
		},
		error: (resp)=>{
			$.each(resp.responseJSON, (key, value)=>{
				Materialize.toast(key.toUpperCase() + ": " + value , 10000);
			})
		}
	});
	return false;
}

function loadClassInfoToEdit(){
	$.get({
		url: "/admin/get-clazz-info",
		data: {
			id: this.currentClass
		},
		success: (resp)=>{
			$("#update_class_id").val(currentClass);
			$("#update-clazzName").val(resp.name);
			$('#update-category').val(resp.category);
			$('#update-openDate').val(resp.openDate);
			$("#update-note").val(resp.note)
		}
	})
}

function editClassInfoSubmit(){
	let form = document.getElementById("update-class-form");
	$.post({
		url: "/admin/create-class",
		data: JSON.stringify(Form2JsonMapper(form)),
		contentType: "application/json",
		success: (resp)=>{
			Materialize.toast(resp, 4000);
		}
	});
	return false;
}

function submitAttendance() {
	swal({
		title: "Are you sure?",
		text: "Your will today of attendance!",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "Yes, delete it!",
		closeOnConfirm: false
	}, () => {
		let attendances = document.getElementById("attendance-trainees");
		let elements = attendances.querySelectorAll(".attendance-form");
		$.post({
			url: '/admin/do-attendance',
			data: JSON.stringify(generateFormArray(elements)),
			contentType: "application/json",
			success: (resp) => {
				swal("Success!", resp,"success");
			},
			error: (error) => {
				swal("Failed!", error, "danger");
			}
		});
	});
}

function loadTrainersToUpdate() {
	$('#inclass-trainer').html("");
	$('#list-trainers').html("");
	$.get({
		url: "/admin/get-trainers-toadd",
		data: {
			classId: this.currentClass
		},
		success: (resp)=>{
			resp.map((res)=>{
				$('#list-trainers').append("<tr>\n" +
					"<td>"+res.firstName+" "+res.lastName+"</td>\n" +
					"<td>"+res.account+"</td>\n" +
					"<td>"+res.phone+"</td>\n" +
					"<td>\n" +
					"<button onclick='addTrainerToClass("+res.id+")' class=\"btn waves-effect blue p-h-xs\">\n" +
					"<i class=\"material-icons\">add</i>\n" +
					"</button>\n" +
					"</td>\n" +
					"</tr>");
			});
		}
	});
	$.get({
		url: '/admin/get-inclass-trainer',
		data: {
			clazzId: this.currentClass
		},
		success: (resp)=>{
			resp.map((res)=>{
				$('#inclass-trainer').append("<tr>\n" +
					"<td>"+res.firstName +" "+res.lastName+"</td>\n" +
					"<td>"+res.account+"</td>\n" +
					"<td>"+res.phone+"</td>\n" +
					"<td>\n" +
					"<button onclick='removeTrainerFromClass("+res.id+")' class=\"btn waves-effect red p-h-xs\">\n" +
					"<i class=\"material-icons\">remove</i>\n" +
					"</button>\n" +
					"</td>\n" +
					"</tr>")
			})
		}
	})
}

function addTrainerToClass(id) {
	$.get({
		url: '/admin/add-trainer-toclass',
		data: {
			clazzId: this.currentClass,
			trainerId: id
		},
		success: resp=>{

			loadTrainersToUpdate();
			//TODO
		}
	});
}

function removeTrainerFromClass(id) {
	swal({
		title: "Are you sure?",
		text: "Your will delete this trainer from class!",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "Yes, delete it!",
		closeOnConfirm: false
	}, () => {
		$.get({
			url: '/admin/remove-trainer-fromclass',
			data: {
				clazzId: this.currentClass,
				trainerId: id
			},
			success: resp=>{
				loadTrainersToUpdate();
				swal("Deleted!", resp,"success");
			}
		});
	});

}
//=========================================================================
//Trainer @Author TrangDM2
function loadTrainers(){
	$("#list_trainers").html("");
	$.get({
		url: "/admin/get-users",
		data: {
			keyword: $("#find-trainer").val(),
			status: $("#trainer-status").val(),
			role: 0
		},
		success: (resp)=>{
			resp.map((res)=>{
				this.showTrainerList(res);
			})
		}
	});
	return false;
}

function showTrainerList(res){
	$("#list_trainers").append("<li>\n" +
		"<a href=\"#!\" onclick=\"loadTrainerInfo("+res.id+")\">\n" +
		"<h5 class=\"f-s-19\">"+res.name+"</h5>\n" +
		"<p><i class=\"tiny material-icons\">cake</i> "+res.birthDay+"</p>\n" +
		"<p><i class=\"tiny material-icons\">email</i> "+res.email+"</p>\n" +
		"</a>\n" +
		"</li>"
	);
}

function loadTrainerInfo(id){
	this.currentUser = id;
	$("#trainer-information").show();
	$.get({
		url: '/admin/user-info',
		data: {
			id: id
		},
		success: (resp)=> {
			$(".trainerId").val(id);
			$("#trainer-name").html(resp.firstName + " " + resp.lastName);
			$("#trainer-email").html(resp.email);
			$("#trainer-birthday").html(resp.birthDay);
			$("#trainer-phone").html(resp.phone);
		}
	});
	$.get({
		url: "/admin/trainer-class",
		data: {
			id: id
		},
		success: (resp)=>{
			trainerClassList(resp);
		}
	});
}

function trainerClassList(classes) {
	$('#list-classes').html("");
	classes.map(clazz=>{
		$('#list-classes').append("<li>\n" +
			"<div class=\"collapsible-header\">"+clazz.name+"</div>\n" +
			"<div class=\"collapsible-body\">\n" +
			"<p>"+clazz.note+"</p>\n" +
			"</div>\n" +
			"</li>")
	})
}

function editTrainerInfo(){
	$.get({
		url: "/admin/user-info",
		data: {
			id: this.currentUser
		},
		success: (resp)=>{
			$("#update_first_name").val(resp.firstName);
			$("#update_last_name").val(resp.lastName);
			$("#update_email").val(resp.email);
			$("#update_phone").val(resp.phone);
			$("#update_birthday").val(resp.birthDay);
			$("#update_account").val(resp.account);
			$('#update_gender').val(resp.gender);
		}
	});
}

function addTrainer(){
	let form = document.getElementById("add_trainer_form");
	addUser(form);
	return false;
}

function updateTrainer(){
	let form = document.getElementById('update_trainer_form');
	updateUser(form);
	return false;
}

function changeTrainerStatus(){
	let e = $("#trainerStatus").val();
	updateUserStatus(e);
}
//=========================================================================
//Function dung chung @Author TrangDM2
/**
 * @return {string}
 */
function Form2JsonMapper(form){
	let formData = form.querySelectorAll("input, select, textarea");
	let obj={};
	formData.forEach(e=>{
		let name = e.name;
		let value = e.value;
		if(name){
			obj[name]=value;
		}
	});
	return obj;
}

function generateFormArray(elements) {
	let listElement=[];
	elements.forEach(form=>{
		listElement.push(Form2JsonMapper(form));
	});
	return listElement;
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

function generateClassName() {
	$.get({
		url: "/admin/get-classname",
		data: {
			location: $("#add-class-location").val(),
			type: $("#add-class-type").val(),
			category: $("#add-class-category").val()
		},
		success: (resp)=>{
			$('#clazzName').val(resp)
		}
	})
}

function addUser(element) {
	$.post({
		url: "/admin/create-user",
		contentType: "application/json",
		data: JSON.stringify(Form2JsonMapper(element)),
		success: (resp)=>{
			Materialize.toast(resp, 4000);
			return true;
		},
		error: (resp)=>{
			$.each(resp.responseJSON, (key, value)=>{
				Materialize.toast(key.toUpperCase() + ": " + value , 10000);
			});
			return false;
		}
	});
}
function updateUser(element) {
	$.post({
		url: "/admin/update-user",
		contentType: "application/json",
		data: JSON.stringify(Form2JsonMapper(element)),
		success: (resp)=>{
			Materialize.toast(resp, 4000)
		},
		error: (resp)=>{
			$.each(resp.responseJSON, (key, value)=>{
				Materialize.toast(key.toUpperCase() + ": " + value , 10000);
			})
		}
	});
}
function updateUserStatus(element) {
	$.get({
		url: "/admin/update-status",
		data: {
			id: this.currentUser,
			status: element
		},
		success: (resp)=>{
			swal("Success!", resp,"success");
		}
	})
}