package com.example.pz23_26mahotkin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetAnalysisFragment extends BottomSheetDialogFragment {

    private TestItem item;
    private Runnable onStateChanged;

    public BottomSheetAnalysisFragment(TestItem item, Runnable onStateChanged) {
        this.item = item;
        this.onStateChanged = onStateChanged;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_analysis, container, false);

        TextView tvTitle = view.findViewById(R.id.tvSheetTitle);
        TextView tvTime = view.findViewById(R.id.tvSheetTime);
        ImageView btnClose = view.findViewById(R.id.btnSheetClose);
        Button btnAction = view.findViewById(R.id.btnSheetAction);

        tvTitle.setText(item.name);
        tvTime.setText(item.time);

        updateButtonState(btnAction);

        btnClose.setOnClickListener(v -> dismiss());

        btnAction.setOnClickListener(v -> {
            if (CartManager.cartList.contains(item)) {
                CartManager.cartList.remove(item);
            } else {
                CartManager.cartList.add(item);
            }
            updateButtonState(btnAction);
            if (onStateChanged != null) onStateChanged.run();
            dismiss();
        });

        return view;
    }

    private void updateButtonState(Button btn) {
        if (CartManager.cartList.contains(item)) {
            btn.setText("Убрать из корзины");
            btn.setBackgroundColor(Color.parseColor("#CCCCCC"));
            btn.setTextColor(Color.WHITE);
        } else {
            btn.setText("Добавить за " + item.price + " ₽");
            btn.setBackgroundColor(Color.parseColor("#1A6FEE"));
            btn.setTextColor(Color.WHITE);
        }
    }
}