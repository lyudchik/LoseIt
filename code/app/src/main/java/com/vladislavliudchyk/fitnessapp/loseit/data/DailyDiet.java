package com.vladislavliudchyk.fitnessapp.loseit.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that describes the whole diet day
 */
public class DailyDiet {
    /**
     * Value of the calendar date
     */
    private String date;
    /**
     * Value of the list of the breakfast dishes
     */
    private List<DailyDietItem> breakfastList;
    /**
     * Value of the list of the lunch dishes
     */
    private List<DailyDietItem> lunchList;
    /**
     * Value of the list of the dinner dishes
     */
    private List<DailyDietItem> dinnerList;
    /**
     * Value of the list of the snack dishes
     */
    private List<DailyDietItem> snackList;
    /**
     * Value of the list of the exercise names
     */
    private List<DailyDietItem> exerciseList;

    /**
     * Public constructor
     * @param date value of the calendar date to search
     */
    public DailyDiet(String date) {
        this.date = date;
        breakfastList = new ArrayList<>();
        lunchList = new ArrayList<>();
        dinnerList = new ArrayList<>();
        snackList = new ArrayList<>();
        exerciseList = new ArrayList<>();
    }

    /**
     * Get calories per breakfast
     * @return amount of calories at the breakfast
     */
    public int getBreakfastCalorie(){
        int res = 0;
        for (DailyDietItem foodItem:breakfastList){
            res += foodItem.getTotalCalorie();
        }
        return res;
    }

    /**
     * Get calories per lunch
     * @return amount of calories at the lunch
     */
    public int getLunchCalorie(){
        int res = 0;
        for (DailyDietItem foodItem:lunchList){
            res += foodItem.getTotalCalorie();
        }
        return res;
    }

    /**
     * Get calories per dinner
     * @return amount of calories at the dinner
     */
    public int getDinnerCalorie(){
        int res = 0;
        for (DailyDietItem foodItem:dinnerList){
            res += foodItem.getTotalCalorie();
        }
        return res;
    }

    /**
     * Get calories per snacks
     * @return amount of calories at the snacks
     */
    public int getSnackCalorie(){
        int res = 0;
        for (DailyDietItem foodItem:snackList){
            res += foodItem.getTotalCalorie();
        }
        return res;
    }

    /**
     * Get burned calories per exercises
     * @return amount of burned calories
     */
    public int getExerciseCalorie(){
        int res = 0;
        for (DailyDietItem exerciseItem:exerciseList){
            res += exerciseItem.getTotalCalorie();
        }
        return res;
    }

    /**
     * Get gained calories per day
     * @return amount of gained calories
     */
    public int getGainedCalorie(){
        return getBreakfastCalorie() + getDinnerCalorie() + getLunchCalorie() + getSnackCalorie();
    }

    /**
     * Get calories burned per day
     * @return amount of burned calories
     */
    public int getBurnedCalorie(){
        return getExerciseCalorie();
    }

    /**
     * Get remaining calories per day
     * @return amount of remaining calories
     */
    public int getRemainingCalorie(int goal){
        return goal - getGainedCalorie() + getBurnedCalorie();
    }

    /**
     * Get the value of the meal list for breakfast
     * @return value of the meal list for breakfast
     */
    public List<DailyDietItem> getBreakfastList() {
        return breakfastList;
    }

    /**
     * Set new value of the meal list for breakfast
     * @param breakfastList new value of the meal list for breakfast
     */
    public void setBreakfastList(List<DailyDietItem> breakfastList) {
        this.breakfastList = breakfastList;
    }

    /**
     * Get the value of the meal list for lunch
     * @return value of the meal list for lunch
     */
    public List<DailyDietItem> getLunchList() {
        return lunchList;
    }

    /**
     * Set new value of the meal list for lunch
     * @param lunchList new value of the meal list for lunch
     */
    public void setLunchList(List<DailyDietItem> lunchList) {
        this.lunchList = lunchList;
    }

    /**
     * Get the value of the meal list for dinner
     * @return value of the meal list for dinner
     */
    public List<DailyDietItem> getDinnerList() {
        return dinnerList;
    }

    /**
     * Set new value of the meal list for dinner
     * @param dinnerList new value of the meal list for dinner
     */
    public void setDinnerList(List<DailyDietItem> dinnerList) {
        this.dinnerList = dinnerList;
    }

