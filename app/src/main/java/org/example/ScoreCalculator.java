package org.example;

import lombok.RequiredArgsConstructor;
import org.example.github.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreCalculator {

    private final DateScorer dateScorer;

    public Integer scoreRepository(Repository repository) {
        var score = repository.getStargazers_count() + repository.getForks_count();
        return score + dateScorer.scoreDate(repository.getPushed_at());
    }
}
