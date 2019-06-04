<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/forget.css">
  <title>Bilbiothèque - Reinitialisation</title>
</head>

<body>
  <header class="container-fluid">
    <h1>Bibliothèque Municipale</h1>
    <img src="res/library-icon.png" alt="">
  </header>
  <div class="container main">
    <h1>Mot de passe oublié</h1>
    <br>
    <h4>Entrez votre nouveau de passe :</h4>
    <form action="newpassword" method="post">
      <input type="hidden" name="token" value="<%= request.getParameter("token")%>">
      <label>Nouveau mot de passe : </label>
      <input type="password" name="password" placeholder="Mot de passe">
      <label>Confirmer : </label>
      <input type="password" name="confirm" placeholder="Confirmer">
      <input type="submit">
    </form>
  </div>
</body>

</html>