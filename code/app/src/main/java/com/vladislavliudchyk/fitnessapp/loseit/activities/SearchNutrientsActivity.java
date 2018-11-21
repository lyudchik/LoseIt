package com.vladislavliudchyk.fitnessapp.loseit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.vladislavliudchyk.fitnessapp.loseit.R;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDiet;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;
import com.vladislavliudchyk.fitnessapp.loseit.utils.ListSearchMealAdapter;
import com.vladislavliudchyk.fitnessapp.loseit.utils.NutrientsResult;
import com.google.gson.Gson;
import com.vladislavliudchyk.fitnessapp.loseit.utils.database.DBService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Class that describes searching nutrients in databases and
 * displays information to the user
 */

public class SearchNutrientsActivity extends AppCompatActivity {

    /**
     * Value of the constant means user's activity (add meal manually)
     */
    public static final int CREATE_FOOD_IN_SEARCH = 21;
    /**
     * Value of the connect timeout constant
     */
    public static final int CONNECT_TIMEOUT = 5000;
    /**
     * Value of the connect error constant
     */
    private static final int CONNECT_ERROR = -1;

    //TODO - add description
    private DBService dbService;

    /**
     * Value of the meal name
     */
    String meal;
    /**
     * Value of the current date
     */
    String currentDateString;

    /**
     * Value of the similar title
     */
    String similarTitle;

    /**
     * This method sets all content to the certain app screen
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbService = new DBService(this);
        setContentView(R.layout.activity_search_nutrients);
        meal = getIntent().getStringExtra("whichMeal");
        currentDateString = getIntent().getStringExtra("currentDateString");
        Toolbar toolbar = findViewById(R.id.toolbar_search_meal);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.search_food_result_from_internet);
        View createFoodButton = getLayoutInflater().inflate(R.layout.create_meal_button, null);
        linearLayout.addView(createFoodButton);

        (createFoodButton.findViewById(R.id.create_food_button)).setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SearchNutrientsActivity.this,
                        AddMealActivity.class), CREATE_FOOD_IN_SEARCH);
            }
        });

        SearchView searchView = findViewById(R.id.search_meal_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                SearchNutrientsAsyncTask SearchNutrientsAsyncTask = new SearchNutrientsAsyncTask();
                SearchNutrientsAsyncTask.execute(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        EditText textView = searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text);
        textView.setHintTextColor(ContextCompat.getColor(SearchNutrientsActivity.this,
                R.color.colorGrey));
        textView.setTextColor(ContextCompat.getColor(SearchNutrientsActivity.this,
                R.color.colorLightText));

        ImageView icon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        icon.setColorFilter(SearchNutrientsActivity.this.getResources().getColor(R.color.colorGrey));
    }

    /**
     * Method that adds meal into the daily diet diary
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_FOOD_IN_SEARCH && resultCode == RESULT_OK) {
            DailyDietItem food = (DailyDietItem) data.getSerializableExtra("Food");
            DailyDiet diary = MainActivity.getPersonalData().getDiary(currentDateString);
            switch (meal){
                case "breakfast":
                    diary.addBreakfastList(food);
                    break;
                case "lunch":
                    diary.addLunchList(food);
                    break;
                case "dinner":
                    diary.addDinnerList(food);
                    break;
                case "snack":
                    diary.addSnackList(food);
                    break;
            }
            finish();
        }
    }

    /**
     * Class that provides searching in databases information about nutrition
     */
    private class SearchNutrientsAsyncTask extends AsyncTask<String, Integer, List<DailyDietItem>> {

