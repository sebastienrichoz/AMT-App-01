# HEIG-VD AMT-App-01 (Bootcamp)

A simple demo application using JavaEE platform, Wildfly server, and docker-compose.

Provide registration, login and admin pages to visualize registered users and a REST API to manage them.

## How to build the app (optional, the app is already built just for you)
*You must have Maven on your machine*
- Clone this repo
- Run the following command `mvn clean install`
- The generated `war` file is located in the `images/wildfly/data` folder 

## How to run the app
*You must have Docker running on your machine*
- Clone this repo
- Open your terminal and browse at the root of the folder (same level as the ´docker-compose.yml´ file)
- Run the following command `docker-compose up`
- Open your browser and access this url `http://127.0.0.1:8080/AMT-App-01/` or `http://192.168.99.100:8080/AMT-App-01/` depending your installation (docker-machine or docker for Windows/Mac)
- Magic happens !

## How to test the app
TODO
