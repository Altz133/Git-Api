package com.example.gitapi.service;

import com.example.gitapi.entity.Repo;
import org.springframework.http.MediaType;

import java.util.List;

public interface GitAPIService {
    public List<Repo> getRepositories(String user, List<MediaType> header);

}
