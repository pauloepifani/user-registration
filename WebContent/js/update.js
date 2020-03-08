//$("#prevent").click(function(event) {
//			alert("preventDefault")
//			event.preventDefault();
//			
//			var data = $("#user-update").serialize();
//			alert(data);
//			
//			
//			$.ajax({
//		        url: 'logged',
//		        type: 'PUT',
//		        data: data,
//		        success: function() {
//		    }
//			});
//		});

//var cont = 0;
//$("#add-phone")
//		.click(
//				function() {
//					cont++;
//					$(".phone-layout-blank${loop.count}")
//							.after(
//									'<label id="label'
//											+ cont
//											+ '">Telefone: </label>'
//											+ '<div class="phone-layout-blank${loop.count}" id="field'
//											+ cont
//											+ '">'
//											+ '<input name="ddd" type="text" id="ddd${loop.count}"/>'
//											+ '<input name="number" type="text" id="number${loop.count}"/>'
//											+ '<input name="type'
//											+ cont
//											+ '" type="radio" id="type${loop.count}" value="Celular">Celular'
//											+ '<input name="type'
//											+ cont
//											+ '" type="radio" id="type${loop.count}" value="Fixo">Fixo'
//											+ '<button type="button" id="'
//											+ cont
//											+ '" class="btn-apagar">-</button>');
//				});

//$("form").on('click', '.btn-apagar', function() {
//	var button_id = $(this).attr("id");
//	$('#field' + button_id + '').remove();
//	$('#label' + button_id + '').remove();
//});

