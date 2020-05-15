# doodle_finale
Ce projet consiste à automatiser la gestion des sondages pour le choix d'une date relative à une réunion.

Une personne crée un sondage et l'envoie, avec différentes dates et heures en proposition, qu'il soumet à un ensemble 
de personnes invité par mail. Ceux-ci ont la possibilité de sélectionner des créneaux entre celles proposée. 

Le participant au sondage (qui peut être aussi celui qui a créé le sondage) dispose d'un Dashboard qui lui permet de voir l'ensemble des sondages qu'il a créés, ceux auxquels il a participé, voir les détails d'un sondage, celui validé par le créateur. 

Il peut voir entre autres la liste des participants, leurs préférences alimentaires, un résumé de la réunion, le code QR unique pour entrer dans le bâtiment pour une réunion spécifique. 

Pour pouvoir participer au sondage, il faut s'authentifier (avoir un compte ou s'en créer). 

Dans le processus de création tu renseignes tes allergies et préférences alimentaires qui seront pris en compte lors de pause si la réunion en contient, pour un éventuel échange culinaire. 
## Voir une demo de l'application (10 min)
https://drive.google.com/drive/folders/108mWXN2I4dXlS-sAtOeWK-WI-a0MJmnZ?usp=sharing

# Comment installer et utiliser l'application
  Clonner le projet depuis le repository
## Bases de données et serveur mail
  Crées la base de données avec le nom "doodle_sir" sur phpmyadmin
  
  Configurer le serveur fakesmtp sur le port 2020
  ###### <a>http://nilhcem.com/FakeSMTP/</a>

## Le Back-end
 
 1 - Ouvrir le projet dans son éditeur et selectionner le pom.xml pour importer le projet (import de projet maven)
 ```bash
 Sir/API/pom.xml
 ```
 2 - puis lancer l'application
 
 ##### NB: Utiliser la version 8 de java, une fois l'application lancer se rendre dans, 
  ```bash
 Sir/API/src/main/ressources/application.properties
  ```
  puis remplacer create par update, pour ne pas récréer la base de données à chaque lancement du back-end du projet.
  ```bash
  spring.jpa.hibernate.ddl-auto =update
  ```
 
## Le front-end

  1- Ouvrir le projet dans son editeur
   ```bash
  Sir/WEB
   ```
  ```bash
  2- npm install
  ```
  Lancer le serveur sur le port 5000
  ```bash
  3 - ng serve --port 5000
   ```
