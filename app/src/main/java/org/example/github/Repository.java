package org.example.github;

import lombok.Data;

@Data
public class Repository {
    private String pushed_at;
    private Integer stargazers_count;
    private Integer forks_count;
    private String html_url;
}
