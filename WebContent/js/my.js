$(document).ready(function(){
	$("#find").click(function(){
		$.ajax({
			type:"POST",
			dataType:"json",
			url: 'compareServlet',
			data:{
				"name":$("#name").val(),
			},
			error:function(){
				alert(1)
			}
		});
	})
});
