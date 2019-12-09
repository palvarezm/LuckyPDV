package com.example.luckypdv.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luckypdv.R;
import com.example.luckypdv.adapters.ShopAdapter;
import com.example.luckypdv.models.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopListFragment extends Fragment {
    private List<Shop> shopList;
    private ShopAdapter shopAdapter;
    private RecyclerView rvShopList;
    private EditText etSearch;
    
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop_list, container, false);

        initViews(root);
        initData();
        initList();

        return root;
    }

    private void initViews(View root) {
        rvShopList = root.findViewById(R.id.rv_shop_list);
        etSearch = root.findViewById(R.id.et_search);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                shopAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initData() {
        shopList = new ArrayList<>();
        Shop shop = new Shop();

        shop.setName("Bodega Paquita");
        shop.setAddress("Av. Peru #1350 - San Martin de Porres");
        shop.setLatitude(-12.0326843);
        shop.setLongitude(-77.0615803);
        shopList.add(shop);

        shop = new Shop();
        shop.setName("Metro Izaguirre");
        shop.setAddress("Av Peru #2660 - San Martin de Porres");
        shop.setLatitude(-12.0316481);
        shop.setLongitude(-77.0771404);
        shopList.add(shop);

        shop = new Shop();
        shop.setName("Ernesto Tito Galvez / Mrdo. Las Frutas");
        shop.setAddress("Av.Peru #1360 - San Martin de Porres");
        shop.setLatitude(-12.0326626);
        shop.setLongitude(-77.0617391);
        shopList.add(shop);

        shop = new Shop();
        shop.setName("Ferreteria Carsina SAC");
        shop.setAddress("Av.Peru #1740 - San Martin de Porres");
        shop.setLatitude(-12.0321406);
        shop.setLongitude(-77.0663581);
        shopList.add(shop);
    }

    private void initList() {
        rvShopList.setHasFixedSize(true);
        shopAdapter  = new ShopAdapter(this, shopList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvShopList.setLayoutManager(linearLayoutManager);
        rvShopList.setAdapter(shopAdapter);
    }
}