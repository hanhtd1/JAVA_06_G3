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

$("#paging li a").click(function(){	
	
});


function traineeChange() {
	
	var category = document.getElementById('category').value;
	var class_name = document.getElementById('class-name').value;
	var status = document.getElementById('status').value;
	$.get({
		url: "/trainer/search?category="+category+"&clazzName="+class_name+"&status="+status,
		success: (res)=>{
			$("#trainees").html(res);
		}
	});
}

function appendClass(tag) {
	var a = document.getElementById("paging").getElementsByTagName("a");
	var category = document.getElementById('category').value;
	var class_name = document.getElementById('class-name').value;
	var status = document.getElementById('status').value;
	$.get({
		url: "/trainer/search?category="+category+"&clazzName="+class_name+"&status="+status+"&page=" + tag.innerHTML,
		success: (res)=>{
			$("#trainees").html(res);
		}
	});
}



function loadClass() {
	var clazzSearch = document.getElementById("find-class").value;
	var status = document.getElementById("status").value;
	$.get({
		url: "/trainer/clazz/content?clazz="+clazzSearch+"&status="+status,
		success: (res)=>{
			$("#clazz-content").html(res);
		}
	});
}

function clazzByPage(pageObject) {
	var clazzSearch = document.getElementById("find-class").value;
	var status = document.getElementById("status").value;
	$.get({
		url: "/trainer/clazz/content?clazz="+clazzSearch+"&status="+status+"&page="+pageObject.innerHTML,
		success: (res)=>{
			$("#clazz-content").html(res);
		}
	});
}

function loadTrainee(clazzId) {
	$.get({
		url: "/trainer/clazz/trainee?clazzId="+clazzId,
		success: (res)=>{
			console.log(res)
			$("#main-content").html(res);
		}
	});
}

function loadSubject(clazzId) {
	$.get({
		url: "/trainer/clazz/subject?clazzId="+clazzId,
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
}

function changePage(tag) {
	var category = document.getElementById('category').value;
	var class_name = document.getElementById('class-name').value;
	var status = document.getElementById('status').value;
	$.get({
		url: "/trainer/search?category="+category+"&clazzName="+class_name+"&status="+status+"&page=" + tag.innerHTML,
		success: (res)=>{
			$("#trainees").html(res);
		}
	});
}

function changeClazzPage(tag) {
	var clazzSearch = document.getElementById("find-class").value;
	var status = document.getElementById("status").value;
	$.get({
		url: "/trainer/clazz/content?clazz="+clazzSearch+"&status="+status+"&page=" + tag.innerHTML,
		success: (res)=>{
			$("#clazz-content").html(res);
		}
	});
}

function changePreviousPage(tag) {
	var a = document.getElementById("paging").getElementsByTagName("a");
	var index;
	// 0 is previous and length - 1 is next
	for (i = 1; i < a.length - 1; i++) {
		if(a[i].classList.contains('active')) {
			index = i;
			break;
		}
    }
	tag.classList.remove("active");
	if(index != 1) {
		index -= 1;
	} 
	a[index].classList.add("active");
	changeClazzPage(a[index]);
}

function changePreviousClazzPage(tag) {
	var a = document.getElementById("paging").getElementsByTagName("a");
	var index;
	// 0 is previous and length - 1 is next
	for (i = 1; i < a.length - 1; i++) {
		if(a[i].classList.contains('active')) {
			index = i;
			break;
		}
    }
	tag.classList.remove("active");
	if(index != 1) {
		index -= 1;
	} 
	a[index].classList.add("active");
	changeClazzPage(a[index]);
}

function changeNextPage(tag) {
	var a = document.getElementById("paging").getElementsByTagName("a");
	var index;
	// 0 is previous and length - 1 is next
	for (i = 1; i < a.length - 1; i++) {
		if(a[i].classList.contains('active')) {
			index = i;
			break;
		}
    }
	tag.classList.remove("active");
	if(index != a.length - 2) {
		index += 1;
	} 
	a[index].classList.add("active");
	changePage(a[index]);
}

function changeNextClazzPage(tag) {
	var a = document.getElementById("paging").getElementsByTagName("a");
	var index;
	// 0 is previous and length - 1 is next
	for (i = 1; i < a.length - 1; i++) {
		if(a[i].classList.contains('active')) {
			index = i;
			break;
		}
    }
	tag.classList.remove("active");
	if(index != a.length - 2) {
		index += 1;
	} 
	a[index].classList.add("active");
	changeClazzPage(a[index]);
}

$("#trainer-class-manage").click(()=>{
	$.get({
		url: "/trainer/class-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});

$("#trainer-trainee-manage").click(()=>{
	$.get({
		url: "/trainer/trainee-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});

$("#trainer-subject-manage").click(()=>{
	$.get({
		url: "/trainer/subject-manage",
		success: (res)=>{
			$("#main-content").html(res);
		}
	});
});

function openFeedbackForm() {
	$("#feedback-modal").openModal();
}



