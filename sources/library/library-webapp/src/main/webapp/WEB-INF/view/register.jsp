<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Bibliothèque - Créer un compte</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/register.css">
</head>

<body>
  <div class="image-left">
    <a href="welcome"><img src="res/library-icon.png"></a>
  </div>
  <div class="container login-div">
    <form action="register" method="post">
      <h4>Créer un compte</h4>
      <input type="text" class="form-control" name="firstname" value="" placeholder="Nom">
      <input type="text" class="form-control" name="lastname" value="" placeholder="Prénom">
      <input type="text" class="form-control" name="mail" placeholder="Mail">
      <input type="password" class="form-control" name="password" placeholder="Mot de passe">
      <input type="password" class="form-control" name="confirm" value="" placeholder="Confirmer">
      <input type="submit" class="btn btn-light" name="Connection">
    </form>
    <a href="connection">Déjà inscrit(e)? Se connecter</a>
    <span>${error}</span>
  </div>
</body>

</html>