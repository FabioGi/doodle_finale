# doodle_finale
Ce projet consiste à automatiser la gestion des sondages pour le choix d'une date relative à une réunion
# Comment installer et utiliser l'apllication
  Clonner le projet depuis le repository
## Bases de données
  Crées la base de données avec le nom "doodle_sir" sur MariaDB

## Le Back-end
 
 1 - Ouvrir le projet dans son editeur et selectionner le pom.xml pour importer le projet
 ```bash
 Sir/API/pom.xml
 ```
 2 - puis lancer l'application
 
 ##### NB: Utiliser la version 8 de java, une fois l'application lancer se rendre dans, 
  ```bash
 Sir/API/src/main/ressources/application.properties
  ```
  puis remplacer create par update, pour ne pas récréer la base de données a chaque lancement du back-end du projet
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
 ##### NB : Installez ng-cli au préalable sur votre machine

```bash
npm install -g @angular/cli
```
## Voir une demo de l'application
