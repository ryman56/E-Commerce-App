// CartAdapter.java
package com.example.aplikasi_ecommerce.Adapter;

import android.content.Context;
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
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<PopularDomain> listItemSelected;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<PopularDomain> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        PopularDomain item = listItemSelected.get(position);
        if (item != null) {
            holder.title.setText(item.getTitle());
            holder.feeEachItem.setText("Rp. " + item.getPrice());
            holder.totalEachItem.setText("Rp. " + Math.round(item.getNumberInCart() * item.getPrice()));
            holder.num.setText(String.valueOf(item.getNumberInCart()));

            int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(item.getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .transform(new GranularRoundedCorners(30, 30, 30, 30))
                    .into(holder.pic);

            // Plus and Minus button listeners
            holder.plusItem.setOnClickListener(v -> {
                managementCart.plusNumberItem(listItemSelected, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.Change();
                });
            });

            holder.minusItem.setOnClickListener(v -> {
                managementCart.minusNumberItem(listItemSelected, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.Change();
                });
            });
        }
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, totalEachItem, num, plusItem, minusItem;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt_vwc);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
