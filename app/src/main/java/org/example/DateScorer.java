package org.example;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateScorer {

    public Integer scoreDate(Date pushedAt) {
        var aWeekAgo = new Date();
        var aMonthAgo = new Date();
        var aYearAgo = new Date();

        if (pushedAt.before(aYearAgo)) {
            return -10;
        } else if (pushedAt.after(aWeekAgo)) {
            return  10;
        } else if (pushedAt.after(aMonthAgo)) {
            return  2;
        }
        return 0;
    }
}
