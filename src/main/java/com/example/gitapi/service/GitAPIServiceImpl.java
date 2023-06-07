package com.example.gitapi.service;

import com.example.gitapi.entity.Branch;
import com.example.gitapi.entity.Repo;
import com.example.gitapi.rest.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class GitAPIServiceImpl implements GitAPIService{

    private String authToken;
    private final WebClient webClient;

    @Autowired
    public GitAPIServiceImpl(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.github.com/")
                .build();
    }

    public List<Repo> getRepositories(String user, List<MediaType> header){

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


        return null;
    }
    public Mono<Repo> getRepositoriesByName(String name){
        Map<String,String> requestParameters = new HashMap<>();
        requestParameters.put("name", name);
        return webClient.get().uri("/users/{name}", name)
                .retrieve()
                .bodyToMono(Repo.class);
    }

}
