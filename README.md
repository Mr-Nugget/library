# **Projet Library Openclassrooms**



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

**Déploiement des webservices**

1) Lancer votre serveur Glassfish via Eclipse ou en ligne de commande 

```bash
asadmin start-domain domain1
```

2) Accéder à votre manager d'application à l'url suivante :

http://localhost:4848/common/index.jsf

3) Dans l'onglet Application, cliquer sur "deploy" et chercher le fichier **library-webservice.war** dans le dossier install/war et cliquer sur OK.

4) Les webservices sont déployés, vous pouvez consulter les URL des WSDL en cliquant sur "Launch" puis en rajoutant à l'url générée **/search**



**Déploiement de l'application Web**

1) Lancer votre serveur TomCat via Eclipse ou en ligne de commande  en allant dans le dossier **bin** de votre serveur et en lançant la commande 

```bash
startup
```

2) Accéder à votre manager d'application à l'url suivante :

http://localhost:8080/manager/html

puis rentrer vos identifiant de connexion définis dans le fichier **tomcat-users.xml**

3)  Dans la section "fichier War à déployer" chercher le fichier **library-webapp.war** puis cliquer sur le lien généré dans la section Application (/library-webapp)

4) Vous voila sur la page d'accueil de l'application web

**Lancement du Batch**

Dans le dossier **install/jar**, exécuter le fichier **run-batch.sh** pour lancer le batch d'envoie de mail régulier aux utilisateurs qui n'ont pas rendu leurs prêts à temps.

#### **Test**

Un utilisateur test a été créé pour tester les différentes actions possible d'un utilisateur. Son mail est **test@openclassrooms.fr** et son mot de passe : **1234**

Pour tester le batch, on peut créer un utilisateur dans la base de donnée avec une adresse mail valide pour vérifier le bon envoie du mail. 

Par exemple :						
				

```mysql
INSERT INTO users (id, firstname, lastname, password, mail, connected) VALUES(4242, 'Jean', 'Dupond', '12345', '[votre email valide]', FALSE);

INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(4242, 500, TO_DATE('23/12/2018', 'DD/MM/YYYY'), TO_DATE('01/01/2019', 'DD/MM/YYYY'), 1);
```

### **2/ Présentation**

Library est un projet Java EE organisé et packagé à l'aide d'Apache Maven en projet multi-module.

Le projet se compose de 5 modules :

- library-webservice
- library-webapp
- library-batch
- library-modele
- library-client

Le but est de proposer aux adhérents d'une bibliothèque municipale, une application web sur laquelle ils pourront se connecter pour consulter la liste de leurs prêts (en cours ou archivés), de prolonger les prêts toujours en cours d'une durée de 4 semaines si le délais n'est pas suffisant et enfin de pouvoir rechercher les différents documents proposés à la location par la bibliothèque pour d'éventuels emprunts.

#### **La base de donnée**

La base de données se composent de 5 tables :

- USERS : pour enregistrer les informations des utilisateurs de l'application
- DOCUMENTS : pour enregistrer les différents ouvrages de la bibliothèque
- TYPES : qui définit un type de document à louer
- CATEGORIES : qui définit une catégorie de document à louer
- LOANS : pour enregistrer les prêts des utilisateurs avec deux clefs étrangères vers un document_id et un user_id

La gestion de la base de donnée s'est faites avec Postgres SQL.

#### **Le web service**

Le module library-webservice contient les DAOet les web services. On y trouve principalement les accès aux tables "users", "documents" et "loans".  Ce module implémente toute la logique métier avec les actions de connexion et de déconnexion pour les utilisateurs, la récupération des documents stockés en mémoire pour la recherche d'ouvrage et l'accès aux prêts en cours et archivés des utilisateurs avec la possibilité de prolonger un prêt. 

Ces actions ont été implémentées dans des "WebMethod" pour générer un WSDL pour chaque service (Connection, ManageLoan, Search). Le war a été déployé sur un serveur Glassfish. Les fichiers xml des WSDL générés sont enregistrés dans le dossier wsdl du dossier install.

#### **L'application web**

Le module library-webapp contient les vues, fichiers JSP accompagnés de leur feuille de style CSS. On y trouve une page d'accueil, d'information, de connexion, de recherche d'ouvrage et de visualisation des prêts en cours et archivés. 

On y trouve également les "Controllers", implémentés et paramétrés à l'aide du framework Spring MVC qui vont appeler les webservices généré grâce aux fichiers WSDL du webservice, traiter les informations reçues et rediriger l'utilisateurs vers les différentes vues. Les classes générées par le WSDL ont été enregistrées dans les package wsdl à l'aide de la commande "wsimport". Le war a été déployé sur un serveur Tomcat.

#### **Le batch**

Le module library-batch est une application lancée à l'aide du framework SpringBoot qui fait appel à un Scheduler qui va lancer une tâche tous les Lundi, Mercredi et Vendredi pour envoyer un mail de rappel aux utilisateurs dont la date de retour de prêts est expirée. Tout le corps de la tâche se trouve dans la méthode task, notamment la requête à la base de données pour récupérer ces utilisateurs grâce à Spring JDBC. et l'envoie des mail avec Spring Mail. On y retrouve également les configurations nécessaires par annotation dans la classe AppConfig.

#### **Modèle**

On y trouve les Java Bean, objet représentant les objets stockés en mémoire dans la DB.

#### **Client**

On y trouve les classes pour faire appel au webservice via les services SOAP. Ces classes ont été générées grâce aux WSDL des webservices via la commande wsimport.