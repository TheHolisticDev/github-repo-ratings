package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.github.RepositorySearchParameters;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ScoringController {

    private final ScoringService scoringService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/scored-repos")
    public ScoredRepositoriesResult scoredRepos(RepositorySearchParameters repositorySearchParameters) {
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
