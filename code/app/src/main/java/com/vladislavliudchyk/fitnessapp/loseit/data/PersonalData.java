package com.vladislavliudchyk.fitnessapp.loseit.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that describes all personal user's data
 */
public class PersonalData implements Serializable {
    /**
     * Value of the calorie restriction
     */
    private int goal;
    /**
     * Value of the Map of the daily diet
     */
    private Map<String, DailyDiet> stringDiaryHashMap;

    /**
     * Public constructor
     */
    public PersonalData() {
        goal = 0;
        stringDiaryHashMap = new HashMap<>();
    }

    /**
     * Get daily diet
     * @param dateString value of the date to search
     * @return value of the daily diet
     */
    public DailyDiet getDiary(String dateString){
        DailyDiet diary;
        if (stringDiaryHashMap.containsKey(dateString)){
            diary =  stringDiaryHashMap.get(dateString);
        } else {
            diary = new DailyDiet(dateString);
            stringDiaryHashMap.put(dateString, diary);
        }
        return diary;
    }

    /**
     * Get total calorie restriction
     * @return amount of calories
     */
    public int getGoal() {
        return goal;
    }

    /**
     * Set total calorie restriction
     * @param goal new value for the calorie restriction
     */
    public void setGoal(int goal) {
        this.goal = goal;
    }
}
