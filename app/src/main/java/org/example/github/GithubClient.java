package org.example.github;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class GithubClient {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final RestClient restClient;

    public GithubClient(@Value( "${org.example.github.server}" ) String githubServer) {
        restClient = RestClient.create(String.format("%s/search/repositories", githubServer));
    }

    public Set<Repository> getRepositories(RepositorySearchParameters repositorySearchParameters) {
        var createdAfter = this.format.format(repositorySearchParameters.getCreatedAfter());
        var language = repositorySearchParameters.getLanguage();
        log.info("Searching for github repositories created={}&language={}", createdAfter, language);

        var repositories = new HashSet<Repository>();
        var page = 0;
        var hasNext = true;
        while (hasNext) {
            page++;
            var result = restClient.get()
                    .uri("?page={page}&q='created:>{createdAfter} language:{language}'", page, createdAfter, language)
                    .retrieve()
                    .body(RepositoriesResult.class);
            repositories.addAll(result.getItems());
            hasNext = repositories.size() < result.getTotal_count();
        }
        log.info("Found {} repositories.", repositories.size());
        return repositories;
    }
}
