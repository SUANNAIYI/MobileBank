package com.example.mobilebank.ui.dashboard.PayLife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;

public class Schoolfare extends AppCompatActivity {

    private Button Schoolfare_BTN_check;
    private DatabaseHelper dbHelper;
    private EditText Schoolfare_ET_schoolcardID;
    private EditText Schoolfare_ET_payYear;
    private Double fee = 0.0;
    private TextView Schoolfare_TV_showOwe;
    private Button Schoolfare_BTN_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolfare);
        setTitle("缴纳学费");
        Schoolfare_BTN_check = findViewById(R.id.Schoolfare_BTN_check);
        Schoolfare_ET_schoolcardID = findViewById(R.id.Schoolfare_ET_schoolcardID);
        Schoolfare_ET_payYear = findViewById(R.id.Schoolfare_ET_payYear);
        Schoolfare_TV_showOwe = findViewById(R.id.Schoolfare_TV_showOwe);
        Schoolfare_BTN_next = findViewById(R.id.Schoolfare_BTN_next);
        dbHelper = new DatabaseHelper(this);
        final Data app = (Data) getApplication();

        Schoolfare_BTN_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = String.valueOf(Schoolfare_ET_schoolcardID.getText());
                String year = String.valueOf(Schoolfare_ET_payYear.getText());
                app.setCurrPayid(username);
                app.setyear(year);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("schoolfare", null, "Studentnum=? AND Year=?",
                        new String[]{username, year}, null, null, null);

                if (cursor.getCount() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Schoolfare.this);
                    builder.setTitle("提示");
                    builder.setMessage("找不到该记录");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                } else {
                    cursor.moveToFirst();
                    {
                        fee = cursor.getDouble(2);
                    }
                    cursor.close();

                    if (fee <= 0.001) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Schoolfare.this);
                        builder.setTitle("提示");
                        builder.setMessage("当前学号没有欠费");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    } else {
                        Schoolfare_TV_showOwe.setVisibility(View.VISIBLE);
                        Schoolfare_TV_showOwe.setText("当前欠费金额：" + String.valueOf(fee));
                        Schoolfare_BTN_next.setVisibility(View.VISIBLE);
                        app.setfee(fee);

                    }
                }
            }
        });

        Schoolfare_BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Schoolfare.this, Cardfee.class);
                startActivity(intent);

            }
        });
    }
}