<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bibliothèque Municipale - Home</title>
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
			<c:when test="${empty listResLoans}">
				<div class="noloans"><h3>Vous n'avez pas de prêts en cours...</h3></div>
			</c:when>
			<c:otherwise>
				<h1>Liste des prêts en cours</h1>
				<div class="container loans">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Titre</th>
								<th scope="col">Auteur</th>
								<th scope="col">Date d'emprunt</th>
								<th scope="col">Date de retour</th>
								<th scope="col">Statut</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listResLoans}" var="loan">
								<tr>
									<td>${loan.doc.title}</td>
									<td>${loan.doc.author}</td>
									<td><fmt:formatDate value="${loan.beginDate.toGregorianCalendar().time}"
											pattern="dd-MM-yyyy" /></td>
									<td><fmt:formatDate value="${loan.endDate.toGregorianCalendar().time}"
											pattern="dd-MM-yyyy" /></td>
									<c:choose>
										<c:when test="${loan.status == 'IN_PROGRESS'}">
											<td><a href="extend?id=${loan.id}"
												class="btn btn-light more-infos" role="button"
												aria-pressed="true">Cliquez pour prolonger</a></td>
										</c:when>
										<c:otherwise>
											<td>Déjà Prolongé</td>
										</c:otherwise>
									</c:choose>
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
