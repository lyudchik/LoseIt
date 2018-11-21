package com.vladislavliudchyk.fitnessapp.loseit.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vladislavliudchyk.fitnessapp.loseit.R;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDiet;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;
import com.vladislavliudchyk.fitnessapp.loseit.data.PersonalData;
import com.vladislavliudchyk.fitnessapp.loseit.utils.ListItemAdapter;
import com.vladislavliudchyk.fitnessapp.loseit.utils.PickDate;
import com.vladislavliudchyk.fitnessapp.loseit.utils.database.DBService;
import com.vladislavliudchyk.fitnessapp.loseit.utils.database.StorageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that describes main activity.
 * Set all content including scrolling list,
 * toolbar and buttons
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Value of the current date represented in String format
     */
    private static String currentDateString;
    /**
     * Value of the personal data of users
     */
    private static PersonalData personalData;
    /**
     * Value of the object which provides
     * access to local database
     */
    private DBService dbService;

    /**
     * Value of the request means adding meal for breakfast
     */
    public static final int REQUEST_ADD_BREAKFAST = 11;
    /**
     * Value of the request means adding meal for lunch
     */
    public static final int REQUEST_ADD_LUNCH = 12;
    /**
     * Value of the request means adding meal for dinner
     */
    public static final int REQUEST_ADD_DINNER = 13;
    /**
     * Value of the request means adding meal for snacks
     */
    public static final int REQUEST_ADD_SNACKS = 14;
    /**
     * Value of the request means adding exercise
     */
    public static final int REQUEST_ADD_EXERCISE = 15;

    /**
     * This method sets all data and content
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            dbService = new DBService(this);
            dbService.optimizeDB();
        } catch (SQLiteDatabaseCorruptException ex) {
            Toast.makeText(MainActivity.this, "Cannot open db", Toast.LENGTH_LONG).show();
        }
        personalData = StorageService.loadFromStorage(MainActivity.this);
        currentDateString = PickDate.millisecondTimeToString(System.currentTimeMillis());
        setToolbar();
        setDate();
    }

    /**
     * This method sets handler to onResume event
     * {@inheritDoc}
     */
    @Override
    protected void onResume() {
       super.onResume();
       setAllDataInMain();
    }

    /**
     * This method sets handler to onStop event(save all user data)
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();
        StorageService.writeToStorage(MainActivity.this, personalData);
        dbService.close();
    }

    /**
     * This method sets handler to onBackPressed event (exit from app)
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    /**
     * This method sets toolbar actions and calorie restriction
     */
    private void setToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_settings_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
                setAllDataInMain();
            }
        });
    }

    /**
     * This method set data chosen in Calendar
     */
    private void setDate() {
        final TextView textView = findViewById(R.id.main_toolbar_title);
        textView.setText(currentDateString);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = PickDate.stringToDate(currentDateString);
                Dialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        currentDateString = PickDate.dateToString(new Date(year - 1900, monthOfYear, dayOfMonth));
                        textView.setText(currentDateString);
                        setAllDataInMain();
                    }
                }, date.getYear() + 1900, date.getMonth(), date.getDate());
                dialog.show();
            }
        });
    }

    /**
     * This method sets handler for Input dialog of calorie restriction event
     */
    private void showInputDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_text_editor, null);
        final EditText editText = view.findViewById(R.id.text_editor_of_calorie_restriction);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.set_goal_hint);
        builder.setView(editText);
        builder.setPositiveButton(R.string.dialog_positive_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText().toString().equals("")){
                    return;
                }
                personalData.setGoal(Integer.parseInt(editText.getText().toString()));
                setAllDataInMain();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    /**
     * Sets calorie information to main screen
     */
    private void setTableData() {
        DailyDiet dailyDiet = personalData.getDiary(currentDateString);
        ((TextView) findViewById(R.id.goal_number)).setText(String.format("%,d", personalData.getGoal()));
        ((TextView) findViewById(R.id.food_number)).setText(String.format("%,d", dailyDiet.getGainedCalorie()));
        ((TextView) findViewById(R.id.exercise_number)).setText(String.format("%,d", dailyDiet.getBurnedCalorie()));
        ((TextView) findViewById(R.id.remaining_number)).setText(String.format("%,d", dailyDiet.getRemainingCalorie(personalData.getGoal())));
    }

    /**
     * Set content for scrolling list for every food intake
     * @param R_id_header Value of the header part of the meal list
     * @param R_id_footer Value of the footer part of the meal list
     * @param R_id_item
     * @param itemList List of items represents information about meals
     * @param totalCalorie Value of the total calories for every food intake
     * @param R_string_item Value of the button
     * @param R_string_add_item Value of the label "Add meal"
     */
    private void setListData(int R_id_header, int R_id_footer, int R_id_item, final List<DailyDietItem> itemList, int totalCalorie, final int R_string_item, int R_string_add_item) {
        View header = getLayoutInflater().inflate(R.layout.diary_content_header, null);
        View footer = getLayoutInflater().inflate(R.layout.diary_content_footer, null);
        ((TextView) header.findViewById(R.id.diary_tag_title)).setText(R_string_item);
        ((TextView) header.findViewById(R.id.diary_tag_value)).setText(Integer.toString(totalCalorie));
        ((TextView) footer.findViewById(R.id.diary_add_button)).setText(R_string_add_item);
        ((FrameLayout) findViewById(R_id_header)).addView(header);
        ((FrameLayout) findViewById(R_id_footer)).addView(footer);

        RecyclerView listViewItem = findViewById(R_id_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        listViewItem.setLayoutManager(layoutManager);
        ListItemAdapter listItemAdapter = new ListItemAdapter(new ArrayList<>(itemList));
        listItemAdapter.setOnItemClickListner(new ListItemAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                switch (R_string_item) {
                    case R.string.diary_meal_breakfast:
                        intent = new Intent(MainActivity.this, EditMealActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("Food", (DailyDietItem) itemList.get(position));
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "breakfast");
                        startActivityForResult(intent, REQUEST_ADD_BREAKFAST);
                        break;
                    case R.string.diary_meal_lunch:
                        intent = new Intent(MainActivity.this, EditMealActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("Food", (DailyDietItem) itemList.get(position));
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "lunch");
                        startActivityForResult(intent, REQUEST_ADD_LUNCH);
                        break;
                    case R.string.diary_meal_dinner:
                        intent = new Intent(MainActivity.this, EditMealActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("Food", (DailyDietItem) itemList.get(position));
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "dinner");
                        startActivityForResult(intent, REQUEST_ADD_DINNER);
                        break;
                    case R.string.diary_meal_snacks:
                        intent = new Intent(MainActivity.this, EditMealActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("Food", (DailyDietItem) itemList.get(position));
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "snack");
                        startActivityForResult(intent, REQUEST_ADD_SNACKS);
                        break;
                    case R.string.diary_exercise:
                        intent = new Intent(MainActivity.this, EditExerciseActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("Exercise", itemList.get(position));
                        intent.putExtra("currentDateString", currentDateString);
                        startActivity(intent);
                        startActivityForResult(intent, REQUEST_ADD_EXERCISE);
                        break;
                }
            }
        });
        listViewItem.setAdapter(listItemAdapter);

        //set footer (add button)
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (R_string_item) {
                    case R.string.diary_meal_breakfast:
                        intent = new Intent(MainActivity.this, SearchNutrientsActivity.class);
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "breakfast");
                        startActivity(intent);
                        break;
                    case R.string.diary_meal_lunch:
                        intent = new Intent(MainActivity.this, SearchNutrientsActivity.class);
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "lunch");
                        startActivity(intent);
                        break;
                    case R.string.diary_meal_dinner:
                        intent = new Intent(MainActivity.this, SearchNutrientsActivity.class);
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "dinner");
                        startActivity(intent);
                        break;
                    case R.string.diary_meal_snacks:
                        intent = new Intent(MainActivity.this, SearchNutrientsActivity.class);
                        intent.putExtra("currentDateString", currentDateString);
                        intent.putExtra("whichMeal", "snack");
                        startActivity(intent);
                        break;
                    case R.string.diary_exercise:
                        intent = new Intent(MainActivity.this, AddExerciseActivity.class);
                        intent.putExtra("currentDateString", currentDateString);
                        startActivity(intent);
                        break;
                }
            }
        });

    };

    /**
     * Set all content to main app screen
     */
    private void setAllDataInMain(){
        setTableData();
        DailyDiet dailyDiet = personalData.getDiary(currentDateString);
        setListData(R.id.diary_breakfast_header, R.id.diary_breakfast_footer, R.id.diary_breakfast, dailyDiet.getBreakfastList() , dailyDiet.getBreakfastCalorie(), R.string.diary_meal_breakfast, R.string.diary_add_meal);
        setListData(R.id.diary_lunch_header, R.id.diary_lunch_footer, R.id.diary_lunch, dailyDiet.getLunchList() , dailyDiet.getLunchCalorie(), R.string.diary_meal_lunch, R.string.diary_add_meal);
        setListData(R.id.diary_dinner_header, R.id.diary_dinner_footer, R.id.diary_dinner, dailyDiet.getDinnerList() , dailyDiet.getDinnerCalorie(), R.string.diary_meal_dinner, R.string.diary_add_meal);
        setListData(R.id.diary_snack_header, R.id.diary_snack_footer, R.id.diary_snack, dailyDiet.getSnackList() , dailyDiet.getSnackCalorie(), R.string.diary_meal_snacks, R.string.diary_add_meal);
        setListData(R.id.diary_exercise_header, R.id.diary_exercise_footer, R.id.diary_exercise, dailyDiet.getExerciseList() , dailyDiet.getExerciseCalorie(), R.string.diary_exercise , R.string.diary_add_exercise);

    }

    /**
     * This method returns personal data
     * @return value of the user's personal data
     */
    public static PersonalData getPersonalData() {
        return personalData;
    }

}
