package Helper;

import static Helper.TinyDB.CART_LIST;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.ChangeNumberItemsListener;
import com.example.foodapp.Food;
import com.example.foodapp.LoginActivity;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class ManagementCart {

    public Context context;
    private TinyDB tinyDB;


    public ManagementCart(Context context) {
        this.context = context;
        tinyDB = new TinyDB(context);
    }

    public void insertFood(Food item)
    {
        ArrayList<Food> list = getListCart();
        boolean isExist = false;
        int index = 0;
        for(int i = 0; i < list.size(); ++i)
        {
            if(list.get(i).getTitle().equals(item.getTitle()))
            {
                index = i;
                isExist = true;
                break;
            }
        }
        if(isExist)
        {
            list.get(index).setNumberInCart(item.getNumberInCart());
        }
        else
        {
            list.add(item);
        }

        tinyDB.putListObject(LoginActivity.user,list);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public void incrementFood(ArrayList<Food> foodList, int position, ChangeNumberItemsListener changeNumberItemsListener)
    {
        foodList.get(position).setNumberInCart(foodList.get(position).getNumberInCart() + 1);
        tinyDB.putListObject(LoginActivity.user,foodList);
        changeNumberItemsListener.changed();
    }

    public void decrementFood(ArrayList<Food> foodList,int position,ChangeNumberItemsListener changeNumberItemsListener)
    {
        if(foodList.get(position).getNumberInCart() == 1)
        {
            foodList.remove(position);
        }
        else
        {
            foodList.get(position).setNumberInCart(foodList.get(position).getNumberInCart() - 1);
        }

        tinyDB.putListObject(LoginActivity.user,foodList);
        changeNumberItemsListener.changed();
    }
    public ArrayList<Food> getListCart()
    {
        return tinyDB.getListObject(LoginActivity.user);
    }

    // The function adds all items fee and return the total
    public double getTotal(ArrayList<Food> foodList)
    {
        double totalFee = 0;

        int size = foodList.size();

        for(int i = 0; i < size; ++i)
        {
            double price = foodList.get(i).getPrice();
            int quantity = foodList.get(i).getNumberInCart();

            totalFee += (price * quantity);
        }

        return totalFee;
    }
}
