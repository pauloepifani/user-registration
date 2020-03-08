<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Cadastro de Usuários</title>
<style><%@include file="/css/user-form-stylesheet.css"%></style>
</head>
<body>
	<h3>
		<a href="new">Adicionar</a>
		<a href="list">Listar todos</a>
		<a href="logout" id="logout">Sair</a>
	</h3>
	
		<form id="user-update" name="user-update">
	
		<h1>Cadastro de Usuários</h1>
		
		<div class="form-area">
		
		<input type="hidden" name="id" value="${user.id}"/>		
		
		<ul id="form-list">
			<li>
			<label for="name">Nome: </label>
			<input type="text" id="name" placeholder="Digite o nome" 
			required="required" name="name" value="${user.name}" />
			</li>
			
			<li>
			<label for="email">E-mail: </label>
			<input type="text" id="email" placeholder="Digite o email"
			required = "required" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" value="${user.email}" />
			</li>
			
			<li>
			<button type="button" id="add-phone">+</button>
			
			<c:forEach var="phone" items="${user.phoneNumbers}" varStatus="loop">
			<label id="label${loop.count}">Telefone: </label>
					<div class="phone-layout-blank" id="field${loop.count}">
					
						<input type="text" id="ddd" maxlength=2 name="ddd" value="${phone.ddd}" />
						<input type="text" id="number" maxlength=10 name="number" value="${phone.number}" />

						<c:if test="${phone.type == \"Celular\"}">
						<select name="type">
							<option value="Celular" selected>Celular</option>
							<option value="Fixo">Fixo</option>
						</select>
						</c:if>

						<c:if test="${phone.type == \"Fixo\"}">
						<select name="type">
							<option value="Celular">Celular</option>
							<option value="Fixo" selected>Fixo</option>
						</select>
						</c:if>
						
						<button type="button" id="${loop.count}" class="btn-apagar">-</button>
					</div>
			</c:forEach>
			</li>
		</ul>
			<input id="prevent" type="submit" value="Enviar" name="send">
		</div>
	</form>
	
	
	
	
	
<script><%@include file="/js/jquery.min.js"%></script>
<script>
$("#prevent").click(function(event) {
	event.preventDefault();
	
	var data = $("#user-update").serialize();
	
	$.ajax({
        url: '?' + data,
        type: 'PUT',
        data: data,
        success: function() {
        	alert("Cadastro atualizado!");
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

$("form").on('click', '.btn-apagar', function() {
	var button_id = $(this).attr("id");
	$('#field' + button_id + '').remove();
	$('#label' + button_id + '').remove();
});

var cont = 0;
$("#add-phone")
		.click(
				function() {
					cont--;
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

</script>

</body>
</html>