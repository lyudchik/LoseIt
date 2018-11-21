package com.vladislavliudchyk.fitnessapp.loseit.utils.database;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.vladislavliudchyk.fitnessapp.loseit.activities.MainActivity;
import com.vladislavliudchyk.fitnessapp.loseit.activities.SearchNutrientsActivity;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that describes
 */
public class DBService extends SQLiteOpenHelper {
    /**
     * Value of the database name
     */
    private static final String DATABASE_NAME = "LoseIt.db";

    /**
     * Value of the database version
     * Note: increment by 1 when database scheme changes
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Public constructor
     * @param context context of the application
     */
    public DBService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Executes when creating database
     * {@inheritDoc}
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Строка для создания таблицы
        String SQL_CREATE_NUTRITION_TABLE = "CREATE TABLE "
                + AppData.DietInfoTable.TABLE_NAME + " ("
                + AppData.DietInfoTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AppData.DietInfoTable.COLUMN_TITLE + " TEXT NOT NULL, "
                + AppData.DietInfoTable.COLUMN_CALORIE_PER_UNIT + " TEXT NOT NULL, "
                + AppData.DietInfoTable.COLUMN_UNIT_NUMBER + " TEXT NOT NULL, "
                + AppData.DietInfoTable.COLUMN_UNIT_NAME + " TEXT NOT NULL" + ")";

        // Execute table creation
        db.execSQL(SQL_CREATE_NUTRITION_TABLE);
    }

    /**
     * Executes, when database scheme changes
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AppData.DietInfoTable.TABLE_NAME);
        onCreate(db);
    }

    /**
     * This method save all elements to the local database
     * @param listMeal value of the list of elements
     */
    public void insertAll(List<DailyDietItem> listMeal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < listMeal.size(); i++) {
            contentValues.put(AppData.DietInfoTable.COLUMN_TITLE, listMeal.get(i).getTitle());
            contentValues.put(AppData.DietInfoTable.COLUMN_CALORIE_PER_UNIT, listMeal.get(i).getCaloriePerUnit());
            contentValues.put(AppData.DietInfoTable.COLUMN_UNIT_NUMBER, listMeal.get(i).getUnitNumber());
            contentValues.put(AppData.DietInfoTable.COLUMN_UNIT_NAME, listMeal.get(i).getUnitName());
            db.insert(AppData.DietInfoTable.TABLE_NAME, null, contentValues);
            contentValues.clear();
        }
    }

    /**
     * This method save one element to the local database
     * @param title value of the meal title
     * @param caloriePerUnit value of the meal calories per unit
     * @param unitNumber value of the meal number of servings
     * @param unitName value of the meal serving size
     */
    public void insertElement(String title, int caloriePerUnit, double unitNumber, String unitName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppData.DietInfoTable.COLUMN_TITLE, title);
        contentValues.put(AppData.DietInfoTable.COLUMN_CALORIE_PER_UNIT, caloriePerUnit);
        contentValues.put(AppData.DietInfoTable.COLUMN_UNIT_NUMBER, unitNumber);
        contentValues.put(AppData.DietInfoTable.COLUMN_UNIT_NAME, unitName);
        db.insert(AppData.DietInfoTable.TABLE_NAME, null, contentValues);
        contentValues.clear();
    }

    /**
     * Read from database all information
     * @return new value of the meals that were retrieved from database
     */
    public ArrayList<DailyDietItem> getAllMeals() {
        ArrayList<DailyDietItem> mealList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from nutrition", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            mealList.add(new DailyDietItem(
                    res.getString(1),
                    Integer.parseInt(res.getString(2)),
                    Double.parseDouble(res.getString(3)),
                    res.getString(4)
            ));
            res.moveToNext();
        }
        res.close();
        return mealList;
    }

    /**
     * This method search for information about nutriniton in local database
     * @param searchTitle Value of the title to search
     * @return new value of the meals that were retrieved from database
     */
    public ArrayList<DailyDietItem> searchMeals(String searchTitle) {
        ArrayList<DailyDietItem> mealList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM nutrition WHERE title LIKE '%" + searchTitle + "%'", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            mealList.add(new DailyDietItem(
                    res.getString(1),
                    Integer.parseInt(res.getString(2)),
                    Double.parseDouble(res.getString(3)),
                    res.getString(4)
            ));
            res.moveToNext();
        }
        res.close();
        return mealList;
    }

    public int getElementID(String title) {
        Integer result;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT _id FROM nutrition WHERE title = '%" + title + "%'", null);
        res.moveToFirst();
        result = res.getInt(0);
        res.close();
        return result;
    }

    /**
     * Read from database all information
     * @return new value of the meals that were retrieved from database
     */
    public ArrayList<DailyDietItem> getDistinctMeals() {
        ArrayList<DailyDietItem> mealList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select distinct * from nutrition", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            mealList.add(new DailyDietItem(
                    res.getString(1),
                    Integer.parseInt(res.getString(2)),
                    Double.parseDouble(res.getString(3)),
                    res.getString(4)
            ));
            res.moveToNext();
        }
        res.close();
        return mealList;
    }

    public void optimizeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DailyDietItem> list = getDistinctMeals();
        db.execSQL("DROP TABLE IF EXISTS " + AppData.DietInfoTable.TABLE_NAME);
        onCreate(db);
        insertAll(list);
    }

    public void deleteElement(DailyDietItem toDelete) {
        SQLiteDatabase db = this.getReadableDatabase();
        String title = toDelete.getTitle();
        Integer calPerUnit = toDelete.getCaloriePerUnit();
        Double unitNumber = toDelete.getUnitNumber();
        String unitName = toDelete.getUnitName();
        String DELETE_ELEMENT = "DELETE FROM " + AppData.DietInfoTable.TABLE_NAME
                + " WHERE title = " + title + " AND caloriePerUnit = " + calPerUnit
                + " AND unitNumber = " + unitNumber + " AND unitName = " + unitName;
        db.execSQL(DELETE_ELEMENT);
    }

}
