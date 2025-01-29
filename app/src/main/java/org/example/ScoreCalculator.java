package org.example;

import org.example.github.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScoreCalculator {

    @Autowired
    DateScorer dateScorer;

    public Integer scoreRepository(Repository repository) {
        var score = repository.getStargazers_count() + repository.getForks_count();
        var pushedAt = new Date();
        repository.getPushed_at();
        return score + dateScorer.scoreDate(pushedAt);
    }
}
