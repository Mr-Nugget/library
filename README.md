# **Projet Library Openclassrooms 1.1.0**



### **1/ Installation**

On retrouve dans le dossier installation du projet les WAR de l'application web et des web services ainsi qu'un script pour lancer le jar du batch. On y trouve également un projet Soap UI pour tester le bon fonctionnement des webservices par WSDL. Le dossier sql contient les scripts pour déployer la base de données, BDD, tables et valeurs d'exemple.

**Déploiement de la DB**

Connectez-vous à la console psql en lançant la commande : psql

<pre><code>\i create_db.sql
\i create_tables.sql
\i insert_datas.sql
</code></pre>
Le script create_db va créer un utilisateur openclassrooms avec le mot de passe admin et créer la base de donnée library.

Le script create_table va créer les tables énumérées précédemment.

Le script insert_data va créer des valeurs d'exemple, notamment un utilisateur test, des documents et des prêts associés.

Déployer ensuite les war sur un serveur en local. Glassfish a été utilisé pour les webservices et Tomcat pour l'application web. 

**Mise à jour de la DB**

Dans le dossier update des scripts de mise à jour sont à déployer pour ajouter les tables WAITINGLIST et POSITION et ajouter certains attribues aux tables déjà déployées pour la version 1.1.0.

**Déploiement des webservices**

1) Lancer votre serveur Tomcat via Eclipse ou en ligne de commande 

```bash
startup
```

2) Accéder à votre manager d'application TOMCAT

3) Dans l'onglet Application, cliquer sur "deploy" et chercher le fichier **library-webservice.war** dans le dossier target du module library-webservice et cliquer sur OK.

4) Les webservices sont déployés, vous pouvez consulter les URL des WSDL en cliquant sur "Launch" puis en rajoutant à l'url générée **/search**

**Mise a jour de l'url des webservices**

Dans la module library-client éditez le fichier deploy-wsdl.sh et remplacez les urls où se trouvent les webservices précédemment déployés puis exécutez le script.

Lancer un maven "clean install" sur le module webservice et batch pour actualiser les urls.

**Déploiement de l'application Web**

1) Lancer votre serveur TomCat via Eclipse ou en ligne de commande  en allant dans le dossier **bin** de votre serveur et en lançant la commande 

```bash
startup
```

2) Accéder à votre manager d'application puis rentrer vos identifiant de connexion définis dans le fichier **tomcat-users.xml**

3)  Dans la section "fichier War à déployer" chercher le fichier **library-webapp.war** dans la dossier target du module library-webapp.

4) Vous voila sur la page d'accueil de l'application web

**Lancement des Batch**

Dans le dossier **install/jar**, exécuter le fichier **run-batch.sh** pour lancer les batch d'envoie de mail régulier aux utilisateurs qui n'ont pas rendu leurs prêts à temps, de mail de rappel pour les utilisateurs ayant leurs prêt qui arrivent à terme (5 jours) et de mise à jour automatique de la file d'attente pour les utilisateurs qui n'ont pas récupéré leur réservation sous 48h.

#### **Test**

Un utilisateur test a été créé pour tester les différentes actions possible de l'application web et du batch. Son mail est **test@openclassrooms.fr** et son mot de passe : **1234**.

Il est désormais possible de créer soit même un utilisateur directement via l'interface web en cliquant sur "Créer un compte".

Pour tester le batch, on peut créer un utilisateur dans la base de donnée avec une adresse mail valide pour vérifier le bon envoie du mail. 

Par exemple :						
				

```mysql
INSERT INTO users (id, firstname, lastname, password, mail, connected) VALUES(4242, 'Jean', 'Dupond', '12345', '[votre email valide]', FALSE);

INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(4242, 500, TO_DATE('23/12/2018', 'DD/MM/YYYY'), TO_DATE('01/01/2019', 'DD/MM/YYYY'), 1);
```

Des fichiers insert_data_*.sql se trouve dans le dossier install de l'application pour tester les nouvelles fonctionnalité comme le batch de rappel et l'automatisation du retour de prêt.

