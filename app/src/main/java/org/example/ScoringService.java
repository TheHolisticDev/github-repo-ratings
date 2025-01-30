package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.github.GithubClient;
import org.example.github.RepositorySearchParameters;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoringService {

    private final GithubClient githubClient;

    private final RepositoriesTransformer repositoriesTransformer;

    @Cacheable("repositories")
    public ScoredRepositoriesResult getScoredRepositories(RepositorySearchParameters repositorySearchParameters) {
        log.info("Requesting scored github repositories with search parameters: {}", repositorySearchParameters);
        var repositoriesResult = githubClient.getRepositories(repositorySearchParameters);

        var scoredRepositories = repositoriesTransformer.scoreRepositories(repositoriesResult.getItems());
        log.info("Scored {} repositories: {}", scoredRepositories.size(), scoredRepositories);
        return ScoredRepositoriesResult
                .builder()
                .page(repositorySearchParameters.getPage())
                .scoredRepositories(scoredRepositories)
                .repositoriesCount(repositoriesResult.getTotal_count())
                .build();
    }
}
