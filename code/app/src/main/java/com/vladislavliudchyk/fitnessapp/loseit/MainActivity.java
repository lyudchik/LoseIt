package com.vladislavliudchyk.fitnessapp.loseit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String currentDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDateString = PickDate.millisecondTimeToString(System.currentTimeMillis());
        setToolbar();
        setDate();
    }

    @Override
    protected void onResume() {
       super.onResume();
        setAllDataInMain();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

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
                        //setAllDataInMain();
                    }
                }, date.getYear() + 1900, date.getMonth(), date.getDate());
                dialog.show();
            }
        });
    }

    private void showInputDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_text_editor, null);
        final EditText editText = view.findViewById(R.id.text_editor_of_calorie_restriction);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.set_goal_hint);
        builder.setView(editText);
///        editText.setText(userData.getGoal());
        builder.setPositiveButton(R.string.dialog_positive_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText().toString().equals("")){
                    return;
                }
                //userData.setGoal(Integer.parseInt(editText.getText().toString()));
                //setAllDataInMain();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    private void setTableData() {
        ((TextView) findViewById(R.id.goal_number)).setText("2000");
        ((TextView) findViewById(R.id.food_number)).setText("100");
        ((TextView) findViewById(R.id.exercise_number)).setText("100");
        ((TextView) findViewById(R.id.remaining_number)).setText("1800");
    }

    private void setListData(int R_id_header, int R_id_footer, int R_id_item, int totalCalorie, final int R_string_item, int R_string_add_item) {
        View header = getLayoutInflater().inflate(R.layout.diary_content_header, null);
        View footer = getLayoutInflater().inflate(R.layout.diary_content_footer, null);
        ((TextView) header.findViewById(R.id.diary_tag_title)).setText(R_string_item);
        ((TextView) header.findViewById(R.id.diary_tag_value)).setText(Integer.toString(totalCalorie));
        ((TextView) footer.findViewById(R.id.diary_add_button)).setText(R_string_add_item);
        ((FrameLayout) findViewById(R_id_header)).addView(header);
        ((FrameLayout) findViewById(R_id_footer)).addView(footer);

    };

    private void setAllDataInMain(){
        setTableData();
        setListData(R.id.diary_breakfast_header, R.id.diary_breakfast_footer, R.id.diary_breakfast, 0, R.string.diary_meal_breakfast, R.string.diary_add_meal);
        setListData(R.id.diary_lunch_header, R.id.diary_lunch_footer, R.id.diary_lunch, 0, R.string.diary_meal_lunch, R.string.diary_add_meal);
        setListData(R.id.diary_dinner_header, R.id.diary_dinner_footer, R.id.diary_dinner, 0, R.string.diary_meal_dinner, R.string.diary_add_meal);
        setListData(R.id.diary_snack_header, R.id.diary_snack_footer, R.id.diary_snack, 0, R.string.diary_meal_snacks, R.string.diary_add_meal);
        setListData(R.id.diary_exercise_header, R.id.diary_exercise_footer, R.id.diary_exercise, 0, R.string.diary_exercise , R.string.diary_add_exercise);

    }



}
