package com.example.gitapi.service;
import com.example.gitapi.entity.Branch;
import com.example.gitapi.entity.Repo;
import com.example.gitapi.rest.EntityNotFoundException;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class GitAPIServiceImpl implements GitAPIService{

    private String authToken;
    private WebClient webClient;
    Logger log = LoggerFactory.getLogger(GitAPIServiceImpl.class);
    private HttpStatus status;

    @Autowired
    public GitAPIServiceImpl(WebClient.Builder builder) {



        this.webClient = builder
                .baseUrl("https://api.github.com/")
                .defaultHeader("Authorization","Bearer" +authToken)
                .defaultHeader(HttpHeaders.ACCEPT,"application/json")
                .build();
        this.authToken = getAuthToken();
    }

    public String getAuthToken(){
        File file = new File("token.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            sc.useDelimiter("\\Z");
        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }
        return authToken;
    }

    public Flux<Repo> getRepositoriesByName(String name) throws EntityNotFoundException {
        return this.webClient.get().uri("/users/{name}/repos", name)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(EntityNotFoundException::new))
                .bodyToFlux(Repo.class);
    }
    public Mono<List<Branch>> getBranches(String name, String repo){
        ParameterizedTypeReference<List<Branch>> listParameterizedTypeReference = new ParameterizedTypeReference<List<Branch>>() {
        };
        return webClient.get()
                .uri("/repos/{name}/{repo}/branches", name,repo)
                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.empty())
                .bodyToMono(listParameterizedTypeReference);
    }
    public Flux<Repo> getBranchesForRepo(String name){
        return getRepositoriesByName(name).filter(f->!f.fork())
                .flatMap(repo -> Flux
                .zip(Flux.just(repo), getBranches(repo.owner()
                        .login(), repo.name())
                        .defaultIfEmpty(new ArrayList<Branch>()))
                .map(tuple -> {
                    log.info("number of branches: " + tuple.getT2().size());
                    return Repo.builder().name(tuple.getT1().name())
                            .owner(tuple.getT1().owner())
                            .fork(tuple.getT1().fork())
                            .branch(tuple.getT2()).build();
                }));
    }


}
