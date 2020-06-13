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
        public TextView category;
        public TextView aqi;
        public TextView co;
        public TextView no2;
        public TextView o3;
        public TextView pm10;
        public TextView pm25;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            category = itemView.findViewById(R.id.textView2);
            aqi = itemView.findViewById(R.id.textView3);
            co = itemView.findViewById(R.id.textView4);
            no2 = itemView.findViewById(R.id.textView5);
            o3 = itemView.findViewById(R.id.textView6);
            pm10 = itemView.findViewById(R.id.textView7);
            pm25 = itemView.findViewById(R.id.textView8);
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
        holder.category.setText(currentItem.getCategory());
        holder.aqi.setText(currentItem.getAqi());
        holder.co.setText(currentItem.getCo());
        holder.no2.setText(currentItem.getNo2());
        holder.o3.setText(currentItem.getO3());
        holder.pm10.setText(currentItem.getPm10());
        holder.pm25.setText(currentItem.getPm25());
    }
    @Override
    public int getItemCount() {
        return boxFavourites.size();
    }
}
