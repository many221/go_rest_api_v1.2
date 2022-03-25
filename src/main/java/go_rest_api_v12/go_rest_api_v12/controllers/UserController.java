package go_rest_api_v12.go_rest_api_v12.controllers;

import go_rest_api_v12.go_rest_api_v12.models.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/all")
    public ResponseEntity getAllUser( RestTemplate restTemplate ){


        try {

            String url = "https://gorest.co.in/public/v2/users";

            ArrayList<UserModel> allUserArray = new ArrayList<>();

            ResponseEntity<UserModel[]> response = restTemplate.getForEntity( url, UserModel[].class );

            allUserArray.addAll ( Arrays.asList ( Objects.requireNonNull ( response.getBody () ) ) );

            int totalPageNumber = Integer.parseInt (
                    Objects.requireNonNull (
                            response.getHeaders ().get (
                                    "X-Pagination-Pages" ) ).get ( 0 ) );

            for (int i = 2; i < totalPageNumber; i++) {

                String tempURl = url + "?page=" + i;

                UserModel[] pageData = restTemplate.getForObject ( tempURl,UserModel[].class );

                assert pageData != null;
                allUserArray.addAll ( Arrays.asList ( pageData));

            }

            return new ResponseEntity ( allUserArray,HttpStatus.OK );

        } catch( Exception e ){

            System.out.println (e.getClass ()) ;

            return new ResponseEntity( e.getMessage (), HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

}
