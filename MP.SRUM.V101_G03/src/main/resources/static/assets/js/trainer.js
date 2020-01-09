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