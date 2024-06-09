# Pathfinder

## Overview

This Spring Boot service calculates the shortest land route from one country to another using border information from a provided [list of countries](https://raw.githubusercontent.com/mledoze/countries/master/countries.json) in JSON format.

## Specifications

- Framework: Spring Boot, Maven
- Country Data Source: [countries.json](src/main/resources/data/countries.json)
- Algorithm: Utilizes an undirected graph without weights and finds the shortest path using [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm). Real distances are not considered. Efficiency is based solely on the number of border crossings.
- Endpoint: `GET /routing/{origin}/{destination}`
- Path Parameters: `origin`, `destination` - the three-letter country codes ([ISO 3166-1 alpha-3](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3))
- Functionality: Returns a list of border crossings to get from origin to destination e.g. `{"route":["CZE","AUT","ITA"]}`
- Error Handling: Returns HTTP 400 if no land crossing is possible

## Usage

### Building and Running the Application

**Using Docker:**
```shell
docker-compose up --build -d
```

**Using Maven:**
```shell
./mvnw spring-boot:run
```

### Accessing the API

**Using cURL (e.g. from South Africa to China):**
```shell
curl -X GET -i "http://localhost:8080/routing/ZAF/CHN"
```

## Coverage

- All services are covered by unit tests
- Additionally, an [e2e test](src/test/java/engineer/dima/pathfinder/route/RouteControllerE2ETest.java) is performed on the `/routing/{origin}/{destination}` endpoint