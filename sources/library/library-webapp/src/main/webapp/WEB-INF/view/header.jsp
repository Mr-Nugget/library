<div class="container-fluid header">
    <img src="res/library-icon.png">
    <a href="accessSearch" class="btn btn-light more-infos" role="button" aria-pressed="true">Rechercher</a>
    <a href="currentloans" class="btn btn-light more-infos" role="button" aria-pressed="true">Mes prêts en cours</a>
    <a href="currentbooking" class="btn btn-light more-infos" role="button" aria-pressed="true">Mes réservations</a>
    <a href="archivedloans" class="btn btn-light more-infos" role="button" aria-pressed="true">Historique</a>
    <a href="logout" class="btn btn-light more-infos" role="button" aria-pressed="true">Se déconnecter</a>
    <div class="account">
      <img src="res/profil.png" alt="">
      <span>Nom</span>
      <span>Prénom</span>
    </div>
  </div>
  <div class="search">
    <form action="search">
      <select id="criteria" name="criteria" class="form-control criteriaSelect" size="1">
        <option>Titre
        <option>Auteur
        <option>Reference
      </select>
      <input id="searchBar" type="text" class="form-control searchBar" placeholder="Rechercher un document" name="value">
      <button type="submit"><i class="fa fa-search"></i></button>
      </form>
  </div>