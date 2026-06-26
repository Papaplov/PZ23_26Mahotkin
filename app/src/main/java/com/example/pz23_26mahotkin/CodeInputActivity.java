package com.example.pz23_26mahotkin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CodeInputActivity extends AppCompatActivity {

    private EditText otp1, otp2, otp3, otp4;
    private TextView timerText;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.code_input);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        timerText = findViewById(R.id.timerText);

        setupOtp();
        startTimer();
    }

    private void setupOtp() {
        otp1.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) otp2.requestFocus();
            }
            public void afterTextChanged(Editable s) { checkCode(); }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) otp3.requestFocus();
            }
            public void afterTextChanged(Editable s) { checkCode(); }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) otp4.requestFocus();
            }
            public void afterTextChanged(Editable s) { checkCode(); }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void afterTextChanged(Editable s) { checkCode(); }
        });
    }

    private void checkCode() {
        String code = otp1.getText().toString() +
                otp2.getText().toString() +
                otp3.getText().toString() +
                otp4.getText().toString();

        if (code.length() == 4) {
            if (code.equals("1234")) {
                Intent intent = new Intent(CodeInputActivity.this, PasswordCreateActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Неверный код", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Отправить код повторно можно будет через " + (millisUntilFinished / 1000) + " секунд");
                timerText.setClickable(false);
            }

            @Override
            public void onFinish() {
                timerText.setText("Отправить код повторно");
                timerText.setTextColor(Color.parseColor("#1A6FEE"));
                timerText.setClickable(true);
                timerText.setOnClickListener(v -> startTimer());
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}