package com.example.gitapi.service;

import com.example.gitapi.entity.Repo;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GitAPIService {
    public Flux<Repo> getRepositoriesByName(String user);
    Flux<Repo> getBranchesForRepo(String name);
}
