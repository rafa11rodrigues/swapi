# SW API

## What is it?

A simple project to practice. It consumes some endpoints of [Star Wars API](https://swapi.dev/) and stores some information about planets in database.

## How to run?

This project uses:

* Java 11
* Maven 3.x
* MySQL8

It's expected that database has the schema 'sw_api' already created.

To run locally, simply execute `mvn spring-boot:run`.

Alternatively, you can run using Docker, by invoking the docker-compose in the root of the project. Note that you must package the project before running docker-compose, thus make sure that the folder 'target' with resulting JAR file was generated successfully. Tip: use `-DskipTests` when packaging to get it faster.

The docker-compose file expects the following environment variables to be declared:

* SERVER_PORT
* DATABASE_PASSWORD

## Documentation

With your API running, you can access `http://<API_ADDRESS>/api/v1/swagger-ui.html` to get the documentation.
