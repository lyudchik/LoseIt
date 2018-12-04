package com.vladislavliudchyk.fitnessapp.loseit.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vladislavliudchyk.fitnessapp.loseit.R;
import com.vladislavliudchyk.fitnessapp.loseit.data.DailyDietItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that dislays object as a scrolling list of elements using RecyclerView adapter
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

    /**
     * List of items to display
     */
    private List<DailyDietItem> itemList = new ArrayList<>();

    /**
     * public constructor
     * @param itemList Value of the with with items
     */
    public ListItemAdapter(List<DailyDietItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * The value of listener for "onItemClick" event
     */
    private OnItemClickListner onItemClickListner;

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (!itemList.isEmpty()) {

            holder.title.setText((itemList.get(position)).getTitle());
            holder.value.setText(Integer.toString((itemList.get(position)).getTotalCalorie()));
            holder.amount.setText(Double.toString((itemList.get(position)).getUnitNumber()) + " " + ((DailyDietItem) itemList.get(position)).getUnitName());
            holder.all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListner.onItemClick(position);
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    /**
     * Class that represents views in the list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The value of the title text view
         */
        TextView title;
        /**
         * The value of the title text view
         */
        TextView value;
        /**
         * The value of the title text view
         */
        TextView amount;
        /**
         * The value of the relative layout of all element
         */
        RelativeLayout all;

        /**
         * Public constructor
         * @param itemView the value of itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.diary_content_title);
            value = itemView.findViewById(R.id.diary_content_value);
            amount = itemView.findViewById(R.id.diary_content_amount);
            all = itemView.findViewById(R.id.diary_item_layout);
        }
    }

    /**
     * Interface for click events
     */
    public interface OnItemClickListner{
        void onItemClick(int position);
    }

    /**
     * Expose click interface
     * @param onItemClickListner value of the object of interface for click events
     */
    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }
}
