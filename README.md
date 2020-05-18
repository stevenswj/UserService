About the User Service
============================================

The User Service is a simple Java Spring Boot REST application for tracking users.

The application is designed to be very simple and light weight, runnable over a single jar. It utilizes a built-in embedded SQLite database for persistence of item information, storing data in-memory, which allows the project to be run without needing a separate database. The embedded Tomcat server is also built-in. Lastly, the application does not come packaged with a front end, so you may use your own if you wish. The application consumes and produces JSON over REST.


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

- ***GET /user/{userName}*** - Retrieves information about the user of the given username. Username must exist.

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

- ***POST /user/{userName}*** - Creates a user with the given username with the given information. Username must not already be in use. Name and email cannot be empty.

Example Request:

	curl --location --request POST 'http://localhost:8080/api/v1/user/bvila' \
	--header 'Content-Type: application/json' \
	--header 'Content-Type: text/plain' \
	--data-raw '{
	    "name": "Bob Vila",
	    "email": "bob.vila@gmail.com"
	}'
		
Example Response:

	{ "result": "User successfully created." }

- ***PUT /user/{userName}*** - Updates the user with the given username with the given information. User must exist. Name and email cannot be empty.

Example Request:

	curl --location --request PUT 'http://localhost:8080/api/v1/user/jdoe' \
	--header 'Content-Type: application/json' \
	--header 'Content-Type: text/plain' \
	--data-raw '{
	    "name": "Johnny Doe",
	    "email": "john.doe@hotmail.com"
	}'
		
Example Response:

	{ "result": "User successfully updated." }
	
- ***DELETE /user/{userName}*** - Deletes user with the given username. Username must exist.

Example Request:

	curl --location --request DELETE 'http://localhost:8080/api/v1/user/jdoe' \
	--header 'Content-Type: application/json'
		
Example Response:

	{ "result": "User successfully deleted." }

