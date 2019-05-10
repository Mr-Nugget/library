<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/index.css">
<title>Bibliothèque Municipale</title>
</head>
<body>
	<div class="container-fluid header">
		<div class="btn btn-secondary pull-right login">
			<a href="connection" class="btn btn-light" role="button"
				aria-pressed="true">Login</a>
		</div>
	</div>
	<div class="container welcome">
		<div class="container-fluid">
			<h1>Bienvenue sur le site de la Bibliothèque</h1>
		</div>
		<div class="container-fluid">
			<h4>Cherchez et réservez ce dont vous avez besoin...</h4>
		</div>
		<div class="container-fluid">
			<a href="infos" class="btn btn-light more-infos" role="button"
				aria-pressed="true">Plus d'infos</a>
		</div>
	</div>
</body>
</html>
