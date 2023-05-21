# Places Management

A project I created for studying Spring Boot and other technologies. It is a RESTful API based on a backend challenge available on `https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer`.
My goal was to learn about Spring Security using JSON Web Tokens, and perfecting my usage of unit and integration tests.

## Setup

Required MySQL Server 8.0.31 or above installed. Create a schema named "places_db".
After this, you will need to add your root MySQL username and password to the application.properties file, in `src/main/resources`. Insert it on the following lines:

```properties
spring.datasource.username=root
spring.datasource.password=root
```
Further instructions being made.

## Technologies

* **Java 17**
* **Spring Boot 2.7.4**
* **Spring Security**
* **JSON Web Tokens (JWT)**
* **JUnit 5**
* **Model Mapper 3.0.0**
* **MySQL**
* **Swagger 3.0.0**
* **Maven**

## Features

Endpoints provided by this API:

	* 'POST' request - Registering an user: `api/users`
	* 'GET' request - Getting all users: `api/users`
	* 'POST' request - Authenticating an user: `api/auth`
	* 'POST' request - Creating a place: `api/places`
	* 'PUT' request - Updating a place by id: `api/places/1`
	* 'GET' request - Getting an unique place by id: `api/places/1`
	* 'GET' request - Getting an unique place by name: `/api/places?name=foo`
	* 'GET' request - Getting all places: `api/places`

### Details

	`POST/api/users`
	
**Body:**

```json
{
  "name":"User 1",
  "email":"test@email.com",
  "password":"anyPassword",
  "role":"ROLE_USER"
}
```

Returns the data on the response body:

```json
{
  "data": {
    "id": 1,
    "name":"User 1",
    "password":"encryptedPassword",
    "email":"test@email.com",    
    "role":"ROLE_USER",
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/api/users/1"
        }
    ]
  }
}
```

`id` - User id automatically generated.
`name` - Name selected by the user.
`password` - The saved encrypted password.
`email` - The user email, used as username when authenticating.
`role` - The selected user role. Can be "ROLE_USER" or "ROLE_ADMIN".
`links` - An URL link automatically generated of the self entity.

	`POST/api/auth`

**Body**

```json
{
  "email":"myEmail@email.com",
  "password":"myPassword"
}
```

Returns the authentication token when user is validated.

```json
{
  "data":{
    "token":"generatedBearerToken"
  }
}
```

	`POST/api/places`

**Body**

```json
{
  "name":"Name of a Bar",
  "slug":"Nice Bar",
  "city":"Great City",
  "state":"Old State"
}
```

Returns the created place on the response body.

```json
{
  "data": {
    "id": 1,
    "name": "Name of a Bar",
    "slug": "Nice Bar",
    "city": "Great City",
    "state": "Old State",
    "createdAt": "2023-02-26T11:15:49.7246388",
    "updatedAt": null,
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/api/places/1"
        }
    ]
  }
}
```

`id` - Place id automatically generated.
`name` - Name of the place.
`slug` - Slug giver for the place.
`city` - City where the place is.
`state` - State where the place is.
`createdAt` - Date time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.ssssss in the Local time zone when the place was created.
`updatedAt` - Date time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.ssssss in the Local time zone for when the place is updated.
`links` - An URL link automatically generated of the self entity.

*Responses*

* 200 Ok - The request has succeeded.
* 201 Created - Successfully created the resource and returns it on the response body.
* 400 Bad Request - Request unacceptable due to wrong/missing parameter/data.
* 403 Forbidden - Missing valid token for authorization.
* 404 Not Found - When the requested resource is not found.

## Documentation

* Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Acknowledgements

I took inspiration and learned from these repos. I am greatly thankful for the lessons and the examples provided by them.

* [https://github.com/mariazevedo88/travels-java-api](https://github.com/mariazevedo88/travels-java-api)
* [https://github.com/ali-bouali/spring-boot-3-jwt-security](https://github.com/ali-bouali/spring-boot-3-jwt-security)

### License

This API is licensed under the MIT License.