        /**
         * This method removes all views and sets progress circe
         * {@inheritDoc}
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LinearLayout linearLayout = findViewById(R.id.search_food_result_from_internet);
            linearLayout.removeAllViews();
            linearLayout.addView(getLayoutInflater().inflate(R.layout.search_progress_circle, null));
        }

        /**
         * This method notifies user when error of connection occured
         * {@inheritDoc}
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values[0] == CONNECT_ERROR){
                Toast.makeText(SearchNutrientsActivity.this,
                        R.string.internet_connection_error, Toast.LENGTH_LONG).show();
            }
        }

        /**
         * This method searching for the meal information in databases
         * {@inheritDoc}
         */
        @Override
        protected List<DailyDietItem> doInBackground(String... strings) {
            similarTitle = strings[0];
            String appId = "e1265399";
            String appKey = "91f3c74aa5fa6a416df6ff89431a3f5e";
            String initialPart = "https://api.nutritionix.com/v1_1/search/";
            String query = strings[0].replace(" ", "%20");
            String resultsNum = "?results=0:20&";
            String fields = "fields=item_name,brand_name,nf_calories,nf_serving_size_qty,nf_serving_size_unit&";
            String idAndKey = "appId=" + appId + "&appKey=" + appKey;
            String URLString = initialPart + query + resultsNum + fields + idAndKey;

            try {
                URL url = new URL(URLString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.connect();

                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK){
                    InputStreamReader inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }
                    String resultString = stringBuilder.toString();
                    Gson gson = new Gson();
                    NutrientsResult results = gson.fromJson(resultString, NutrientsResult.class);
                    List<DailyDietItem> foodList = new ArrayList<>();
                    for (NutrientsResult.NutrientsGoal hit :results.hits){
                        NutrientsResult.NutrientsGoal.NutrientsItem item = hit.fields;
                        foodList.add(new DailyDietItem(item.item_name + ", " + item.brand_name,
                                (int) item.nf_calories, item.nf_serving_size_qty,
                                item.nf_serving_size_unit));
                    }
                    return foodList;

                }else{
                    publishProgress(CONNECT_ERROR);
                }

            } catch (MalformedURLException e) {
                publishProgress(CONNECT_ERROR);
                e.printStackTrace();

            } catch (IOException e) {
                publishProgress(CONNECT_ERROR);
                e.printStackTrace();
            }

            return new ArrayList<>();
        }

        /**
         * This method displays data after searching
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(List<DailyDietItem> foods) {
            if(!foods.isEmpty())
                super.onPostExecute(foods);
            else
                super.onPostExecute(dbService.getAllMeals());
            dbService.insertAll(foods);
            LinearLayout linearLayout = findViewById(R.id.search_food_result_from_internet);
            linearLayout.removeAllViews();
            // hide keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            View createFoodButton;

            //TODO - change controller
//            if (foods.isEmpty()) {
//                linearLayout.addView(getLayoutInflater().inflate(R.layout.error_no_results, null));
//                createFoodButton = getLayoutInflater().inflate(R.layout.create_meal_button, null);
//                linearLayout.addView(createFoodButton);
            if (foods.isEmpty()) {
                linearLayout.addView(getLayoutInflater().inflate(R.layout.nutrients_result, null));
                ListView listView = findViewById(R.id.search_meal_result_list);
                listView.setAdapter(new ListSearchMealAdapter(dbService.searchMeals(similarTitle), SearchNutrientsActivity.this));
                createFoodButton = getLayoutInflater().inflate(R.layout.create_meal_button, null);
                listView.addFooterView(createFoodButton);
                listView.setDivider(null);
            } else {
                linearLayout.addView(getLayoutInflater().inflate(R.layout.nutrients_result, null));
                ListView listView = findViewById(R.id.search_meal_result_list);
                listView.setAdapter(new ListSearchMealAdapter(foods, SearchNutrientsActivity.this));
                createFoodButton = getLayoutInflater().inflate(R.layout.create_meal_button, null);
                listView.addFooterView(createFoodButton);
                listView.setDivider(null);
            }
            (createFoodButton.findViewById(R.id.create_food_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SearchNutrientsActivity.this, AddMealActivity.class);
                    startActivityForResult(intent, CREATE_FOOD_IN_SEARCH);
                }
            });
        }
    }
}
