package com.example.pz23_26mahotkin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private LinearLayout containerCartItems;
    private TextView tvCartTotalSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        containerCartItems = findViewById(R.id.containerCartItems);
        tvCartTotalSum = findViewById(R.id.tvCartTotalSum);
        ImageView btnBack = findViewById(R.id.btnCartBack);
        ImageView btnClear = findViewById(R.id.btnCartClear);

        btnBack.setOnClickListener(v -> finish());

        btnClear.setOnClickListener(v -> {
            CartManager.cartList.clear();
            buildCartList();
        });

        buildCartList();
    }

    private void buildCartList() {
        containerCartItems.removeAllViews();
        for (TestItem item : CartManager.cartList) {
            View row = LayoutInflater.from(this).inflate(R.layout.item_cart_product, containerCartItems, false);
            TextView tvName = row.findViewById(R.id.tvCartItemName);
            TextView tvPrice = row.findViewById(R.id.tvCartItemPrice);
            TextView tvPatients = row.findViewById(R.id.tvCartItemPatientCount);
            ImageView btnRemove = row.findViewById(R.id.btnCartItemRemove);
            TextView btnMinus = row.findViewById(R.id.btnPatientMinus);
            TextView btnPlus = row.findViewById(R.id.btnPatientPlus);

            tvName.setText(item.name);
            tvPrice.setText(item.price + " ₽");

            updatePatientText(tvPatients, item.patientCount);

            btnRemove.setOnClickListener(v -> {
                CartManager.cartList.remove(item);
                buildCartList();
            });

            btnMinus.setOnClickListener(v -> {
                if (item.patientCount > 1) {
                    item.patientCount--;
                    updatePatientText(tvPatients, item.patientCount);
                    updateTotal();
                }
            });

            btnPlus.setOnClickListener(v -> {
                item.patientCount++;
                updatePatientText(tvPatients, item.patientCount);
                updateTotal();
            });

            containerCartItems.addView(row);
        }
        updateTotal();
    }

    private void updatePatientText(TextView view, int count) {
        String suffix = " пациентов";
        if (count == 1) suffix = " пациент";
        else if (count > 1 && count < 5) suffix = " пациента";
        view.setText(count + suffix);
    }

    private void updateTotal() {
        tvCartTotalSum.setText(CartManager.getTotalPrice() + " ₽");
    }
}