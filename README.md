# :movie_camera: The Movie Club

Project developed to demonstrate the knowledge acquired during the Inatel Developer Program (IDP).

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![springboot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) ![postgresql](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white) ![eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white) 


## :scroll: About the project

The application is a REST API of a social network about cinema. It allows users to create lists of movies they want to watch, write reviews of movies they've watched, add other users as friends, and receive comments on their reviews.

## :books: Features

The API provides the following features:

- Users
	- Create
	- List
	- Detail
	- Update
	- Delete

- Reviews
	- Create
	- List
	- Detail
	- Update
	- Delete

- Movie Lists
	- Create
	- List
	- Detail
	- Update
	- Delete
	- Add movie
	- Remove movie
	- Mark movie as watched

- Comments
	- Create
	- List
	- Detail
	- Update
	- Delete

To test the API features you can use the documentation built using Swagger, just run the project and go to `localhost:8080/swagger-ui.html`.
 
## :hammer_and_wrench: Tools and technologies

The project was developed using the following tools and technologies:
- Spring Boot
- PostgreSQL ([ElephantSQL](https://www.elephantsql.com/))
- Authentication by JWT
- Swagger
- Spring Boot Admin

## :rocket: External API
The application uses the API from [The Movie Database](https://www.themoviedb.org/?language=pt-BR). This site contains information about movies, series and famous people. Using its API it is possible to perform searches in its extensive database.

## :gear: Setup
- If you are using an IDE like Eclipse, just clone the project and import it. The IDE will install dependencies and you can run.
- If you are using the command line, clone the project and run `maven install` in the root directory, then run `./mvnw spring-boot:run`.
