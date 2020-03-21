<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
<title>Lista de Usuários</title>
<style><%@include file="/css/user-list-style.css"%></style>
</head>

<body>
		<h3>
			<a href="new">Adicionar</a>
			<a href="list">Listar todos</a>
			<a href="logout" id="logout">Sair</a>
		</h3>
	
		<h1>Lista de Usuários</h1>
		<h3>
		<input id="search" onkeyup="search()" type="text" placeholder="Buscar...">
		</h3>
		<table>
		
			<thead>
			<tr>
				<th rowspan="2">Nome</th>
				<th rowspan="2">E-mail</th>
				<th colspan="2">Telefone</th>
			</tr>
			<tr>
				<th>DDD + Número</th>
				<th>Tipo</th>
			</tr>
			</thead>
			
			<tbody id="names">	
			<c:forEach var="user" items="${Users}">
			<tr>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>
				<c:forEach var="phone" items="${user.phoneNumbers}">
				<div class="phone-format">
						${phone.ddd}
						${phone.number}
				</div>
				</c:forEach>
				<td>
				<c:forEach var="phone" items="${user.phoneNumbers}">
				<div class="type-format">
						${phone.type}
				</div>
				</c:forEach>
				</td>
				
				<td>
					<button class="editForm" id="${user.id}">Editar</button>
					<button class="deleteForm" id="${user.id}" name="${user.name}">Excluir</button>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		
		
		
		
		
	<script><%@include file="/js/jquery.min.js"%></script>
	<script>
	$(".deleteForm").on("click", function(){
		   var userId = $(this).attr("id");
		   var userName = $(this).attr("name");
		   if(confirm("Excluir " + userName + "?")) {
			   
			$.ajax({
		        url: 'logged/?id=' + userId,
		        type: 'DELETE',
		        success: function() {
		        	location.reload();
		        }
			});
		   }
			  });
	
	$(".editForm").on("click", function(){
		   var userId = $(this).attr("id");
		   var url = 'edit?id=' + userId;
			   
			$.ajax({
		        url: url,
		        type: 'GET',
		        success: function() {
		        	window.location.assign(url);
		        }
			});
			  });
	
	$("#logout").click(function(event) {
		event.preventDefault();
		
		$.ajax({
	        url: 'logout',
	        type: 'GET',
	        success: function() {
	        	window.location.replace("../")
	    }
		});
	});
	
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

	</script>

</body>
</html>