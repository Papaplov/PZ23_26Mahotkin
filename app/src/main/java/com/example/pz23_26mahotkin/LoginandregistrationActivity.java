package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns; // Важно: импортируем встроенные шаблоны Android
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginandregistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.loginandregistration);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        Button btnNext = findViewById(R.id.button);

        btnNext.setEnabled(false);
        btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#999999")));


        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailInput = s.toString().trim();


                if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {

                    btnNext.setEnabled(true);
                    btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1A6FEE")));
                } else {

                    btnNext.setEnabled(false);
                    btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#999999")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(LoginandregistrationActivity.this, CodeInputActivity.class);
            startActivity(intent);
        });
    }
}