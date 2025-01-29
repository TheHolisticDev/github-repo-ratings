package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.github.RepositorySearchParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.UncheckedIOException;
import java.util.Map;
import java.util.SortedMap;

@RestController
@Slf4j
public class ScoringController {

    @Autowired
    ScoringService scoringService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/scored-repos")
    public Map<String, Integer> scoredRepos(RepositorySearchParameters repositorySearchParameters) {
        log.info("Received call to /scored-repos with search parameters {}", repositorySearchParameters);

        try {
            return scoringService.getScoredRepositories(repositorySearchParameters);
        } catch (Exception ex) {
            log.error("Something went wrong scoring the repositories", ex);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong retrieving scored repositories",
                    ex);
        }
    }
}
