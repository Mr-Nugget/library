<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    	<h1>Search a document :</h1>
		<form action="search">
			<input type="text" class="form-control searchBar" placeholder="Rechercher un document" name="value">
			<select name="criteria" class="form-control criteriaSelect" size="1">
				<option>Titre
				<option>Auteur
				<option>Reference
			</select> 
		</form>
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
								<th scope="col">Category</th>
								<th scope="col">Stock</th>
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
									<td>${doc.nbstock}</td>
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