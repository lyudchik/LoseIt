package com.vladislavliudchyk.fitnessapp.loseit.utils.database;

import android.provider.BaseColumns;

/**
 * Class that describes application data
 */
public class AppData {
    /**
     * Private constructor
     */
    private AppData() {

    };

    /**
     * Class that describes tables in databases
     */
    public static final class DietInfoTable implements BaseColumns {
        /**
         * Value of the table title
         */
        public final static String TABLE_NAME = "nutrition";
        /**
         * Value of the table column title
         */
        public final static String COLUMN_TITLE = "title";
        /**
         * Value of the table column calorie per unit
         */
        public final static String COLUMN_CALORIE_PER_UNIT = "caloriePerUnit";
        /**
         * Value of the table column unit number
         */
        public final static String COLUMN_UNIT_NUMBER = "unitNumber";
        /**
         * Value of the column unitName
         */
        public final static String COLUMN_UNIT_NAME = "unitName";
    }
}
