package com.example.aplikasi_ecommerce.Adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.aplikasi_ecommerce.Domain.PopularDomain;
import com.example.aplikasi_ecommerce.Helper.ChangeNumberItemsListener;
import com.example.aplikasi_ecommerce.Helper.ManagementCart;
import com.example.aplikasi_ecommerce.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {

    ArrayList<PopularDomain> listItemSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<PopularDomain> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {

        holder.title.setText(listItemSelected.get(position).getTitle());
        holder.feeEachItem.setText("Rp. " + listItemSelected.get(position).getPrice());
        holder.totalEachItem.setText("Rp. " + Math.round((listItemSelected.get(position).getNumberInCart()) * listItemSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listItemSelected.get(position).getNumberInCart()));

        int drawableResource = holder.itemView.getContext().getResources().getIdentifier(listItemSelected.get(position).getPicUrl()
                , "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic);
        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberItem(listItemSelected, position, new ChangeNumberItemsListener() {
            @Override
            public void Change() {
                notifyDataSetChanged();
                changeNumberItemsListener.Change();
            }
        }));
        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberItem(listItemSelected, position, new ChangeNumberItemsListener() {
            @Override
            public void Change() {
                notifyDataSetChanged();
                changeNumberItemsListener.Change();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