    /**
     * Get the value of the meal list for snacks
     * @return value of the meal list for snacks
     */
    public List<DailyDietItem> getSnackList() {
        return snackList;
    }

    /**
     * Set new value of the meal list for snacks
     * @param snackList new value of the meal list for snacks
     */
    public void setSnackList(List<DailyDietItem> snackList) {
        this.snackList = snackList;
    }

    /**
     * Get the value of the exercise list names
     * @return value of the exercise list names
     */
    public List<DailyDietItem> getExerciseList() {
        return exerciseList;
    }

    /**
     * Set new value of the exercise list names
     * @param exerciseList new value of the exercise list names
     */
    public void setExerciseList(List<DailyDietItem> exerciseList) {
        this.exerciseList = exerciseList;
    }

    /**
     * Add dish to the list for breakfast
     * @param food new value of the dish
     */
    public void addBreakfastList(DailyDietItem food) {
        breakfastList.add(food);
    }

    /**
     * Add dish to the list for lunch
     * @param food new value of the dish
     */
    public void addLunchList(DailyDietItem food) {
        lunchList.add(food);
    }

    /**
     * Add dish to the list for dinner
     * @param food new value of the dish
     */
    public void addDinnerList(DailyDietItem food) {
        dinnerList.add(food);
    }

    /**
     * Add dish to the list for snacks
     * @param food new value of the dish
     */
    public void addSnackList(DailyDietItem food) {
        snackList.add(food);
    }

    /**
     * Add exercise to the list of exercises
     * @param exercise new value of the exercise
     */
    public void addExerciseList(DailyDietItem exercise){
        exerciseList.add(exercise);
    }

    /**
     * Remove dish from list of the dishes for breakfast
     * @param position index of the dish to remove
     */
    public void removeBreakfastList(int position){
        if (position < breakfastList.size() && position >= 0){
            breakfastList.remove(position);
        }
    }

    /**
     * Remove dish from list of the dishes for lunch
     * @param position index of the dish to remove
     */
    public void removeLunchList(int position){
        if (position < lunchList.size() && position >= 0){
            lunchList.remove(position);
        }
    }

    /**
     * Remove dish from list of the dishes for dinner
     * @param position index of the dish to remove
     */
    public void removeDinnerList(int position){
        if (position < dinnerList.size() && position >= 0){
            dinnerList.remove(position);
        }
    }

    /**
     * Remove dish from list of the dishes for snacks
     * @param position index of the dish to remove
     */
    public void removeSnackList(int position){
        if (position < snackList.size() && position >= 0){
            snackList.remove(position);
        }
    }

    /**
     * Remove exercise from list of the exercises
     * @param position index of the exercise to remove
     */
    public void removeExerciseList(int position){
        if (position < exerciseList.size() && position >= 0){
            exerciseList.remove(position);
        }
    }

    /**
     * Edit list of the dishes for breakfast
     * @param position index of the dish to edit
     * @param food value of the dish item
     */
    public void editBreakfastList(int position, DailyDietItem food){
        if (position < breakfastList.size() && position >= 0){
            breakfastList.set(position, food);
        }
    }

    /**
     * Edit list of the dishes for lunch
     * @param position index of the dish to edit
     * @param food value of the dish item
     */
    public void editLunchList(int position, DailyDietItem food){
        if (position < lunchList.size() && position >= 0){
            lunchList.set(position, food);
        }
    }

    /**
     * Edit list of the dishes for dinner
     * @param position index of the dish to edit
     * @param food value of the dish item
     */
    public void editDinnerList(int position, DailyDietItem food){
        if (position < dinnerList.size() && position >= 0){
            dinnerList.set(position, food);
        }
    }

    /**
     * Edit list of the dishes for dinner
     * @param position index of the dish to edit
     * @param food value of the dish item
     */
    public void editSnackList(int position, DailyDietItem food){
        if (position < snackList.size() && position >= 0){
            snackList.set(position, food);
        }
    }

    /**
     * Edit list of the exercises
     * @param position index of the exercise to edit
     * @param exercise value of the exercise item
     */
    public void editExerciseList(int position, DailyDietItem exercise){
        if (position < exerciseList.size() && position >= 0){
            exerciseList.set(position, exercise);
        }
    }

}
