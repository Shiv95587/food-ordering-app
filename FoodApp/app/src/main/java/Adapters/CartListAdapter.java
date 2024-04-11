package Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.ChangeNumberItemsListener;
import com.example.foodapp.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

import Helper.ManagementCart;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private ArrayList<Food> list;
    private Context context;
    private ManagementCart managementCart;
    public ChangeNumberItemsListener changeNumberItemsListener;

    // Constructor which data from list to put it into the views and context
    public CartListAdapter(ArrayList<Food> list, Context context,ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        this.context = context;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override

    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(list.get(position).getPrice()));
        holder.pic.setImageResource(list.get(position).getPic());
        holder.totalFee.setText(String.valueOf(Math.round(list.get(position).getNumberInCart() * list.get(position).getPrice())));
        holder.quantity.setText(String.valueOf(list.get(position).getNumberInCart()));

        // setting click listeners
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.incrementFood(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.decrementFood(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
        }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, quantity, feeEachItem, totalFee;
        ImageView plus, minus, pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cart_item_title);
            quantity = itemView.findViewById(R.id.cart_item_quantity);
            feeEachItem = itemView.findViewById(R.id.price_each_item);
            totalFee = itemView.findViewById(R.id.totalFee);
            plus = itemView.findViewById(R.id.cart_plus);
            minus = itemView.findViewById(R.id.cart_minus);
            pic = itemView.findViewById(R.id.cart_item_pic);
        }
    }
}
