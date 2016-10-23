# REST API

The Rest API of this project allows you to manage the users.

Note : the API is not secured.

### Get a single user

```
GET /api/users/{id}
```

#### Response

Status: 200 Ok
```
Content-type: application/json
[
  {
    "id": 1,
    "firstname": "Sébastien",
    "lastname": "Richoz",
    "email": "sebastien@courgettes.com",
    "username": "sebastien",
    "uri": "http://192.168.99.100:8080/AMT-App-01/api/users/sebastien"
  }
]
```

#### Other response

If the user is not found, the response code will be **HTTP 404**.

#### Alternative

The users can also be retrieved by username, simply replace the id by a string representing the username of the user.

### Retrieve the list off all users

```
GET /api/users
```

#### Response

Status: 202 Ok
```
Content-type: application/json
[
  {
    "id": 1,
    "firstname": "Sébastien",
    "lastname": "Richoz",
    "email": "sebastien@courgettes.com",
    "username": "sebastien",
    "uri": "http://192.168.99.100:8080/AMT-App-01/api/users/sebastien"
  },
  {
    "id": 2,
    "firstname": "Damien",
    "lastname": "Rochat",
    "email": "damien@courgettes.com",
    "username": "damien",
    "uri": "http://192.168.99.100:8080/AMT-App-01/api/users/damien"
  }
]
```

### Create a new user

```
POST /api/users
```

#### Parameters

| Name         | Type   | Required | Description                 |
|--------------|--------|----------|-----------------------------|
| firstname    | string | no       | The first name of the user  |
| lastname     | string | no       | The last name of the user   |
| email        | string | no       | The email of the user       |
| username     | string | yes      | The username of the user    |
| password     | string | yes      | The password of the user    |
| passwordCtrl | string | yes      | The control of the password |

Note : the password and the passwordCtrl must be the same.

#### Example

```
POST /api/users
Content-type: application/json
{
	"firstname" : "Anakin",
	"lastname" : "Skywalker",
	"email" : "anakin@force.com",
	"username" : "anakin",
	"password" : "anakin",
	"passwordCtrl" : "anakin"
}
```

#### Response

Status: 201 Created

#### Other response

If the fields are not valid, the response status code will be **HTTP 422** and **HTTP 409** if some fields create a conflict with and existing user. Check the reponse content to know more.

### Update an existing user

```
PUT /api/users/{id}
```

#### Parameters

| Name         | Type   | Required | Description                 |
|--------------|--------|----------|-----------------------------|
| firstname    | string | no       | The first name of the user  |
| lastname     | string | no       | The last name of the user   |
| email        | string | no       | The email of the user       |
| username     | string | yes      | The username of the user    |
| password     | string | yes      | The password of the user    |
| passwordCtrl | string | yes      | The control of the password |

Note : the password and the passwordCtrl must be the same.

#### Example

```
PUT /api/users/1
Content-type: application/json
{
	"firstname" : "Darth Vader",
	"lastname" : "",
	"email" : "vador@empire.com",
	"username" : "vador",
	"password" : "vador",
	"passwordCtrl" : "vador"
}
```

#### Response

Status: 200 Ok

#### Other response

If the user is not found, the response code will be **HTTP 404**.

Otherwise, if the fields are not valid, the response status code will be **HTTP 422** and **HTTP 409** if some fields create a conflict with and existing user. Check the reponse content to know more.

### Deleting an user

```
DELETE /api/users/{id}
```

#### Response

Status: 204 No content

#### Other response

If the user is not found, the response code will be **HTTP 404**.
