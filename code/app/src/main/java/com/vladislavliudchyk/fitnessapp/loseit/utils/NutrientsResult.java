package com.vladislavliudchyk.fitnessapp.loseit.utils;

import java.util.List;

/**
 * Class that stores information about nutrients searched from the databases
 */
public class NutrientsResult {
    /**
     * The value of the total hits
     */
    public int total_hits;
    /**
     * The value of the max score
     */
    public double max_score;
    /**
     * The value of the object of nutrient hits
     */
    public List<NutrientsGoal> hits;

    /**
     * Class that describes nutrient hits
     */
    public class NutrientsGoal {
        /**
         * The value of the goal index
         */
        public String _index;
        /**
         * The value of the goal type
         */
        public String _type;
        /**
         * The value of the goal identifier
         */
        public String _id;
        /**
         * The value of the hit score
         */
        public double _score;
        /**
         * The value of the object of searched item
         */
        public NutrientsItem fields;

        /**
         * Class that describes one item that was searched
         */
        public class NutrientsItem {

            /**
             * The value of the meal name
             */
            public String item_name;
            /**
             * The value of the brand name
             */
            public String brand_name;
            /**
             * The value of the meal calories
             */
            public double nf_calories;
            /**
             * The value of the meal number of servings
             */
            public double nf_serving_size_qty;
            /**
             * The value of the meal size of servings
             */
            public String nf_serving_size_unit;

        }
    }
}
