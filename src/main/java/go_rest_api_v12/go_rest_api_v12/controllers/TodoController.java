package go_rest_api_v12.go_rest_api_v12.controllers;

import go_rest_api_v12.go_rest_api_v12.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final String TODO_URL = "https://gorest.co.in/public/v2/todos";
    private final String USER_URL = "https://gorest.co.in/public/v2/users";

    @Autowired
    private Environment environment;

    //O> Create
    @PostMapping("/user/{userId}")
    public ResponseEntity<TodoModel> createTodoByUserId(
            @PathVariable int userId,
            @RequestBody TodoModel newTodo,
            RestTemplate restTemplate
    ){
        try {

            String token = environment.getProperty ("GO_REST_API_KEY");

            String url = USER_URL + "/" + userId + "/todos";

            HttpHeaders headers = new HttpHeaders ();

            headers.setBearerAuth ( token );

            HttpEntity<TodoModel> request = new HttpEntity<> ( newTodo,headers );

            TodoModel response = restTemplate.postForObject ( url,request,TodoModel.class );

            return new ResponseEntity<TodoModel> (response,HttpStatus.OK  );


        }catch (HttpClientErrorException.Unauthorized exception){

            return new ResponseEntity ( "You Are Not Authorized To Create A Todo", HttpStatus.UNAUTHORIZED );

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity (e.getMessage (),HttpStatus.INTERNAL_SERVER_ERROR  );
        }
    }

    //O> Read

    @GetMapping
    public TodoModel[] getFirstPage (RestTemplate restTemplate){

        return restTemplate.getForObject (TODO_URL,TodoModel[].class );

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<TodoModel[]> getTodoByUser (@PathVariable int userId, RestTemplate restTemplate ){

        try {

            String url = USER_URL + "/" + userId + "/todos";

            return new ResponseEntity<TodoModel[]> (restTemplate.getForObject (url,TodoModel[].class  ),HttpStatus.OK );

        } catch (HttpClientErrorException.NotFound exception) {

            return new ResponseEntity(  "Todos by User #" + userId + " cannot be found",HttpStatus.NOT_FOUND);

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity (e.getMessage (),HttpStatus.INTERNAL_SERVER_ERROR  );
        }
    }

}
