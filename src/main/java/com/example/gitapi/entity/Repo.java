package com.example.gitapi.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.List;

/*
repositoryName - contains name of repository
ownerLogin - contains GitHUb username of the repository Owner
isFork - tells about if current repository is a fork or not
branchList - contains information about every branch in repository

 *
//@JsonPropertyOrder({"repositoryName","ownerLogin", "branchList"})

 */

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public record Repo(String name, Owner owner, boolean fork, List<Branch> branch) {

    public Repo {
    }

}




