package com.vladislavliudchyk.fitnessapp.loseit.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vladislavliudchyk.fitnessapp.loseit.R;
import com.vladislavliudchyk.fitnessapp.loseit.activities.AddMealActivity;
import com.vladislavliudchyk.fitnessapp.loseit.activities.SearchMealActivity;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;

import java.util.List;

import static com.vladislavliudchyk.fitnessapp.loseit.activities.SearchMealActivity.CREATE_FOOD_IN_SEARCH;

/**
 * Class that displays meals, founded via Nutritionix API as a list of elements using Base adapter
 */

public class ListSearchMealAdapter extends BaseAdapter {

    /**
     * Value of the food list
     */
    private List<DailyDietItem> foodList;

    /**
     * Value of the executing context
     */
    private Context context;

    /**
     * Public constructor
     * @param foodList value of the food list
     * @param context value of the executing context
     */
    public ListSearchMealAdapter(List<DailyDietItem> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return foodList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = ((LayoutInflater)(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.diary_content, null);
        }
        ((TextView) view.findViewById(R.id.diary_content_title)).setText(foodList.get(i).getTitle());
        ((TextView) view.findViewById(R.id.diary_content_value)).setText(Integer.toString(foodList.get(i).getCaloriePerUnit()));
        ((TextView) view.findViewById(R.id.diary_content_amount)).setText(Double.toString(foodList.get(i).getUnitNumber()) + " " + foodList.get(i).getUnitName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddMealActivity.class);
                intent.putExtra("Food", foodList.get(i));
                ((SearchMealActivity)context).startActivityForResult(intent, CREATE_FOOD_IN_SEARCH);
            }
        });
        return view;
    }
}
