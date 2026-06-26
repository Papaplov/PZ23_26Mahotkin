package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CardCreateActivity extends AppCompatActivity {

    private EditText etLastName, etFirstName, etBirthDate, etGender;
    private Button btnCreateCard;
    private TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.card_create);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etLastName = findViewById(R.id.etLastName);
        etFirstName = findViewById(R.id.etFirstName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etGender = findViewById(R.id.etGender);
        btnCreateCard = findViewById(R.id.btnCreateCard);
        tvSkip = findViewById(R.id.tvSkip);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                checkFields();
            }
        };

        etLastName.addTextChangedListener(watcher);
        etFirstName.addTextChangedListener(watcher);
        etBirthDate.addTextChangedListener(watcher);
        etGender.addTextChangedListener(watcher);

        etGender.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(CardCreateActivity.this, etGender);
            popupMenu.getMenu().add("Мужской");
            popupMenu.getMenu().add("Женский");
            popupMenu.setOnMenuItemClickListener(item -> {
                etGender.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });

        tvSkip.setOnClickListener(v -> {
            Intent intent = new Intent(CardCreateActivity.this, AnalysesActivity.class);
            startActivity(intent);
        });

        btnCreateCard.setOnClickListener(v -> {
            Intent intent = new Intent(CardCreateActivity.this, AnalysesActivity.class);
            startActivity(intent);
        });
    }

    private void checkFields() {
        String lastName = etLastName.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String birthDate = etBirthDate.getText().toString().trim();
        String gender = etGender.getText().toString().trim();

        if (!lastName.isEmpty() && !firstName.isEmpty() && !birthDate.isEmpty() && !gender.isEmpty()) {
            btnCreateCard.setEnabled(true);
            btnCreateCard.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1A6FEE")));
        } else {
            btnCreateCard.setEnabled(false);
            btnCreateCard.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
        }
    }
}