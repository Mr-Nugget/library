<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bibliothèque - Mot de passe oublié</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<h1>Mot de passe oublié</h1>
	<br>
	<h4>Entrez votre email pour réinitialiser votre mot de passe :</h4>
	<br>
	<form action="forgetresponse" method="post">
		<input type="text" class="form-control" name="mail"
			placeholder="Mail..."> <input type="submit">
	</form>
</body>
</html>