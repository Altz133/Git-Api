package com.example.gitapi.entity;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
/*
repositoryName - contains name of repository
ownerLogin - contains GitHUb username of the repository owner
isFork - tells about if current repository is a fork or not
branchList - contains information about every branch in repository

 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"repositoryName","ownerLogin", "branchList"})
public class Repo {

    @JsonProperty("name")
    private String repositoryName;

    private String ownerLogin;

    @JsonProperty("owner")
    private void unpackNameFromNestedObject(Map<String, String> owner){
        ownerLogin=owner.get("login");
    }
    @JsonProperty(value = "fork",access = JsonProperty.Access.WRITE_ONLY)
    private boolean isFork;

    private List<Branch> branchList;


    public boolean getIsFork() {
        return isFork;
    }

    public void setIsFork(boolean isFork) {
        this.isFork = isFork;
    }

    public String getRepositoryName() {
        return repositoryName;
    }
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }
    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "repositoryName='" + repositoryName + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", branchList=" + branchList +
                '}';
    }
}
