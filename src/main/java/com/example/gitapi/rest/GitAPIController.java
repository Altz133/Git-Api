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

    @GetMapping(value = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repo> getReposVar(@PathVariable("username") String user, @RequestHeader(HttpHeaders.ACCEPT) List<MediaType> header){
        return gitAPIService.getRepositories(user, header);
    }




}
