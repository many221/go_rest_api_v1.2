package go_rest_api_v12.go_rest_api_v12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*

	Create a controller and model for each of the following resources
posts - https://gorest.co.in/public/v2/posts
comments -  https://gorest.co.in/public/v2/comments
todos - https://gorest.co.in/public/v2/todos


Your controllers should be capable of doing the following

GET one resource by id (using PathVariable)
DELETE one resource (using PathVariable)

POST one resource (using RequestBody)
PUT one resource (using RequestBody)

BONUS:
GET all resources in one request
POST multiple resources at once (using an array of objects passed through the RequestBody)
	*/

}
