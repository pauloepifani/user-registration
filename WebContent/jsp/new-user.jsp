<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<style><%@include file="/css/user-form-stylesheet.css"%></style>
<title>Cadastro de Usuários</title>
</head>
<body>
	<h3>
		<a href="new">Adicionar</a>
		<a href="list">Listar todos</a>
		<a href="logout" id="logout">Sair</a>
	</h3>
	
	<form id="user-registration" name="user-registration">
		<h1>Cadastro de Usuários</h1>
		<div class="form-area">
		
		<ul id="form-list">
			<li>
			<label for="name">Nome: </label>
			<input type="text" id="name" placeholder="Digite o nome" name="name" />
			</li>
			
			<li>
			<label for="email">E-mail: </label>
			<input type="text" id="email" placeholder="Digite o email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="email" />
			</li>
			
			<li>
			
			<label>Telefone: </label>
				<div class="phone-layout-blank">
						<input name="ddd" type="text" maxlength=2 placeholder="DDD" id="ddd"/>
						<input name="number" type="text" maxlength=10 placeholder="XXXX-XXXX" id="number"/>
						
						<select name="type">
							<option value="Celular">Celular</option>
							<option value="Fixo">Fixo</option>
						</select>
						
				<button type="button" id="add-phone">+</button>
				</div>
			</li>
		</ul>
			<input id="prevent" type="submit" value="Adicionar" name="send">
		</div>
	</form>
	
	
	
	
	
	<script type="text/javascript"><%@include file="/js/jquery.min.js"%></script>
	<script type="text/javascript">
	$("#prevent").click(function(event) {
		event.preventDefault();
		
		var data = $("#user-registration").serialize();
		
		$.ajax({
	        url: 'logged',
	        type: 'POST',
	        data: data,
	        success: function() {
	        	alert("Cadastro realizado com sucesso!");
	        	window.location.assign("list");
	    },
		    error: function() {
	    		alert("Já existe um cadastro com este e-mail!");
		    }
		});
	});
	
	$("#logout").click(function(event) {
		alert("bla");
		event.preventDefault();
		
		$.ajax({
	        url: 'logout',
	        type: 'GET',
	        success: function() {
	        	window.location.replace("../")
	    }
		});
	});

	var cont = 0;
	$("#add-phone")
			.click(
					function() {
						cont++;
						$("#form-list")
								.append(		'<li>'
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