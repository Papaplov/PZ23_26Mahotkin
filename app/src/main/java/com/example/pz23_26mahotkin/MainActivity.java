package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboard1);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TextView textSkip = findViewById(R.id.textSkip);


        List<OnboardItem> items = new ArrayList<>();
        items.add(new OnboardItem("Анализы", "Экспресс сбор и получение проб", R.drawable.illustration));
        items.add(new OnboardItem("Уведомления", "Вы быстро узнаете о результатах", R.drawable.__2022_09_17__19_21_1));
        items.add(new OnboardItem("Мониторинг", "Наши врачи всегда наблюдают\nза вашими показателями здоровья", R.drawable._130_1));


        OnboardAdapter adapter = new OnboardAdapter(items);
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {

        }).attach();


        textSkip.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginandregistrationActivity.class);
            startActivity(intent);
        });
    }
}