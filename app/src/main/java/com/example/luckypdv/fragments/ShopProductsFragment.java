package com.example.luckypdv.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luckypdv.R;
import com.example.luckypdv.models.Product;
import com.google.android.material.navigation.NavigationView;

public class ShopProductsFragment extends Fragment {
    private TableLayout tlProducts;
    private ImageView ivSave;
    private Fragment self = this;

    public ShopProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop_products, container, false);
        initViews(root);
        initTable();
        return root;
    }

    private void initViews(View root) {
        tlProducts = root.findViewById(R.id.tl_products);
        ivSave = root.findViewById(R.id.iv_save);

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationView navigationView = self.getActivity().findViewById(R.id.nav_view);
                navigationView.getMenu().performIdentifierAction(R.id.nav_shop_list, 0);
            }
        });
    }

    private void initTable() {
        Product product;
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        TableRow tableRow = new TableRow(getContext());
        EditText editText = new EditText(getContext());

        product = new Product();
        product.setSKU("Aceite Cilx12 Bot");
        product.setCost(45.23f);
        product.setHighest(45.23f);
        product.setStock(100);

        tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(layoutParams);
        editText = new EditText(getContext());
        createRow(product, layoutParams, tableRow, editText);

        tlProducts.addView(tableRow);

        product = new Product();
        product.setSKU("Aceite Primorx12 Bot");
        product.setCost(42.00f);
        product.setHighest(45.23f);
        product.setStock(120);

        tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(layoutParams);
        editText = new EditText(getContext());
        createRow(product, layoutParams, tableRow, editText);

        tlProducts.addView(tableRow);

        product = new Product();
        product.setSKU("Aceite Saox12 Bot");
        product.setCost(41.50f);
        product.setHighest(45.23f);
        product.setStock(30);

        tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(layoutParams);
        editText = new EditText(getContext());
        createRow(product, layoutParams, tableRow, editText);

        tlProducts.addView(tableRow);
    }

    private void createRow(Product product, TableRow.LayoutParams layoutParams, TableRow tableRow, EditText editText) {
        editText.setText(product.getSKU());
        editText.setTextAppearance(R.style.table_text);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setLayoutParams(layoutParams);
        tableRow.addView(editText);

        editText = new EditText(getContext());
        editText.setText(String.format("%.2f", product.getCost()));
        editText.setTextAppearance(R.style.table_text);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(layoutParams);
        tableRow.addView(editText);

        editText = new EditText(getContext());
        editText.setText(String.format("%.2f", product.getHighest()));
        editText.setTextAppearance(R.style.table_text);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(layoutParams);
        tableRow.addView(editText);

        editText = new EditText(getContext());
        editText.setText(Integer.toString(product.getStock()));
        editText.setTextAppearance(R.style.table_text);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(layoutParams);
        tableRow.addView(editText);
    }
}
