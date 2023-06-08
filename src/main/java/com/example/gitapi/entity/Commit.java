package com.example.gitapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Commit(String sha) {
    public Commit {
    }
}
