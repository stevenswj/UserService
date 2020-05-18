About the User Service
============================================

The User Service is a Java Spring Boot REST application for tracking users.

The application is designed to be very simple and light weight, runnable over a single jar. It utilizes a built-in embedded SQLite database for persistence of item information, storing data in-memory, which allows the project to be run without needing a separate database. The embedded Tomcat server is also built-in. Lastly, the application does not come packaged with a front end, so you may use your own if you wish. The application consumes and produces JSON over REST because this is easiest to read and process by front ends.


Getting Started
==================

The User Service runs on a jar called ***GildedRoseService-0.0.1-SNAPSHOT.jar***, which is in the repo. Simply run `java -jar GildedRoseService-0.0.1-SNAPSHOT.jar`. It should be available over the URL e.g. http://localhost:8080/api/v1/user/{userName}.

Maven Commands
--------------
In addition, the following maven commands may be run from the root directory of the project for building and testing. Before running these commands, it is recommended to change your $JAVA_HOME environment variable to point to a Java 1.8 JDK installation.

- Runs the application from the source

	`mvn spring-boot:run`
		
- Builds the executable jar
	
	`mvn clean package`

REST Endpoints
--------------

To test the endpoints, you might decide to use tools like cURL, Swagger, Postman, Fiddler, etc.

- ***GET /user/{userName}*** - Retrieves information about the user.

Example Request:
	
    curl --location --request GET 'http://localhost:8080/api/v1/user/jdoe' \
    --header 'Content-Type: application/json'
		
Example Response:

    {
        "result": {
            "userName": "jdoe",
            "name": "John Doe",
            "email": "john.doe@gmail.com"
        }
    }

- ***GET /item/{id}*** - Retrieves information about a specific item. Furthermore, the item is logged as viewed, which may potentially cause the price to surge 10%. Right now the IDs 1 or 2 are valid.

Example Request:

	curl -X GET \
	http://localhost:8080/item/1 \
	-H 'Accept: application/json' \
	-H 'Accept-Encoding: gzip, deflate' \
	-H 'Authorization: Basic dXNlcjE6dXNlcjFQYXNz' \
	-H 'Cache-Control: no-cache' \
	-H 'Connection: keep-alive' \
	-H 'Content-Type: application/json' \
	-H 'Cookie: JSESSIONID=553F2D8F091CD62C86226C8712AF97E2' \
	-H 'Host: localhost:8080' \
	-H 'Postman-Token: 424e7f4d-01bd-4e7f-af4d-48139202d248,20ab6150-15c9-41b7-839d-b1d32024c589' \
	-H 'User-Agent: PostmanRuntime/7.16.3' \
	-H 'cache-control: no-cache'
		
Example Response:

	{"id":1,"name":"Aged Brie","description":"Fancy lad stuff","price":10,"quantity":1}

- ***POST /item/{id}*** - Purchases the item. Right now all that happens is the quantity count gets decremented. If not sold out, returns true. Otherwise false (IE you may not purchase more).

Example Request:

	curl -X POST \
	http://localhost:8080/item/1 \
	-H 'Accept: application/json' \
	-H 'Authorization: Basic dXNlcjE6dXNlcjFQYXNz' \
	-H 'Content-Type: application/json' \
	-H 'Postman-Token: b5c65ed8-3f88-46fc-9f44-4efa1c4a4976' \
	-H 'cache-control: no-cache'
		
Example Response:

	{"result": true}
	
The only header you strictly need is the 'Authorization' header, and you can copy and past it wholesale since the username and 
password are constant.

