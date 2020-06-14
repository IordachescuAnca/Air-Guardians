package com.example.air_guardiansproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterFavourite extends RecyclerView.Adapter<AdapterFavourite.ExampleViewHolder> {
    private ArrayList<BoxFavourite> boxFavourites;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView category_aqi;
        public TextView co_no2_o3;
        public TextView pm10_pm25;
        public TextView trees;
        public TextView healthRecomandation;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            category_aqi = itemView.findViewById(R.id.textView2);
            co_no2_o3= itemView.findViewById(R.id.textView3);
            pm10_pm25 = itemView.findViewById(R.id.textView4);
            healthRecomandation = itemView.findViewById(R.id.textView5);
            trees = itemView.findViewById(R.id.textView6);
        }
    }
    public AdapterFavourite(ArrayList<BoxFavourite> exampleList) {
        this.boxFavourites = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_favourite, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        BoxFavourite currentItem = boxFavourites.get(position);
        holder.name.setText(currentItem.getName());
        holder.category_aqi.setText(currentItem.getCategory_aqi());
        holder.co_no2_o3.setText(currentItem.getCo_no2_o3());
        holder.pm10_pm25.setText(currentItem.getPm10_pm25());
        holder.healthRecomandation.setText(currentItem.getHealthRecomandation());
        holder.trees.setText(currentItem.getTrees());
    }
    @Override
    public int getItemCount() {
        return boxFavourites.size();
    }
}
