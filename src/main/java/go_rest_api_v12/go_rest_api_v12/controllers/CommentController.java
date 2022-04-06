package go_rest_api_v12.go_rest_api_v12.controllers;

import go_rest_api_v12.go_rest_api_v12.models.CommentModel;
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
@RequestMapping("/api/comments")
public class CommentController {

    private final String COMMMENT_URL = "https://gorest.co.in/public/v2/comments";
    private final String POST_URL = "https://gorest.co.in/public/v2/posts";

    @Autowired
    private Environment environment;


    //O> CREATE
    @PostMapping("/post/{postID}")
    public ResponseEntity<CommentModel> createCommentOnPost(
            @PathVariable int postID,
            @RequestBody CommentModel newComment,
            RestTemplate restTemplate
            ){

        try {

            String token = environment.getProperty ("GO_REST_API_KEY");

            String url = POST_URL + "/" + postID + "/comments";

            HttpHeaders headers = new HttpHeaders ();

            headers.setBearerAuth ( token );

            HttpEntity<CommentModel> request = new HttpEntity<> ( newComment, headers);

            CommentModel response = restTemplate.postForObject ( url,request, CommentModel.class );

            return new ResponseEntity (response,HttpStatus.OK  );


        } catch (HttpClientErrorException.Unauthorized exception){

            return new ResponseEntity ( "You Are Not Authorized To Create A Comment", HttpStatus.UNAUTHORIZED );

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity (e.getMessage (),HttpStatus.INTERNAL_SERVER_ERROR  );
        }

    }

    //O> READ

    @GetMapping
    public CommentModel[] getFirstPage(RestTemplate restTemplate){

        return restTemplate.getForObject ( COMMMENT_URL,CommentModel[].class );

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<CommentModel[]> getCommentsByPostId(@PathVariable int postId, RestTemplate restTemplate){

        try {

            String url = POST_URL + "/" + postId + "/comments";

            return new ResponseEntity<CommentModel[]> (restTemplate.getForObject ( url,CommentModel[].class ),HttpStatus.OK  );

        } catch (HttpClientErrorException.NotFound exception) {

            return new ResponseEntity(  "Comments on Post #" + postId + " cannot be found",HttpStatus.NOT_FOUND);

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity (e.getMessage (),HttpStatus.INTERNAL_SERVER_ERROR  );
        }
    }


}
