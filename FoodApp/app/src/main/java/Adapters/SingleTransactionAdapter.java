// This custom adapter is used to get the data of food items and put them into custom layout
package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.Transaction;

import java.util.ArrayList;

public class SingleTransactionAdapter extends RecyclerView.Adapter<SingleTransactionAdapter.ViewHolder>{

    ArrayList<Transaction> list;
    Context context;
    // Constructor which data from list to put it into the views and context
    public SingleTransactionAdapter(ArrayList<Transaction> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override

    public SingleTransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getFooditem().getPic());
        holder.itemNameTxt.setText(list.get(position).getFooditem().getNumberInCart() + " " + list.get(position).getFooditem().getTitle());
        holder.feeEachTxt.setText(String.valueOf(list.get(position).getFooditem().getPrice()));
        holder.totalFeeTxt.setText(String.valueOf(list.get(position).getFooditem().getNumberInCart() * list.get(position).getFooditem().getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView feeEachTxt;
        TextView totalFeeTxt;
        TextView itemNameTxt;
        TextView totalItemsFee;
        TextView deliveryFee;
        TextView taxFee;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_pic);
            feeEachTxt = itemView.findViewById(R.id.fee_each_item);
            totalFeeTxt = itemView.findViewById(R.id.textView22);
            itemNameTxt = itemView.findViewById(R.id.item_name);
        }
    }
}
