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
- Wildfly administration on the port `9990`

## Rest API
The API allowing users management is documented. Check the [API file](https://github.com/sebastienrichoz/AMT-App-01/blob/master/doc/API.md). It provide endpoints to create, find, update and delete users. You can also find a Postman collection to test see the API in action.

You can learn how to test the Rest API with [this documentation](https://github.com/sebastienrichoz/AMT-App-01/blob/master/doc/Postman.md).

This API is not secured with an authentication.

## Known bugs and improvements
- Issue for special characters when retrieving json data with the rest api.
- The postman tests script must be run on a fresh install of the app.
- Only the endpoint to get a specific user can be used with the id and username. Other actions will come in the futur.

## Authors
Damien Rochat & Sébastien Richoz (HEIG-VD 2016)
