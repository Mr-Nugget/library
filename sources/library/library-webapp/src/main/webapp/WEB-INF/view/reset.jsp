<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bilbiothèque - Reinitialisation</title>
</head>
<body>
<h1>Mot de passe oublié</h1>
<br>
<h4>Entrez votre nouveau de passe :</h4>
<form action="newpassword" method="post">
	<input type="hidden" value="<%= request.getParameter("token")%>">
	<label>Nouveau mot de passe : </label><input type="password" name="password">
	<label>Confirmer :            </label><input type="password" name="confirm">
	<input type="submit">
</form>
</body>
</html>