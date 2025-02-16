# Ticket Miagester

Ce projet a été réalisé dans le cadre du cours de Systèmes d'Information Répartis (SIR), et a pour but de mettre en place une application web et mobile permettant aux utilisateurs d'acheter des tickets de concert en ligne.

## Sommaire
1. [Prérequis](#prérequis)
2. [Installation et lancement](#Installation-et-lancement)
3. [Structure du projet](#structure-du-projet)
    - [Arborescence](#arborescence)
    - [Domain](#domain)
    - [DAO](#dao)
    - [Service](#service)
    - [DTO](#dto)
    - [Ressource](#ressource)
    - [JPATest](#JPATEST)
    - [Swagger](#swagger)
4. [Qualité du Code](#qualité-du-code)
5. [Configuration](#configuration)
6. [RestServer](#restserver)
7. [A venir](#a-venir)

## Prérequis
   Avant de bien démarrer l'ensemble du projet, assurez-vous les éléments suivants :
   - [java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
   - [maven](https://maven.apache.org/download.cgi)
   - Un IDE compatible (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Installation et lancement
Cloner le projet
```sh
git clone https://github.com/ChristianPeuffier/JaxRSOpenAPI.git
cd JaxRSOpenAPI
```
Lancer la base de données
```sh
# Pour Windows
.\run-hsqldb-server.bat

# Pour Linux et MacOS
./run-hsqldb-server.sh
```
Pour afficher la base de données
```sh
# Pour Windows
.\show-hsqldb.bat

# Pour Linux et MacOS
./show-hsqldb.sh
```
Lancer le serveur

Pour lancer le serveur, il suffit de lancer la classe
`RestServer.java`. Cela va démarrer le serveur sur le port **8080**.


## Structure du projet
### Arborescence
```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── fr.istic.taa.jaxrs
│   │   │   │   ├── dao
│   │   │   │   ├── domain
│   │   │   │   ├── dto
│   │   │   │   ├── rest
│   │   │   │   ├── service
│   │   │   │   ├── uml
│   │   │   │   ├── JPATest.java
│   │   │   │   ├── RestServer.java
│   │   │   │   ├── TestApplication.java
│   │   ├── resources
│   │   ├── webapp
│   ├── pom.xml
│   ├── README.md
```
### Domain

Pour réaliser ce projet, nous avons d'abord commencé par créer le domain, qui regroupe toutes nos classes principales permettant de modéliser les différents objets : **Utilisateur, Organisateur, Administrateur, Evenement, Stats et Ticket**.

Chaque classe est une **entité** qui sera créée dans notre base de données.

Les classes **Administrateur** et **Organisateur**, qui héritent d'**Utilisateur**, possèdent un `@DiscriminatorValue`. Elle permet d'indiquer, lors de la création d'un administrateur ou d'un organisateur, le type spécifique de l'utilisateur dans la colonne `type_utilisateur`.

Il y a également une énumération **StatutTicket** qui permet de définir les différents statuts d'un ticket.

### Dao

Dans un second temps, nous avons poursuivi avec la mise en place des classes **DAO.** Elles permettent d’interagir avec la base de données et d’exécuter des opérations **CRUD** sur les entités.

Grâce à `extends AbstractJpaDao`, les classes bénéficient de la structure générique proposée par JPA qui permet d’effectuer des requêtes sans avoir à réécrire la logique et la persistance pour les requêtes de base, comme `find`, `findAll`, `save`, `update`, `delete`.

Créer ces classes nous permet également d’isoler la logique d’accès aux données et de séparer les responsabilités dans l'application.

### Service

Nos classes service nous permettent de centraliser la logique métier, ce qui nous permet de créer nos différents besoins pour poursuivre l’application.

Elles servent d’intermédiaire entre la couche DAO et la couche Ressource.

### Dto

La couche DTO permet de restituer uniquement les données nécessaires, contrairement à la DAO, qui retourne l’intégralité des données de l’objet.

Cela permet de limiter l’exposition de données sensibles et améliore la sécurité en évitant d’exposer directement la structure de la base de données.

Les données renvoyées sont sous un format json.

### Ressource

Les classes ressource permettent d’interagir avec la partie web. Elles interagissent avec les classes services et les requêtes HTTP des clients. On utilise les méthodes des services pour récupérer les données souhaitées.

### JPATest

Dans cette classe, nous avons créé des utilisateurs, des événements, etc. Par la suite, si nous avons besoin de créer de nouveaux éléments, nous passerons par Swagger, pour des raisons de simplicité.

### Swagger

Swagger est un outil qui permet de documenter les API REST. Il permet de générer une documentation à partir des annotations JAX-RS. Il est accessible à l'adresse suivante (après avoir lancé le serveur) :

http://localhost:8080/api/

## Qualité du Code

Dans le fichier `pom.xml`, nous avons ajouté plusieurs plugins de reporting pour assurer la qualité du code. Ces plugins incluent :

- **maven-javadoc-plugin** : génère la documentation du code source.
- **maven-pmd-plugin** : détecte les erreurs potentielles et les mauvaises pratiques.
- **maven-checkstyle-plugin** : applique les règles de style de code.
- **maven-jxr-plugin** : facilite la navigation dans le code source.

Pour générer les rapports, on utilise la commande `mvn clean sit`:

On obtient le rapport sur la page html qui se trouve : `target/site/index.html`.

## Configuration

Elle permet de configurer les ressources de l’application.

L'annotation `@ApplicationPath("/")` définit le chemin racine de l'API, ce qui signifie que toutes les ressources seront accessibles à partir de l'URL de base du serveur.

Si on rajoute une classe, nous devons le spécifier ici.

Grâce à ça l’app peut gérer des requêtes REST et générer la documentation pour swagger.

## RestServer

La classe `RestServer` initialise et démarre un service sur le port **8080**. Elle déploie l'application `TestApplication`.

## A venir

Pour le moment la structure globale du Back-End est en place, il reste à implémenter les fonctionnalités de l'application, telles que la gestion des utilisateurs, des événements, des tickets, etc.

Dans l'idéal, il faudrait faire un premier jet du front pour pouvoir cibler les logiques métiers à implémenter.

Il faudrait également ajouter des tests unitaires pour garantir le bon fonctionnement de l'application.

