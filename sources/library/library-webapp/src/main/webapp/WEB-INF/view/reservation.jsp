<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bibliothèque Municipale - Réservations</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/loan.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="principal">
		<c:choose>
			<c:when test="${empty listRes}">
				<div class="noloans"><h3>Vous n'avez pas de réservations actives...</h3></div>
			</c:when>
			<c:otherwise>
				<h1>Liste des réservations</h1>
				<div class="container loans">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Titre</th>
								<th scope="col">Auteur</th>
								<th scope="col">Référence</th>
								<th scope="col">Date de retour</th>
								<th scope="col">Position dans la file</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listRes}" var="resa">
								<tr>
									<td>${resa.title}</td>
									<td>${resa.author}</td>
									<td>${resa.ref}</td>
									<td><fmt:formatDate value="${resa.returnDate.toGregorianCalendar().time}"
											pattern="dd-MM-yyyy" /></td>
									<td>${resa.position }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
</body>
</html>
