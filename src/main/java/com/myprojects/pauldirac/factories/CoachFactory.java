package com.myprojects.pauldirac.factories;

import com.myprojects.pauldirac.classes.BasketballCoach;
import com.myprojects.pauldirac.classes.RunningCoach;
import com.myprojects.pauldirac.classes.SoccerCoach;
import com.myprojects.pauldirac.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachFactory {

    public Coach getMyCoach(String sportName) {
        if (sportName == null) {
            return null;
        }

        switch (sportName.toLowerCase()) {
            case "soccer": return new SoccerCoach();
            case "basketball": return new BasketballCoach();
            case "running": return new RunningCoach();
            default: throw new IllegalArgumentException("Unknown shape type: " + sportName);
        }
    }

}
