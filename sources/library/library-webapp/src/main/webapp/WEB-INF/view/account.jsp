<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bibliothèque Municipale - Home</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/account.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container main">
		<form action="confirmRecall">
			<span>Souhaitez-vous recevoir des mails de rappel lorsque vos prêt arrivent à terme ?</span>
			<c:choose>
				<c:when test="${ mailRecall }">
					<input id="yes" type="radio" value="true" name="mailRecall" checked>
					<label for="yes">Oui</label>
					<input id="no" type="radio" value="false" name="mailRecall">
					<label for="no">Non</label>
				</c:when>
				<c:otherwise>
					<input id="yes" type="radio" value="true" name="mailRecall">
					<label for="yes">Oui</label>
					<input id="no" type="radio" value="false" name="mailRecall" checked>
					<label for="no">Non</label>
				</c:otherwise>
			</c:choose>
			<input type="submit" name="valider">
		</form>
	</div>
</body>
</html>