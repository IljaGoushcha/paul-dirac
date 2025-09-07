package com.myprojects.pauldirac.classes;

import com.myprojects.pauldirac.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Dribble the ball like Ronaldo.";
    }

}
