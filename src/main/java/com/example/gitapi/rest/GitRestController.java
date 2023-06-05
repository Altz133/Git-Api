package com.example.gitapi.rest;


import com.example.gitapi.entity.Branch;
import com.example.gitapi.entity.Repo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class GitRestController {
    private String authToken;

    @GetMapping(value = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repo> getReposVar(@PathVariable("username") String user, @RequestHeader(HttpHeaders.ACCEPT) List<MediaType> header){
        List<Repo> repos;
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(header);

        //authentication
        //Put OAuth GitHub access token inside token.txt file in root directory
        File file = new File("token.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            this.authToken = sc.next();
        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }

        headers.setBearerAuth(authToken);

        HttpEntity request = new HttpEntity(headers);

        repos =  getCall(user, request, header);

        List<Branch> branches;

        //responsible for removing forks form the list of repositories
        for (int i = 0; i < (repos != null ? repos.size() : 0); i++) {
            if (repos.get(i).getIsFork()) {
                repos.remove(i);
            }
        }

        //gets list of branches names and last commit SHA for every repository
        for (int i = 0; i < (repos != null ? repos.size() : 0); i++) {
            branches = getBranches(repos.get(i));
            repos.get(i).setBranchList(branches);
        }
        return repos;
    }

    private List<Repo> getCall(String user, HttpEntity request, List<MediaType> header) {
        List<Repo> temp = new ArrayList<Repo>();
        String url = "https://api.github.com/users/" + user + "/repos";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Repo>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<List<Repo>>() {
                    }
            );
            List<Repo> repo = response.getBody();
            return repo;

        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();
            if (statusCode == 404) {
                throw new UserNotFound("Username " + user + " not found on github");
            }
        }
        return temp;
    }

    private List<Branch> getBranches(Repo element) {
        String url = "https://api.github.com/repos/" + element.getOwnerLogin() + "/" + element.getRepositoryName() + "/branches";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        headers.setBearerAuth(authToken);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<List<Branch>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<Branch>>() {
                });

        return response.getBody();
    }



}
