package com.example.luckypdv.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luckypdv.R;
import com.example.luckypdv.models.Shop;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ObjectViewHolder> {
    private Fragment fragment;
    private List<Shop> shopList;
    private String TAG = "SHOPADAPTER";

    public ShopAdapter(Fragment fragment, List<Shop> shopList) {
        this.fragment = fragment;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public ShopAdapter.ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ObjectViewHolder holder, int position) {
        Shop shop = shopList.get(position);
        holder.tvName.setText(shop.getName());
        holder.tvAddress.setText(shop.getAddress());

        holder.ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationView navigationView = fragment.getActivity().findViewById(R.id.nav_view);
                navigationView.getMenu().performIdentifierAction(R.id.nav_shops_map, 0);
            }
        });

        holder.clItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Item touch");
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ObjectViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clItem;
        ImageView ivMap;
        TextView tvName, tvAddress;

        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            clItem = itemView.findViewById(R.id.item_layout);
            ivMap = itemView.findViewById(R.id.iv_google_maps);
            tvName = itemView.findViewById(R.id.tv_shop_name);
            tvAddress = itemView.findViewById(R.id.tv_shop_address);
        }
    }
}
