package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.factories.CoachFactory;
import com.myprojects.pauldirac.interfaces.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DailyPracticeController {

    private final CoachFactory coachFactory;
    private final Coach runningCoach;

    @Autowired
    public DailyPracticeController(CoachFactory coachFactory, @Qualifier("runningCoach") Coach theCoach) {
        this.coachFactory = coachFactory;
        this.runningCoach = theCoach;
    }

    @GetMapping("/get-daily-workout")
    public String getWorkout() {
       Coach soccerCoach = coachFactory.getMyCoach("basketball");
        return soccerCoach.getDailyWorkout();
    }

    @GetMapping("/get-daily-running-workout")
    public String sayRunningWorkout() {
        return runningCoach.getDailyWorkout();
    }
}
