package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.classes.BasketballCoach;
import com.myprojects.pauldirac.classes.RunningCoach;
import com.myprojects.pauldirac.classes.SoccerCoach;
import com.myprojects.pauldirac.interfaces.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DailyPracticeController {

    private SoccerCoach mySoccerCoach;
    private BasketballCoach myBasketballCoach;
    private RunningCoach myRunningCoach;

    @Autowired
    public DailyPracticeController(
            SoccerCoach mySoccerCoach,
            BasketballCoach myBasketballCoach
    ) {
        this.mySoccerCoach = mySoccerCoach;
        this.myBasketballCoach = myBasketballCoach;
    }


    @GetMapping("/get-daily-workout")
    public String sayHello() {
        return "Hello from get daily workout.";
    }
}
