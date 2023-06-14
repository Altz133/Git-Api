package com.example.gitapi.rest;


import com.example.gitapi.entity.Branch;
import com.example.gitapi.entity.Repo;
import com.example.gitapi.service.GitAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


@RestController
@RequestMapping("/api")
public class GitAPIController {

    private GitAPIService gitAPIService;

    @Autowired
    public GitAPIController(GitAPIService gitAPIService) {
        this.gitAPIService = gitAPIService;
    }

    @GetMapping(value = "/{username}")
    public Flux<Repo> getReposVar(@PathVariable("username") String user){
            return gitAPIService.getBranchesForRepo(user);

    }




}
