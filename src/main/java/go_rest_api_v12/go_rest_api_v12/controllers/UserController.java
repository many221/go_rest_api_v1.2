package go_rest_api_v12.go_rest_api_v12.controllers;

import go_rest_api_v12.go_rest_api_v12.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final String URL = "https://gorest.co.in/public/v2/users";

    @Autowired
    private Environment env;


    //X--> Get Requests

    @GetMapping("/{id}")
    public ResponseEntity< UserModel> getUserByID(@PathVariable int id, RestTemplate restTemplate){

        try {
            String userUrl = URL + "/" + id;

            return new ResponseEntity<> ( restTemplate.getForObject ( userUrl, UserModel.class ), HttpStatus.OK );

        } catch (Exception e){

            System.out.println (e.getClass ());
            System.out.println (e.getMessage ());

            return new ResponseEntity( e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllUser( RestTemplate restTemplate ){

        try {

            ArrayList<UserModel> allUserArray = new ArrayList<>();

            ResponseEntity<UserModel[]> response = restTemplate.getForEntity( URL, UserModel[].class );

            allUserArray.addAll ( Arrays.asList ( Objects.requireNonNull ( response.getBody () ) ) );

            int totalPageNumber = Integer.parseInt (
                    Objects.requireNonNull (
                            response.getHeaders ().get (
                                    "X-Pagination-Pages" ) ).get ( 0 ) );

            for (int i = 2; i <= 5; i++) {

                String tempURl = URL + "?page=" + i;

                UserModel[] pageData = restTemplate.getForObject ( tempURl,UserModel[].class );

                assert pageData != null;

                allUserArray.addAll ( Arrays.asList ( pageData));

            }

            System.out.println (allUserArray.size ());

            return new ResponseEntity ( allUserArray,HttpStatus.OK );

        } catch( Exception e ){

            System.out.println (e.getClass ());
            System.out.println (e.getMessage ());

            return new ResponseEntity( e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    //X--> Post Requests

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel newUser, RestTemplate restTemplate){

        try {

            String token = env.getProperty ( "GO_REST_API_KEY" );

            //X| Through URl

//            String postURl = URL + "?access-token=" + token;

            //X|

            //O| Through Headers

            HttpHeaders headers = new HttpHeaders ();

            headers.setBearerAuth ( token );

//            headers.set ( "Authorization", "Bearer " + token );

            //O|

            HttpEntity<UserModel> request = new HttpEntity<UserModel> ( newUser,headers );

            UserModel response = restTemplate.postForObject ( URL,request,UserModel.class );

            return new ResponseEntity<> (response,HttpStatus.CREATED );

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity( e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    //X--> Put Requests

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable int id,@RequestBody UserModel updateData,RestTemplate restTemplate){

        try {

            String token = env.getProperty ( "GO_REST_API_KEY" );

            HttpHeaders headers = new HttpHeaders ();

            headers.setBearerAuth ( token );


            String userUrl = URL + "/"+ id;

            HttpEntity<UserModel> request = new HttpEntity<> ( updateData,headers );

            ResponseEntity<UserModel> response =  restTemplate.exchange ( userUrl, HttpMethod.PUT,request,UserModel.class );

            return new ResponseEntity<>(response.getBody (),HttpStatus.OK);

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return new ResponseEntity( e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    //X--> Delete Requests

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable int id, RestTemplate restTemplate){

        try {

            String token = env.getProperty ( "GO_REST_API_KEY" );

            String postURl = URL + "?access-token=" + token;

            String deletedUser = postURl +"/"+id;

            restTemplate.delete ( deletedUser );

//            return restTemplate.delete ( deletedUser,UserModel.class );
            return HttpStatus.OK;

        } catch (Exception e){

            System.out.println (e.getClass ());

            System.out.println (e.getMessage ());

            return HttpStatus.INTERNAL_SERVER_ERROR;

        }

    }


}
