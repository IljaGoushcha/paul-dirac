package com.myprojects.pauldirac.classes;

import com.myprojects.pauldirac.interfaces.Coach;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
//@Lazy
public class BasketballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice to improve three-point shooting.";
    }


}
