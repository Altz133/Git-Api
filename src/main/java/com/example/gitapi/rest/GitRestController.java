package com.example.gitapi.rest;


import com.example.gitapi.entity.Branch;
import com.example.gitapi.entity.Repo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@RestController
@RequestMapping("/api")
public class GitRestController {
    @GetMapping("/{username}")
    public ResponseEntity<List<Repo>> getReposVar(@PathVariable("username") String user, @RequestHeader(value = "Accept", required = true) String header) throws JsonProcessingException {
        String url = "https://api.github.com/users/" + user + "/repos";
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); //Accept:application.Json

        HttpEntity request = new HttpEntity(headers);

        Map<String,String> params = new HashMap<String, String>();
        params.put("type","public");


        ResponseEntity<List<Repo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<Repo>>() {
                },
                params
        );
        List<Repo> repos = response.getBody();

        List<Branch> branches;


        for(int i = 0; i< (repos != null ? repos.size() : 0); i++){
            if(repos.get(i).getIsFork().equals("true") ) {
                repos.remove(i);
            }

        }
        for(int i = 0; i< (repos != null ? repos.size() : 0);i++){
            System.out.println(repos.get(i));
            branches = getBranches(repos.get(i));
            repos.get(i).setBranchList(branches);
        }



        if(response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
        }
        else{
            System.out.println("Request Failed");
        }

    return response;
    }

    private List<Branch> getBranches(Repo element){
        String url = "https://api.github.com/repos/" + element.getOwnerLogin() + "/"+ element.getRepositoryName() + "/branches";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<List<Branch>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<Branch>>() {
                });

        List<Branch> branches = response.getBody();
        for(int i = 0; i< branches.size(); i++){
            System.out.println(branches.get(i));
        }
        return branches;
    }

}
