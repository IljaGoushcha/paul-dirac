package com.myprojects.pauldirac.classes;

import com.myprojects.pauldirac.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class RunningCoach implements Coach  {
    @Override
    public String getDailyWorkout() {
        return "Run 10k.";
    }
}
