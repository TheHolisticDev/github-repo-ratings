package org.example.github;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Repository {
    private LocalDate pushed_at;
    private Integer stargazers_count;
    private Integer forks_count;
    private String html_url;
}
