<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<style><%@include file="/css/user-form-stylesheet.css"%></style>
<title>Cadastrar-se</title>
</head>
<body>
	<h3>
		
	</h3>
	
	<form id="user-registration" name="user-registration">
		<h1>Cadastrar-se</h1>
		<div class="form-area">
		
		<ul id="form-list">
			<li>
			<label for="name">Nome: </label>
			<input type="text" id="name" placeholder="Digite o nome" name="name" required="required"/>
			</li>
			
			<li>
			<label for="email">E-mail: </label>
			<input type="text" id="email" placeholder="Digite o email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="email" required="required"/>
			</li>
			
			<li>
			<label for="password">Senha: </label>
			<input type="password" id="password" placeholder="********" size="8" maxlength="8" name="password" required="required">	
			</li>
			
			<li>
				<button type="button" id="add-phone">+</button>
			
			<label>Telefone: </label>
				<div class="phone-layout-blank">
						<input name="ddd" type="text" maxlength=2 placeholder="DDD" id="ddd"/>
						<input name="number" type="text" placeholder="XXXX-XXXX" id="number"/>
						
						<select name="type">
							<option value="Celular">Celular</option>
							<option value="Fixo">Fixo</option>
						</select>
						
				</div>
			</li>
		</ul>
			<input id="register" type="submit" value="Cadastrar" name="send">
			<input id="back" type="submit" value="Voltar" name="send">
		</div>
	</form>
	
	
	
	
	
	<script type="text/javascript"><%@include file="/js/jquery.min.js"%></script>
	<script type="text/javascript">
	$("#register").click(function(event) {
		event.preventDefault();
		
		var data = $("#user-registration").serialize();
		
		$.ajax({
	        url: 'register',
	        type: 'POST',
	        data: data,
	        success: function() {
	        	alert("Cadastro realizado com sucesso!");
	        	window.location.assign("login");
	    	},
	    	error: function() {
	    		alert("Já existe um cadastro com este e-mail!");
	    	}
		});
	});
	
	$("#back").click(function(event) {
		event.preventDefault();
		
		window.location.assign("login")
	});

	var cont = 0;
	$("#add-phone")
			.click(
					function() {
						cont++;
						$("#form-list")
								.append(		  '<li>'
												+ '<label id="label'
												+ cont
												+ '">Telefone: </label>'
												+ '<div class="phone-layout-blank" id="field'
												+ cont
												+ '">'
												+ '<input name="ddd" type="text" maxlength=2 placeholder="DDD" id="ddd"/>'
												+ '<input name="number" type="text" maxlength=10 placeholder="XXXX-XXXX" id="number"/>'
												+ '<select name="type">'
												+ '<option value="Celular">Celular</option>'
												+ '<option value="Fixo">Fixo</option>'
												+ '</select>'
												+ '<button type="button" id="'
												+ cont
												+ '" class="btn-apagar">-</button>'
												+ '</li>');
					});

$("form").on('click', '.btn-apagar', function() {
var button_id = $(this).attr("id");
$('#field' + button_id + '').remove();
$('#label' + button_id + '').remove();
});
	</script>

</body>
</html>