package com.example.gitapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Branch {

    @JsonProperty("name")
    private String branchName;

    private String lastCommitSha;

    @JsonProperty("commit")
    private void unpackShaFroCommit(Map<String, String> commit){
        lastCommitSha=commit.get("sha");
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchName='" + branchName + '\'' +
                ", lastCommitSha='" + lastCommitSha + '\'' +
                '}';
    }
}
