$("#prevent").click(function(event) {
			alert("preventDefault")
			event.preventDefault();
			
			var data = $("#user-registration").serialize();
			alert(data);
			
			
			$.ajax({
		        url: 'logged',
		        type: 'POST',
		        data: data,
		        success: function() {
		    }
			});
		});

var cont = 0;
$("#add-phone")
		.click(
				function() {
					cont++;
					$("#form-list")
							.append(
									'<label id="label'
											+ cont
											+ '">Telefone: </label>'
											+ '<div class="phone-layout-blank" id="field'
											+ cont
											+ '">'
											+ '<input name="ddd" type="text" id="ddd"/>'
											+ '<input name="number" type="text" id="number"/>'
											+ '<select name="type">'
											+ '<option value="Celular">Celular</option>'
											+ '<option value="Fixo">Fixo</option>'
											+ '</select>'
											+ '<button type="button" id="'
											+ cont
											+ '" class="btn-apagar">-</button>');
				});



$("form").on('click', '.btn-apagar', function() {
	var button_id = $(this).attr("id");
	$('#field' + button_id + '').remove();
	$('#label' + button_id + '').remove();
});
