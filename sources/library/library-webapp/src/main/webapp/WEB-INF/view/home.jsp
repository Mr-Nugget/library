<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Bibliothèque Municipale - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/home.css">
  </head>
  <body>
    <div class="container-fluid header">
      <img src="res/library-icon.png">
    </div>
    <div class="container-fluid menu">
      <a href="accessSearch" class="btn btn-light more-infos" role="button" aria-pressed="true">Rechercher</a>
      <a href="currentloans" class="btn btn-light more-infos" role="button" aria-pressed="true">Mes prêts en cours</a>
      <a href="archivedloans" class="btn btn-light more-infos" role="button" aria-pressed="true">Historique</a>
      <a href="logout" class="btn btn-light more-infos" role="button" aria-pressed="true">Se déconnecter</a>
    </div>
    <div class="principal">
      <h1>Bienvenue ${cookie.firstname.value} ${cookie['lastname'].value}</h1>
      <p>Chercher un livre à emprunter, vérifier sa disponibilité, prolongé un prêt ou consulter votre liste</p>
    </div>
  </body>
</html>