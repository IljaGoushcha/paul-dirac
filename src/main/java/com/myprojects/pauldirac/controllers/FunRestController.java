package com.myprojects.pauldirac.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${coach.name}")
    String coachName;

    @Value("${team.name}")
    String teamName;

    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run hard 5k!!!";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }

    @GetMapping("/team-info")
    public String getTeamInfo() {
        return "Coach: " + coachName + ", Team name: " + teamName;
    }
}
