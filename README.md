# quarkus-user-service project





## Contents at a Glance.
* [About.](#about)
* [Documentation.](#documentation)
* [Creation of the Docker for the database, for the development mode.](#creation-of-the-docker-for-the-database-for-the-development-mode)
* [Creation of the Docker for the database, for the testing mode.](#creation-of-the-docker-for-the-database-for-the-testing-mode)
* [Creation of the Docker for the database, for the production mode.](#creation-of-the-docker-for-the-database-for-the-production-mode)
* [Help.](#help)





## About.





## Documentation.





## Creation of the Docker for the database, for the development mode.
`docker run --rm -d --name postgres-dev -p 5431:5432 -e POSTGRES_PASSWORD=strong_password -e POSTGRES_USER=developer -e POSTGRES_DB=quarkus-user-service-db-dev postgres`





## Creation of the Docker for the database, for the testing mode.
`docker run --rm -d --name postgres-test -p 5433:5432 -e POSTGRES_PASSWORD=strong_password -e POSTGRES_USER=developer -e POSTGRES_DB=quarkus-user-service-db-test postgres`





## Creation of the Docker for the database, for the production mode.
`docker run --rm -d --name postgres-prod -p 5432:5432 -e POSTGRES_PASSWORD=strong_password -e POSTGRES_USER=developer -e POSTGRES_DB=quarkus-user-service-db-prod postgres`





## CENTRALIZED LOG MANAGEMENT.
* `docker-compose -f /home/trl/IdeaProjects/quarkus-user-service/src/main/docker/docker-compose.yaml up -d`
* `docker-compose -f /home/trl/IdeaProjects/quarkus-user-service/src/main/docker/docker-compose.yaml down`



## Help.
