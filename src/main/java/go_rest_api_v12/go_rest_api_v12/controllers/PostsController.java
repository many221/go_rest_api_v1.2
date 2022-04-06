package go_rest_api_v12.go_rest_api_v12.controllers;

import go_rest_api_v12.go_rest_api_v12.models.PostsModel;
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
@RequestMapping("/api/posts")
public class PostsController {

    private final String POST_URL = "https://gorest.co.in/public/v2/posts";
    private final String USER_URL = "https://gorest.co.in/public/v2/users";

    @Autowired
    private Environment environment;


    //O> Create
    @PostMapping("/user/{id}")
    public ResponseEntity<PostsModel> createPost(@PathVariable int id,@RequestBody PostsModel newPost, RestTemplate restTemplate){

        try{

            String token = environment.getProperty ("GO_REST_API_KEY");

            String url = USER_URL + "/" + id + "/posts";

            HttpHeaders headers = new HttpHeaders ();

            headers.setBearerAuth ( token );

            HttpEntity<PostsModel> request = new HttpEntity<> ( newPost, headers);

            PostsModel response = restTemplate.postForObject ( url,request,PostsModel.class );

            return new ResponseEntity (response,HttpStatus.OK  );

        } catch (HttpClientErrorException.Unauthorized exception){

            return new ResponseEntity ( "You Are Not Authorized To Create A Post", HttpStatus.UNAUTHORIZED );

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity (e.getMessage (),HttpStatus.INTERNAL_SERVER_ERROR  );
        }
    }

    //O> Read
    @GetMapping
    public PostsModel[] getFirstPage(RestTemplate restTemplate) {

            return restTemplate.getForObject ( POST_URL, PostsModel[].class );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<PostsModel[]> getPostsByUserId (@PathVariable int id, RestTemplate restTemplate){

        try {

            String url = USER_URL + "/" + id + "/posts";

            return new ResponseEntity<> ( restTemplate.getForObject ( url, PostsModel[].class ), HttpStatus.OK );

        } catch (HttpClientErrorException.NotFound exception) {

            return new ResponseEntity(  "Post #" + id + " does not exist",HttpStatus.NOT_FOUND);

        }catch (Exception exception){

            System.out.println (exception.getMessage ());

            System.out.println (exception.getClass ());

            return new ResponseEntity ( exception.getMessage (),HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }


}

