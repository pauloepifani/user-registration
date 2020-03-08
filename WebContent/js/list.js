
//$(".deleteForm").on("click", function(){
//	   alert("bla");
//	   var userId = $(this).attr("id");
//	   alert(userId);
//		$.ajax({
//	        url: 'logged/?id=' + userId,
//	        type: 'DELETE',
//		});
//		  });

function search() {
	$(document).ready(
			function() {
				$("#search").on(
						"keyup",
						function() {
							var value = $(this).val().toLowerCase();
							$("#names tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});
			});
};