package com.example.mobilebank.ui.dashboard.PayLife;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Payhistory extends AppCompatActivity {

    private TextView Payhistory_TV_bankcardID;
    private TextView Payhistory_TV_schoolcardID;
    private DatabaseHelper dbHelper;
    private LinearLayout Payhistory_LL_lastFive_1;
    private TextView Payhistory_TV_lastFive_1;
    private LinearLayout Payhistory_LL_lastFive_2;
    private TextView Payhistory_TV_lastFive_2;
    private LinearLayout Payhistory_LL_lastFive_3;
    private TextView Payhistory_TV_lastFive_3;
    private LinearLayout Payhistory_LL_lastFive_4;
    private TextView Payhistory_TV_lastFive_4;
    private LinearLayout Payhistory_LL_lastFive_5;
    private TextView Payhistory_TV_lastFive_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payhistory);
        setTitle("校园卡");
        final Data app = (Data) getApplication();
        dbHelper = new DatabaseHelper(this);

        Payhistory_TV_bankcardID = findViewById(R.id.Payhistory_TV_bankcardID);
        Payhistory_TV_schoolcardID = findViewById(R.id.Payhistory_TV_schoolcardID);

        Payhistory_LL_lastFive_1 = findViewById(R.id.Payhistory_LL_lastFive_1);
        Payhistory_LL_lastFive_2 = findViewById(R.id.Payhistory_LL_lastFive_2);
        Payhistory_LL_lastFive_3 = findViewById(R.id.Payhistory_LL_lastFive_3);
        Payhistory_LL_lastFive_4 = findViewById(R.id.Payhistory_LL_lastFive_4);
        Payhistory_LL_lastFive_5 = findViewById(R.id.Payhistory_LL_lastFive_5);

        Payhistory_TV_lastFive_1 = findViewById(R.id.Payhistory_TV_lastFive_1);
        Payhistory_TV_lastFive_2 = findViewById(R.id.Payhistory_TV_lastFive_2);
        Payhistory_TV_lastFive_3 = findViewById(R.id.Payhistory_TV_lastFive_3);
        Payhistory_TV_lastFive_4 = findViewById(R.id.Payhistory_TV_lastFive_4);
        Payhistory_TV_lastFive_5 = findViewById(R.id.Payhistory_TV_lastFive_5);


        Payhistory_TV_bankcardID.setText(app.getcurrbankcard());
        Payhistory_TV_schoolcardID.setText(app.getCurrPayid());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("SchoolSpend", null, "SchoolCard=?",
                new String[]{app.getCurrPayid()}, null, null, null);
        int i = 0;
        if (cursor.moveToFirst()) {

            do {

                String itemtime = cursor.getString(1);
                String itemdaytime = cursor.getString(2);
                String itemmoney = cursor.getString(4);
                i++;
                Payhistory_LL_lastFive_1.setVisibility(View.VISIBLE);
                String str = itemdaytime + "        " + itemtime + "                 " + itemmoney;
                Payhistory_TV_lastFive_1.setText(str);
                if (i == 2) {
                    Payhistory_LL_lastFive_2.setVisibility(View.VISIBLE);
                    str = itemdaytime + "        " + itemtime + "                 " + itemmoney;
                    Payhistory_TV_lastFive_2.setText(str);
                }
                if (i == 3) {
                    Payhistory_LL_lastFive_3.setVisibility(View.VISIBLE);
                    str = itemdaytime + "        " + itemtime + "                 " + itemmoney;
                    Payhistory_TV_lastFive_3.setText(str);
                }
                if (i == 4) {
                    Payhistory_LL_lastFive_4.setVisibility(View.VISIBLE);
                    str = itemdaytime + "        " + itemtime + "                 " + itemmoney;
                    Payhistory_TV_lastFive_4.setText(str);
                }
                if (i == 5) {
                    Payhistory_LL_lastFive_5.setVisibility(View.VISIBLE);
                    str = itemdaytime + "        " + itemtime + "                 " + itemmoney;
                    Payhistory_TV_lastFive_5.setText(str);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();


    }
}