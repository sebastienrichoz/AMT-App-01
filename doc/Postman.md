# Postman collection

You would find in the test folder a file `postman-collection.json` containing a collection (v2) of HTTP request to show the API in action.

You need to have Postman installed. If not, follow [this link](https://www.getpostman.com/).

## Configuration

You can import the script or use the button bellow.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/3e1c0989246d0cb14a05)

You also need to set a new environment in Postman with the environment variable **SERVER_URL** corresponding to your installation : `127.0.0.1:8080` or `192.168.99.100:8080`.

## Testing

The collection give you 18 HTTP correct and wrong requests testing all the API endpoints. You can run test like you want or the whole testing script.

We add some assertions to test the content and the status code of the requests. So to obtain the best result, run the whole script with the **RUN** button of the collection.

The tests are not optimal, so to make sur the results are correct, run the tests on a fresh install of the app. We didn't found how to make conditional tests in Postman.

[More about Postman](https://www.getpostman.com/docs/) .