var currentTrainee;
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
			$("#trainee-name").html(resp.firstName + resp.lastName)
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
			resp.map((res)=>{
				$("#scores").append("<tr>\n" +
					"<td>"+res.subjectName+"</td>\n" +
					"<td>"+res.theory+"</td>\n" +
					"<td>"+res.practice+"</td>\n" +
					"<td>"+res.status+"</td>\n" +
					"<td><a" +
					"\" class=\"modal-trigger waves-effect waves-blue btn m-t-xs\"\n" +
					"href=\"#!\">View feedback</a></td>\n" +
					"</tr>")
			})
		}
	});
}

function editTraineeInfo(){
	console.log(this.currentTrainee)
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
			$("#update_account").val(resp.account)
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
	createForm["gender"]=$('#add_gender').val()
	$.post({
		url: "/admin/create-user",
        contentType: "application/json",
        data: JSON.stringify(createForm),
		success: (resp)=>{
			Materialize.toast(resp, 4000)
		}
	})
	return false;
}

function generateAccountAndEmail(){
	
}