package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.github.GithubClient;
import org.example.github.RepositorySearchParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;

@Service
@Slf4j
public class ScoringService {

    @Autowired
    GithubClient githubClient;

    @Autowired
    RepositoriesTransformer repositoriesTransformer;

    @Cacheable("repositories")
    public Map<String, Integer> getScoredRepositories(RepositorySearchParameters repositorySearchParameters) {
        log.info("Requesting scored github repositories with search parameters: {}", repositorySearchParameters);
        var repositories = githubClient.getRepositories(repositorySearchParameters);

        var scoredRepositories = repositoriesTransformer.scoreRepositories(repositories);
        log.info("Scored {} repositories: {}", scoredRepositories.size(), scoredRepositories);
        return scoredRepositories;
    }
}
