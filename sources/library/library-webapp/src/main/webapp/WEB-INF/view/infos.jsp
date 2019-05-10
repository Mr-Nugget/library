<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/infos.css">
<title>Bibliothèque Municipale</title>
</head>
<body>
	<div class="container-fluid header">
		<div class="btn btn-secondary pull-right login">
			<a href="connection" class="btn btn-light" role="button"
				aria-pressed="true">Login</a>
		</div>
	</div>
	<div class="container welcome">
		<div class="adresse">
			<h1>Adresse</h1>
			<p>1 rue Fictive Paris, 75024. <br> Près de la station Hawkins ligne
				4, 8 et 19. <br> Accès par le bus 548 et 237, arrêt de la Place Rouge.</p>
				<img alt="" src="res/google-maps.png">
		</div>
		<div class="infos">
			<h1>Infos</h1>
			<p>Créer votre compte adhérent avec l'un de nos conseillers
				directement dans notre bibliothèque. Vous serez alors en mesure de
				suivre vos prêts en cours, de rechercher les documents que nous vous
				mettons à disposition, de vérifier leur disponibilité et de
				consulter la liste de vos anciens prêts. Venez nous rendre visite
				pour ouvrir votre compte adhérent et ainsi profiter de notre
				plateforme en ligne.</p>
				<img alt="" src="res/infos.png">
		</div>
		<div class="horaire">
			<h1>Horaires</h1>
			<p>
				Lundi : 14h-22h <br> Mardi : 9h-22h <br> Mercredi : 9h-22h
				<br> Jeudi : 9h-22h <br> Vendredi : 9h-22h <br>
				Samedi : 10-17h <br> Dimanche : Fermé
			</p>
			<img alt="" src="res/clock.png">
		</div>
	</div>
</body>
</html>
