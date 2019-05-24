<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bibliothèque - Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
</head>
<body>
	<div class="image-left">
		<img src="res/library-icon.png">
	</div>
	<div class="container login-div">
		<form action="login" method="post">
			<h4>Se connecter</h4>
			<input type="text" class="form-control" name="mail"
				placeholder="Mail..."><br> <input type="password"
				class="form-control" name="password" placeholder="Mot de passe..."><br>
			<input type="submit" class="btn btn-light" name="Connection">
		</form>
		<a href="forget">Mot de passe oublié ?</a>
		<span>${error}</span>
	</div>
</body>
</html>
