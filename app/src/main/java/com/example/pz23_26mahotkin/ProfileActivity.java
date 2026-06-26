package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText etProfileGender;
    private ImageView ivProfileAvatar;

    private final ActivityResultLauncher<String> pickImage = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    ivProfileAvatar.setImageURI(uri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etProfileGender = findViewById(R.id.etProfileGender);
        ivProfileAvatar = findViewById(R.id.ivProfileAvatar);

        ivProfileAvatar.setOnClickListener(v -> pickImage.launch("image/*"));

        etProfileGender.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, etProfileGender);
            popupMenu.getMenu().add("Мужской");
            popupMenu.getMenu().add("Женский");
            popupMenu.setOnMenuItemClickListener(item -> {
                etProfileGender.setText(item.getTitle());
                return true;
            });
            popupMenu.show();
        });

        View menuAnalyses = findViewById(R.id.menu_analyses);
        ImageView ivProfile = findViewById(R.id.iv_menu_profile);
        TextView tvProfile = findViewById(R.id.tv_menu_profile);

        if (ivProfile != null && tvProfile != null) {
            ivProfile.setImageResource(R.drawable.ic_profile_blue);
            tvProfile.setTextColor(Color.parseColor("#1A6FEE"));
        }

        if (menuAnalyses != null) {
            menuAnalyses.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, AnalysesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            });
        }
    }
}