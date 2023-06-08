package com.example.gitapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
/*
branchName- contains name of the branch
lastCommitSha - contains last commit SHA of current branch
 */




@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Branch(String name, Commit commit) {
    public Branch {
    }
}
