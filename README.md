# HEIG-VD AMT-App-01 (Bootcamp)

A simple demo application using JavaEE platform, Wildfly server, MySQL server and docker-compose.

Provide registration, login and admin pages to visualize registered users and a REST API to manage them.

## How to

### How to build the app (optional, the app is already built just for you)
*You must have Maven installed on your machine*
- Clone this repo
- Run the following command `mvn clean install`
- The generated `war` file is located in the `images/wildfly/data` folder 

### How to run the app
*You must have Docker running on your machine*
- Clone this repo
- Open your terminal and browse at the root of the folder (same level as the ´docker-compose.yml´ file)
- Run the following command `docker-compose up`
- Open your browser and access this url `http://127.0.0.1:8080/AMT-App-01/` or `http://192.168.99.100:8080/AMT-App-01/` depending your installation (docker-machine or docker for Windows/Mac)
- Magic happens !

### Other access
After you've got launched the application with docker-compose, you can access :
- PHPMyAdmin on the port `6060`
- MySQL on the port `3306`
- Wildfly administration `9990`

## Rest API
The API allowing users management is documented here. It provide endpoints to create, find, update and delete users. You can also find a Postman collection to test see the API in action.

This API is not secured with an authentication.

## Authors
Damien Rochat & Sébastien Richoz (HEIG-VD 2016)
