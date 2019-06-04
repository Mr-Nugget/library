<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Bibliothèque Municipale - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/home.css">
  <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<%@ include file="header.jsp"%>
    <div class="principal">
      <h1>Bienvenue ${cookie.firstname.value} ${cookie['lastname'].value}</h1>
      <p>Chercher un livre à emprunter, vérifier sa disponibilité, prolongé un prêt ou consulter votre liste</p>
    </div>
  </body>
</html>