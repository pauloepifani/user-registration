<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<style><%@include file="/css/login-stylesheet.css"%></style>
</head>
<body>
	
	<form class="login-form">
		
		<h1>Login</h1>
		<div class="form-area">
		<div class="message"></div>
		<ul>
			<li>
			<label for="email">E-mail: </label>
			<input type="text" id="email" placeholder="Digite o email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="email"/>
			</li>
			<li>
			<label for="password">Senha: </label>
			<input type="password" id="password" placeholder="********" required="required" size="8" maxlength="8" name="password">	
			</li>
		</ul>
			<a href="register">Cadastrar-se</a><br>
			<input id="login-submit" type="submit" value="Entrar" name="login">
		</div>
	</form>
	
	
	
	
	
	
<script type="text/javascript"><%@include file="/js/jquery.min.js"%></script>
<script type="text/javascript">
		  
$("#login-submit").click(function(event) {
	
	event.preventDefault();
	var data = $(".login-form").serialize();
	
	$.ajax({
        url: 'login',
        type: 'POST',
        data: data,
        success: function(request) {
        	window.location.assign("logged/");
        },
        error: function() {
        	//location.reload();
			$(".message").html("E-mail e/ou senha inválidos!");
        }
    
	});
});
	


</script>
</body>
</html>