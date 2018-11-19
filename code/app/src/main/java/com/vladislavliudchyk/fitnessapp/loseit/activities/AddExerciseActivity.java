package com.vladislavliudchyk.fitnessapp.loseit.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.vladislavliudchyk.fitnessapp.loseit.R;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;

/**
 * Class that allows to user to add exercise information
 */
public class AddExerciseActivity extends AppCompatActivity {

    /**
     * Set all content including toolbar, buttons and text fields
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar_add_exercise);
        toolbar.inflateMenu(R.menu.check_menu);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String title = ((EditText) findViewById(R.id.diary_exercise_title_edit)).getText().toString();
                String calPerUnitText = ((EditText) findViewById(R.id.diary_exercise_calorie_per_unit_edit)).getText().toString();
                String unitNumberText = ((EditText) findViewById(R.id.diary_exercise_amount_edit)).getText().toString();
                String unitName = ((EditText) findViewById(R.id.diary_exercise_unit_name_edit)).getText().toString();
                if (title.equals("") || calPerUnitText.equals("") || unitNumberText.equals("") || unitName.equals("")) {
                    showErrorDialog();
                }
                else{
                    int caloriePerUnit = (int) Double.parseDouble(calPerUnitText);
                    double unitNumber = Double.parseDouble(unitNumberText);

                    DailyDietItem exercise = new DailyDietItem(title, caloriePerUnit, unitNumber, unitName);
                    String dateString = getIntent().getStringExtra("currentDateString");
                    MainActivity.getPersonalData().getDiary(dateString).addExerciseList(exercise);
                    finish();
                }
                return true;
            }
        });
    }

    /**
     * Show error dialog when user doesn't follow certain instructions
     */
    private void showErrorDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.general_alert));
        alertDialog.setMessage(getString(R.string.required_item_empty_error));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.dismiss), (DialogInterface.OnClickListener) null);
        alertDialog.show();
    }
}
