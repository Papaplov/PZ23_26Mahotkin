package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PasswordCreateActivity extends AppCompatActivity {

    private StringBuilder currentPin = new StringBuilder();
    private ImageView[] dots = new ImageView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.password_create);

        dots[0] = findViewById(R.id.pin_dot1);
        dots[1] = findViewById(R.id.pin_dot2);
        dots[2] = findViewById(R.id.pin_dot3);
        dots[3] = findViewById(R.id.pin_dot4);

        int[] buttonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        for (int id : buttonIds) {
            View btn = findViewById(id);
            if (btn != null) {
                btn.setOnClickListener(v -> {
                    if (currentPin.length() < 4) {
                        currentPin.append(((Button) v).getText());
                        updateDots();
                        if (currentPin.length() == 4) {
                            navigateToNext();
                        }
                    }
                });
            }
        }

        View btnBackspace = findViewById(R.id.btnBackspace);
        if (btnBackspace != null) {
            btnBackspace.setOnClickListener(v -> {
                if (currentPin.length() > 0) {
                    currentPin.deleteCharAt(currentPin.length() - 1);
                    updateDots();
                }
            });
        }

        View tvSkip = findViewById(R.id.tvSkip);
        if (tvSkip != null) {
            tvSkip.setOnClickListener(v -> navigateToNext());
        }

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    private void updateDots() {
        for (int i = 0; i < dots.length; i++) {
            if (dots[i] != null) {
                if (i < currentPin.length()) {
                    dots[i].setImageResource(R.drawable.circle_blue);
                } else {
                    dots[i].setImageResource(R.drawable.circle_gray);
                }
            }
        }
    }

    private void navigateToNext() {
        Intent intent = new Intent(PasswordCreateActivity.this, CardCreateActivity.class);
        startActivity(intent);
        finish();
    }
}