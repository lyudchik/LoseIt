package com.vladislavliudchyk.fitnessapp.loseit.data;


/**
 * Class that describes one unit of daily nutrition
 */
public class DailyDietItem {

    /**
     * Value of the dish title
     */
    private String title;
    /**
     * Value of the calorie per unit
     */
    private int caloriePerUnit;
    /**
     * Value of the number of serving size
     */
    private double unitNumber;
    /**
     * Value of the serving size
     */
    private String unitName;

    /**
     * Public constructor
     * @param title value of the dish title
     * @param caloriePerUnit value of the calorie per unit
     * @param unitNumber value of the number of serving size
     * @param unitName value of the serving size
     */
    public DailyDietItem(String title, int caloriePerUnit, double unitNumber, String unitName) {
        this.title = title;
        this.caloriePerUnit = caloriePerUnit;
        this.unitNumber = unitNumber;
        this.unitName = unitName;
    }

    /**
     * Get title of the dish
     * @return value of the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set new title for the dish
     * @param title new value of the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get value of the total calorie number per unit
     * @return value of the calorie
     */
    public int getCaloriePerUnit() {
        return caloriePerUnit;
    }

    /**
     * Set new value of the total calorie number per unit
     * @param caloriePerUnit new value of the calorie number
     */
    public void setCaloriePerUnit(int caloriePerUnit) {
        this.caloriePerUnit = caloriePerUnit;
    }

    /**
     * Get value of the number of servings
     * @return value of the number of servings
     */
    public double getUnitNumber() {
        return unitNumber;
    }

    /**
     * Set new value for the number of servings
     * @param unitNumber new value of the servings
     */
    public void setUnitNumber(double unitNumber) {
        this.unitNumber = unitNumber;
    }

    /**
     * Get value of the serving size
     * @return value of the serving size
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Set new value for the serving size
     * @param unitName new value for the serving size
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * Get the total calorie amount per dish
     * @return value of the total calorie amount per dish
     */
    public int getTotalCalorie(){
        return (int) (caloriePerUnit * unitNumber);
    }
}
