$(window).load(()=>{
	$.ajax({
		type: "GET",
		url: "/admin/home",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});


$("#class-manage").click(()=>{
	$.get({
		url: "/admin/class-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});
$("#trainee-manage").click(()=>{
	$.get({
		url: "/admin/trainee-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});
$("#subject-manage").click(()=>{
	$.get({
		url: "/admin/subject-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});
$("#trainer-manage").click(()=>{
	$.get({
		url: "/admin/trainer-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});
$("#import-export").click(()=>{
	$.get({
		url: "/admin/file-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});
$("#guide").click(()=>{
	$.get({
		url: "/admin/guide",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});


$("#grade-modal").click(()=>{
	$.get({
		url: "/admin/grade-modal",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});

$("#feedback-modal").click(()=>{
	$.get({
		url: "/admin/feedback-modal/1",
		success: (res)=>{
			$("#feedback-modal").html(res);
		}
	});
});

function setId(id) {
	$.get({
		url: "/trainer/grade/" + id,
		success: (res)=>{
			$("#grade-modal").html(res);
		}
	});
}

function traineeChange() {
	
	var category = document.getElementById('category').value;
	var class_name = document.getElementById('class-name').value;
	var status = document.getElementById('status').value;
	alert("category : " + category + ",clazz name : " + class_name + ", status : " + status);
	$.get({
		url: "/trainer/search?category="+category+"&clazzName="+class_name+"&status="+status,
		success: (res)=>{
			$("#trainees").html(res);
		}
	});
}