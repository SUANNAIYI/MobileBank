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

public class Electricity extends AppCompatActivity {

    private Button Electricity_BTN_check;
    private DatabaseHelper dbHelper;
    private EditText Electricity_ET_usernameEdit;
    private Double fee = 0.0;
    private TextView Electricity_TV_showOwe;
    private Button Electricity_BTN_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        setTitle("缴纳电费");
        Electricity_BTN_check = findViewById(R.id.Electricity_BTN_check);
        Electricity_ET_usernameEdit = findViewById(R.id.Electricity_ET_usernameEdit);
        Electricity_TV_showOwe = findViewById(R.id.Electricity_TV_showOwe);
        Electricity_BTN_next = findViewById(R.id.Electricity_BTN_next);
        dbHelper = new DatabaseHelper(this);
        final Data app = (Data) getApplication();

        Electricity_BTN_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = String.valueOf(Electricity_ET_usernameEdit.getText());
                app.setCurrPayid(username);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("electricityfee", null, "Electrnum=?",
                        new String[]{username}, null, null, null);

                if (cursor.getCount() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Electricity.this);
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
                        fee = cursor.getDouble(1);
                    }
                    cursor.close();

                    if (fee <= 0.001) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Electricity.this);
                        builder.setTitle("提示");
                        builder.setMessage("当前用户没有欠费");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    } else {
                        Electricity_TV_showOwe.setVisibility(View.VISIBLE);
                        Electricity_TV_showOwe.setText("当前欠费金额：" + String.valueOf(fee));
                        Electricity_BTN_next.setVisibility(View.VISIBLE);
                        app.setfee(fee);

                    }
                }


            }
        });

        Electricity_BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Electricity.this, Cardfee.class);
                startActivity(intent);

            }
        });

    }

}