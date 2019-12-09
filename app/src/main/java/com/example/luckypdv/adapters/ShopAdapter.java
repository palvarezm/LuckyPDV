package com.example.luckypdv.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luckypdv.R;
import com.example.luckypdv.models.Shop;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ObjectViewHolder> implements Filterable {
    private Fragment fragment;
    private List<Shop> shopList;
    private List<Shop> shopListFull;

    public ShopAdapter(Fragment fragment, List<Shop> shopList) {
        this.fragment = fragment;
        this.shopList = shopList;
        shopListFull = new ArrayList<>(shopList);
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
                NavigationView navigationView = fragment.getActivity().findViewById(R.id.nav_view);
                navigationView.getMenu().performIdentifierAction(R.id.nav_shop_products, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    @Override
    public Filter getFilter() {
        return shopFilter;
    }

    private Filter shopFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            List<Shop> filteredShopList = new ArrayList<>();
            if (charString.isEmpty()) {
                filteredShopList.addAll(shopListFull);
            } else {
                for (Shop row : shopListFull) {
                    if (row.getName().toLowerCase().contains(charString.toLowerCase().trim()) ||
                            row.getAddress().contains(charString.toLowerCase().trim())) {
                        filteredShopList.add(row);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredShopList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            shopList.clear();
            shopList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
