# TAA 2023-24

***

Développeurs (M2 IL) :
* DUJARDIN Nolwenn
* BOUGAUD Jean-Baptiste

***

## Modèle Objet

![Modele.png](img%2FModele.png)

***

## TP1 : JPA

### Lancer le projet

* Lancez le fichier run-hsqldb-server (.sh sur Linux, .bat sur Windows)
* Lancez show-hsqldb (.sh sur Linux, .bat sur Windows)
* Connectez-vous à la base de données avec login="sa", pas de mot de passe et l'URL de connexion = "jdbc:hsqldb:hsql://localhost/"
* Lancez la classe JpaTest pour peupler la base de données.

On retrouve dans la base, les données suivantes :
* Table User :<br/>
![table1.png](img%2Ftable1.png)
* Table RDV :<br/>
![table2.png](img%2Ftable2.png)

## TP2 - Partie 1 : Servlets (branche tp2)

### Lancer les servlets
* Clic droit sur le projet. Run as -> Maven build... -> Mettre compile jetty:run
* Pour afficher les utilisateurs rendez-vous ici : http://localhost:8080/user

![table_servlet.png](img%2Ftable_servlet.png)