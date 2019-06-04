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
				<div class="noloans"><h3>Vous n'avez pas de prêts archivés...</h3></div>
			</c:when>
			<c:otherwise>
				<h1>Liste des prêts archivés</h1>
				<div class="container loans">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Titre</th>
								<th scope="col">Auteur</th>
								<th scope="col">Date d'emprunt</th>
								<th scope="col">Date de retour</th>
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