### 2/ Présentation

Library est un projet Java EE organisé et packagé à l'aide d'Apache Maven en projet multi-module.

Le projet se compose de 5 modules :

- library-webservice
- library-webapp
- library-batch
- library-modele
- library-client

Le but est de proposer aux adhérents d'une bibliothèque municipale, une application web sur laquelle ils pourront se connecter pour consulter la liste de leurs prêts (en cours ou archivés), de prolonger les prêts toujours en cours d'une durée de 4 semaines si le délais n'est pas suffisant et enfin de pouvoir rechercher les différents documents proposés à la location par la bibliothèque pour d'éventuels emprunts, de réserver un ouvrage qui n'est plus en stock, de suivre ses réservation et de désactiver ou activer l'option de rappel de mail.

#### **La base de donnée**

La base de données se composent de 7 tables :

- USERS : pour enregistrer les informations des utilisateurs de l'application
- DOCUMENTS : pour enregistrer les différents ouvrages de la bibliothèque
- TYPES : qui définit un type de document à louer
- CATEGORIES : qui définit une catégorie de document à louer
- LOANS : pour enregistrer les prêts des utilisateurs avec deux clefs étrangères vers un document_id et un user_id
- WAITINGLIST : pour créer des file d'attente lorsqu'un ouvrage n'est plus en stock
- POSITION : associé à WAITINGLIST pour stocker les postions des utilisateurs dans la file

La gestion de la base de donnée se fait avec Postgres SQL.

#### **Le web service**

Le module library-webservice contient les DAOet les web services. On y trouve principalement les accès aux tables "users", "documents", "loans" et "waitingList".  Ce module implémente toute la logique métier avec les actions de connexion et de déconnexion pour les utilisateurs, la récupération des documents stockés en mémoire pour la recherche d'ouvrage et l'accès aux prêts en cours et archivés des utilisateurs avec la possibilité de prolonger un prêt. 

Depuis la version 1.1.0, on utilise SpringJDBC pour l'accès aux bases de données.

Ces actions ont été implémentées dans des "WebMethod" pour générer un WSDL pour chaque service (Connection, ManageLoan, Search, WaitingList). Le war a été déployé sur un serveur Tomcat. Les fichiers xml des WSDL générés sont enregistrés dans le dossier wsdl du dossier install.

#### **L'application web**

Le module library-webapp contient les vues, fichiers JSP accompagnés de leur feuille de style CSS. On y trouve une page d'accueil, d'information, de connexion, de recherche d'ouvrage et de visualisation des prêts en cours et archivés. 

On y trouve également les "Controllers", implémentés et paramétrés à l'aide du framework Spring MVC qui vont appeler les webservices généré grâce aux fichiers WSDL du webservice, traiter les informations reçues et rediriger l'utilisateurs vers les différentes vues. Les classes générées par le WSDL ont été enregistrées dans les package wsdl à l'aide de la commande "wsimport". Le war a été déployé sur un serveur Tomcat.

#### **Le batch**

Le module library-batch est une application lancée à l'aide du framework SpringBoot qui fait appel à un Scheduler qui va lancer une plusieurs tâches à temps régulier. La première pour envoyer un mail de rappel aux utilisateurs dont la date de retour de prêts est expirée. La deuxième envoit un rappel aux utilisateurs dont la date de retour de prêt arrive à son terme. Et la troisième va mettre a jour la file d'attente d'un ouvrage indisponible si un utilisateur n'est pas venu chercher à temps sa réservation. Tout le corps de la tâche se trouve dans la méthode task des trois classes. On y retrouve également les configurations nécessaires par annotation dans la classe AppConfig.

#### **Modèle**

On y trouve les Java Bean, objet représentant les objets stockés en mémoire dans la DB.

#### **Client**

On y trouve les classes pour faire appel au webservice via les services SOAP. Ces classes ont été générées grâce aux WSDL des webservices via la commande wsimport.
