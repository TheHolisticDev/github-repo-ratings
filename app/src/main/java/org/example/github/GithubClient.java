package org.example.github;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.text.SimpleDateFormat;

@Service
@Slf4j
public class GithubClient {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final RestClient restClient;

    public GithubClient(@Value( "${org.example.github.server}" ) String githubServer) {
        restClient = RestClient.create(String.format("%s/search/repositories", githubServer));
    }

    public RepositoriesResult getRepositories(RepositorySearchParameters repositorySearchParameters) {
        var createdAfter = this.format.format(repositorySearchParameters.getCreatedAfter());
        var language = repositorySearchParameters.getLanguage();
        var page = repositorySearchParameters.getPage();
        log.info("Searching for github repositories created={}&language={} and returning page {}", createdAfter, language, page);

        var result = restClient.get()
                .uri("?page={page}&q=created:>{createdAfter}+language:{language}", page, createdAfter, language)
                .retrieve()
                .body(RepositoriesResult.class);
        log.info("Found {} repositories.", result.getTotal_count());
        return result;
    }
}
