package org.example;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ScoredRepositoriesResult {
    public Integer repositoriesCount;
    public Integer page;
    public Map<String, Integer> scoredRepositories;
}
