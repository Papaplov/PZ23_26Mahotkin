package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AnalysesActivity extends AppCompatActivity {

    private EditText etSearch;
    private TextView tvCancelSearch, tvCartBarTotal;
    private ScrollView mainScrollView, searchScrollView;
    private LinearLayout containerCatalogList, containerSearchResults;
    private FrameLayout layoutCartBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyses);

        etSearch = findViewById(R.id.etSearch);
        tvCancelSearch = findViewById(R.id.tvCancelSearch);
        tvCartBarTotal = findViewById(R.id.tvCartBarTotal);
        mainScrollView = findViewById(R.id.mainScrollView);
        searchScrollView = findViewById(R.id.searchScrollView);
        containerCatalogList = findViewById(R.id.containerCatalogList);
        containerSearchResults = findViewById(R.id.containerSearchResults);
        layoutCartBar = findViewById(R.id.layoutCartBar);
        View btnGoToCart = findViewById(R.id.btnGoToCart);

        buildCatalog();
        updateCartBar();

        etSearch.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                enterSearchMode();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                performSearch(s.toString().trim());
            }
        });

        tvCancelSearch.setOnClickListener(v -> exitSearchMode());

        btnGoToCart.setOnClickListener(v -> {
            startActivity(new Intent(AnalysesActivity.this, CartActivity.class));
        });

        View menuProfile = findViewById(R.id.menu_profile);
        ImageView ivAnalyses = findViewById(R.id.iv_menu_analyses);
        TextView tvAnalyses = findViewById(R.id.tv_menu_analyses);

        if (ivAnalyses != null && tvAnalyses != null) {
            ivAnalyses.setImageResource(R.drawable.ic_analyses_blue);
            tvAnalyses.setTextColor(Color.parseColor("#1A6FEE"));
        }

        if (menuProfile != null) {
            menuProfile.setOnClickListener(v -> {
                Intent intent = new Intent(AnalysesActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        buildCatalog();
        updateCartBar();
    }

    private void enterSearchMode() {
        tvCancelSearch.setVisibility(View.VISIBLE);
        mainScrollView.setVisibility(View.GONE);
        searchScrollView.setVisibility(View.VISIBLE);
        performSearch(etSearch.getText().toString().trim());
    }

    private void exitSearchMode() {
        etSearch.setText("");
        etSearch.clearFocus();
        tvCancelSearch.setVisibility(View.GONE);
        mainScrollView.setVisibility(View.VISIBLE);
        searchScrollView.setVisibility(View.GONE);
        buildCatalog();
    }

    private void buildCatalog() {
        containerCatalogList.removeAllViews();
        for (TestItem item : CartManager.allItems) {
            View card = LayoutInflater.from(this).inflate(R.layout.item_catalog_test, containerCatalogList, false);
            TextView tvName = card.findViewById(R.id.tvCatalogItemName);
            TextView tvTime = card.findViewById(R.id.tvCatalogItemTime);
            TextView tvPrice = card.findViewById(R.id.tvCatalogItemPrice);
            Button btnAction = card.findViewById(R.id.btnCatalogAction);

            tvName.setText(item.name);
            tvTime.setText(item.time);
            tvPrice.setText(item.price + " ₽");

            boolean isInCart = CartManager.cartList.contains(item);
            if (isInCart) {
                btnAction.setText("Убрать");
                btnAction.setBackgroundColor(Color.WHITE);
                btnAction.setTextColor(Color.parseColor("#1A6FEE"));
            } else {
                btnAction.setText("Добавить");
                btnAction.setBackgroundColor(Color.parseColor("#1A6FEE"));
                btnAction.setTextColor(Color.WHITE);
            }

            card.setOnClickListener(v -> {
                BottomSheetAnalysisFragment sheet = new BottomSheetAnalysisFragment(item, () -> {
                    buildCatalog();
                    updateCartBar();
                });
                sheet.show(getSupportFragmentManager(), "details");
            });

            btnAction.setOnClickListener(v -> {
                if (CartManager.cartList.contains(item)) {
                    CartManager.cartList.remove(item);
                } else {
                    CartManager.cartList.add(item);
                }
                buildCatalog();
                updateCartBar();
            });

            containerCatalogList.addView(card);
        }
    }

    private void performSearch(String query) {
        containerSearchResults.removeAllViews();
        if (query.isEmpty()) return;

        for (TestItem item : CartManager.allItems) {
            if (item.name.toLowerCase().contains(query.toLowerCase())) {
                View row = LayoutInflater.from(this).inflate(R.layout.item_search_row, containerSearchResults, false);
                TextView tvName = row.findViewById(R.id.tvSearchRowName);
                TextView tvPrice = row.findViewById(R.id.tvSearchRowPrice);
                TextView tvTime = row.findViewById(R.id.tvSearchRowTime);

                String text = item.name;
                SpannableString spannable = new SpannableString(text);
                int start = text.toLowerCase().indexOf(query.toLowerCase());
                if (start >= 0) {
                    int end = start + query.length();
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#1A6FEE")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                tvName.setText(spannable);
                tvPrice.setText(item.price + " ₽");
                tvTime.setText(item.time);

                row.setOnClickListener(v -> {
                    BottomSheetAnalysisFragment sheet = new BottomSheetAnalysisFragment(item, () -> {
                        updateCartBar();
                        if (searchScrollView.getVisibility() == View.VISIBLE) {
                            performSearch(etSearch.getText().toString().trim());
                        }
                    });
                    sheet.show(getSupportFragmentManager(), "details");
                });

                containerSearchResults.addView(row);
            }
        }
    }

    private void updateCartBar() {
        if (CartManager.cartList.isEmpty()) {
            layoutCartBar.setVisibility(View.GONE);
        } else {
            layoutCartBar.setVisibility(View.VISIBLE);
            tvCartBarTotal.setText(CartManager.getTotalPrice() + " ₽");
        }
    }
}