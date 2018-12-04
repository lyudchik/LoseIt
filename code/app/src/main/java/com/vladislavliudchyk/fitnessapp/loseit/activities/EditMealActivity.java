package com.vladislavliudchyk.fitnessapp.loseit.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.vladislavliudchyk.fitnessapp.loseit.R;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDiet;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;
import com.vladislavliudchyk.fitnessapp.loseit.utils.database.DBService;

/**
 * Class that allows to user to edit nutrients information
 */
public class EditMealActivity extends AppCompatActivity {

    /**
     * The value of success edit operation
     */
    public static final int EDIT_RESULT_OK = 100;
    /**
     * The value of success delete operation
     */
    public static final int DELETE_RESULT_OK = 200;

    /**
     * Set all content including toolbar, buttons and text fields
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);
        DBService dbService = new DBService(this);

        // process data passed in
        DailyDietItem food = (DailyDietItem) getIntent().getSerializableExtra("Food");
        final int position = getIntent().getIntExtra("position", -1);
        if (food != null){
            ((EditText) findViewById(R.id.diary_content_edit_title_edit)).setText(food.getTitle());
            ((EditText) findViewById(R.id.diary_content_edit_calory_per_unit_edit)).setText(Integer.toString(food.getCaloriePerUnit()));
            ((EditText) findViewById(R.id.diary_content_edit_amount_edit)).setText(Double.toString(food.getUnitNumber()));
            ((EditText) findViewById(R.id.diary_content_edit_unit_name_edit)).setText(food.getUnitName());
        }

        //toolbar related
        Toolbar toolbar = findViewById(R.id.toolbar_edit_meal);
        toolbar.inflateMenu(R.menu.edit_menu);
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
                Intent intent;

                switch (item.getItemId()){
                    case R.id.toolbar_edit_delete:
                        String titleText = ((EditText) findViewById(R.id.diary_content_edit_title_edit)).getText().toString();
                        String calPerUnitText = ((EditText) findViewById(R.id.diary_content_edit_calory_per_unit_edit)).getText().toString();
                        String amountText = ((EditText) findViewById(R.id.diary_content_edit_amount_edit)).getText().toString();
                        String unitNameText = ((EditText) findViewById(R.id.diary_content_edit_unit_name_edit)).getText().toString();
                        DailyDietItem toDelete = new DailyDietItem(titleText,
                                Integer.parseInt(calPerUnitText),
                                Double.parseDouble(amountText), unitNameText);
                        //dbService.deleteElement(toDelete);
                        showDeleteAlertDialog(position);
                        break;
                    case R.id.toolbar_edit_done:
                        titleText = ((EditText) findViewById(R.id.diary_content_edit_title_edit)).getText().toString();
                        calPerUnitText = ((EditText) findViewById(R.id.diary_content_edit_calory_per_unit_edit)).getText().toString();
                        amountText = ((EditText) findViewById(R.id.diary_content_edit_amount_edit)).getText().toString();
                        unitNameText = ((EditText) findViewById(R.id.diary_content_edit_unit_name_edit)).getText().toString();
                        if (titleText.equals("") || calPerUnitText.equals("") || amountText.equals("") || unitNameText.equals("")) {
                            showErrorDialog();
                        } else {
                            int caloryPerUnit = (int) Double.parseDouble(calPerUnitText);
                            double unitNumber = Double.parseDouble(amountText);
                            DailyDietItem food = new DailyDietItem(titleText, caloryPerUnit, unitNumber, unitNameText);
                            intent = new Intent();
//                            intent.putExtra("Food", food);
//                            intent.putExtra("position", position);
                            String currentDateString = getIntent().getStringExtra("currentDateString");
                            String meal = getIntent().getStringExtra("whichMeal");
                            DailyDiet diary = MainActivity.getPersonalData().getDiary(currentDateString);
                            switch (meal){
                                case "breakfast":
                                    diary.editBreakfastList(position, food);
                                    break;
                                case "lunch":
                                    diary.editLunchList(position, food);
                                    break;
                                case "dinner":
                                    diary.editDinnerList(position, food);
                                    break;
                                case "snack":
                                    diary.editSnackList(position, food);
                                    break;
                            }
                            setResult(EDIT_RESULT_OK, intent);
                            finish();
                        }
                        return true;
                }
                return false;
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

    /**
     * Show alert dialog when user clicked delete button
     * @param position the value of meal position
     */
    private void showDeleteAlertDialog(final int position){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.general_alert));
        alertDialog.setMessage(getString(R.string.delete_item_alert));
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), (DialogInterface.OnClickListener) null);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                String currentDateString = getIntent().getStringExtra("currentDateString");
                String meal = getIntent().getStringExtra("whichMeal");
                DailyDiet diary = MainActivity.getPersonalData().getDiary(currentDateString);
                switch (meal){
                    case "breakfast":
                        diary.removeBreakfastList(position);
                        break;
                    case "lunch":
                        diary.removeLunchList(position);
                        break;
                    case "dinner":
                        diary.removeDinnerList(position);
                        break;
                    case "snack":
                        diary.removeSnackList(position);
                        break;
                }
                setResult(DELETE_RESULT_OK, intent);
                finish();
            }
        });
        alertDialog.show();
    }

}
