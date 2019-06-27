<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bibliothèque Municipale - Home</title>
<link rel="stylesheet"
	href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="css/home.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="principal">
		<c:choose>
			<c:when test="${not empty listResSearch }">
				<div class="container searchResult">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Titre</th>
								<th scope="col">Auteur</th>
								<th scope="col">Reference</th>
								<th scope="col">Type</th>
								<th scope="col">Categorie</th>
								<th scope="col">Stock</th>
								<th scope="col">Disponible</th>
								<th scope="col">Réserver</th>								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listResSearch}" var="doc">
								<tr>
									<td>${doc.title}</td>
									<td>${doc.author}</td>
									<td>${doc.ref}</td>
									<td>${doc.type.label}</td>
									<td>${doc.category.label}</td>
									<td>${doc.currentStock}</td>
									<c:choose>
										<c:when test="${doc.currentStock == 0}">
											<td id="notAvailable">Disponible le <fmt:formatDate value="${doc.availableDate.toGregorianCalendar().time}" pattern="dd-MM-yyyy" /></td>
											<td><a href="">Réserver</a></td>
										</c:when>
										<c:otherwise>
											<td id="available">Disponible</td>
											<td><a href="">-</a></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<h4>${error}</h4>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>