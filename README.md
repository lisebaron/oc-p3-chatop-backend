# OpenClassroom - Projet 3 - Développez le back-end en utilisant Java et Spring
1. [Installer et lancer le projet](#installer-et-lancer-le-projet)
2. [Installer la base de données](#installer-la-base-de-données)
3. [URL du swagger](#url-du-swagger)
## Installer et lancer le projet
1. Cloner le repo
```bash
git clone git@github.com:lisebaron/oc-p3-chatop-backend.git
```
2. Ouvrir le projet avec un IDE tel que Intellij ou Eclipse
3. Aller dans "Run" > "Edit Configurations" > "Environnent variables" et entrer cette ligne et modifier les valeurs entre crochets :
```
DATABASE_URL=jdbc:mysql://[db_url];DATABASE_USERNAME=[db_username];DATABASE_PASSWORD=[db_password];JWT_SECRET=[jwt_secret]
```
4. Run le projet

## Installer la base de données
Importez le fichier ``chatop.sql`` dans MySQL, avec des outils tel que phpmyadmin ou MySQL Workbench, ou encore la commande :
```sql
mysql -u username -p database_name < file.sql
```
## URL du swagger
http://localhost:3001/swagger-ui/index.html#/