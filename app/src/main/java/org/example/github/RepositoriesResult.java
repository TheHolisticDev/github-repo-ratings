package org.example.github;

import lombok.Data;

import java.util.List;

@Data
public class RepositoriesResult {
    private Integer total_count;
    private List<Repository> items;
}
