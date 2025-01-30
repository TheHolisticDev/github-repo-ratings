package org.example;

import lombok.RequiredArgsConstructor;
import org.example.util.ClockProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DateScorer {

    private final ClockProvider clockProvider;

    public Integer scoreDate(LocalDate pushedAt) {
        var now = LocalDate.now(clockProvider.getClock());
        var aWeekAgo = now.minusWeeks(1);
        var aMonthAgo = now.minusMonths(1);
        var aYearAgo = now.minusYears(1);

        if (pushedAt.isBefore(aYearAgo)) {
            return -10;
        } else if (pushedAt.isAfter(aWeekAgo)) {
            return  10;
        } else if (pushedAt.isAfter(aMonthAgo)) {
            return  2;
        }
        return 0;
    }
}
