package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.github.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepositoriesTransformer {

    private final ScoreCalculator scoreCalculator;

    public Map<String, Integer> scoreRepositories(List<Repository> repositories) {
        var scoredRepositories = new HashMap<String, Integer>();
        log.info("Scoring repositories...");
        repositories
            .stream()
            .forEach(
                repository ->
                    scoredRepositories.put(
                        repository.getHtml_url(),
                        scoreCalculator.scoreRepository((repository))
                    )
            );
        log.info("...scored {} repositories", scoredRepositories.size());
        return scoredRepositories;
    }
}
