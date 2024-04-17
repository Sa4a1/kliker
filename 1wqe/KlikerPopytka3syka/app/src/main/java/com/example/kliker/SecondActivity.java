package com.example.kliker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class SecondActivity extends AppCompatActivity {
    private Button upgrade_click, DPS_UP, clickButton;
    private TextView DpsUpgradeCost, DPSTextView, upgrade_text_view, ClickLevelTextView, clickCountTextView;
    private double DPS = 0, DPSupgradecost = 10, upgradecost = 10, clickCount = 0;
    private int ClickLevel = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ClickLevelTextView = findViewById(R.id.click_level_text_view);
        upgrade_click = findViewById(R.id.Upgrade_click);
        clickButton = findViewById(R.id.click_button);
        DPS_UP = findViewById(R.id.DPS);
        DPSTextView = findViewById(R.id.DPS_text_view);


    }

    public void update(){
        ClickLevelTextView.setText("Сила Клика: " + ClickLevel);

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValue = df.format(upgradecost);
        upgrade_click.setText("Улучшить клик за " + formattedValue);

        String DPSFORMATED = df.format(DPS);
        DPSTextView.setText("DPS: " + DPSFORMATED);
        String formattedValued = df.format(DPSupgradecost);
        DPS_UP.setText("Улучшить автокликер за " + formattedValued);

        String d = df.format(clickCount);
        clickButton.setText(d);
    };

    @Override
    public void onResume() {
        super.onResume();
        upgrade_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCount < upgradecost) {
                    System.out.println("Привет, мир!");
                } else {
                    ClickLevel += 1;
                    clickCount -= upgradecost;
                    upgradecost *= 1.4;
                    update();
                }
            }
        });

        DPS_UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCount < DPSupgradecost) {
                    System.out.println("Привет, мир!");
                } else {
                    clickCount -= DPSupgradecost;
                    DPSupgradecost *= 1.6;
                    DPS = DPS + 0.6;
                    update();
                }
            }
        });

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount += ClickLevel;
                update();
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickCount += DPS;  // Увеличиваем clickCount на основе значения DPS
                DecimalFormat df = new DecimalFormat("#.##");
                String formatedClickCount = df.format((clickCount));
                clickButton.setText(formatedClickCount);  // Обновляем отображение clickCount
                handler.postDelayed(this, 1000);  // Повторяем через 1 секунду
            }
        }, 1000);  // Запускаем сразу и повторяем через 1 секунду

    }
}
