// This custom adapter is used to get the data of food items and put them into custom layout
package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Food;
import com.example.foodapp.R;
import com.example.foodapp.ShowDetailsActivity;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>{

    ArrayList<Food> list;
    Context context;
    public static final String FOOD_OBJECT = "object";
    // Constructor which data from list to put it into the views and context
    public PopularAdapter(ArrayList<Food> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override

    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false);
        return new PopularAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        Food foodItem = list.get(position);
        holder.imageView.setImageResource(foodItem.getPic());
        holder.title.setText(foodItem.getTitle());
        holder.price.setText(String.valueOf(foodItem.getPrice()));
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sending an intent to showDetails class
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                intent.putExtra(FOOD_OBJECT,list.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView title;
        TextView price;
        TextView addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.popular_pic);
            title = itemView.findViewById(R.id.popular_title);
            price = itemView.findViewById(R.id.totalFee);
            addBtn = itemView.findViewById(R.id.popular_add);
        }
    }
}
