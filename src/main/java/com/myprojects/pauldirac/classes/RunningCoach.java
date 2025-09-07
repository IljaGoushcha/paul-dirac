package com.myprojects.pauldirac.classes;

import com.myprojects.pauldirac.interfaces.Coach;

public class RunningCoach implements Coach  {
    @Override
    public String getDailyWorkout() {
        return "Run 10k.";
    }
}
