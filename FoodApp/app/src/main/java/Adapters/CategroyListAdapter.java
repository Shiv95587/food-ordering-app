package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CategroyListAdapter extends RecyclerView.Adapter<CategroyListAdapter.ViewHolder> {

    private ArrayList<Food> list;
    Context context;


    public CategroyListAdapter(Context context, ArrayList<Food> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategroyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategroyListAdapter.ViewHolder holder, int position) {
        holder.foodPic.setImageResource(list.get(position).getPic());
        holder.titleTxt.setText(list.get(position).getTitle());
        holder.feeTxt.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView foodPic;
        TextView titleTxt;
        TextView feeTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodPic = itemView.findViewById(R.id.category_list_item_pic);
            titleTxt = itemView.findViewById(R.id.category_list_item_title);
            feeTxt = itemView.findViewById(R.id.category_list_item_fee);
        }
    }
}
