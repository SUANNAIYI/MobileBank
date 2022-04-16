package com.example.mobilebank.ui.dashboard.PayLife;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;

public class Balance extends AppCompatActivity {

    private TextView Balance_TV_bankcardID;
    private TextView Balance_TV_schoolcardID;
    private TextView Balance_TV_schoolcardBalance;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        setTitle("校园卡");

        final Data app = (Data) getApplication();
        dbHelper = new DatabaseHelper(this);

        Balance_TV_bankcardID = findViewById(R.id.Balance_TV_bankcardID);
        Balance_TV_schoolcardID = findViewById(R.id.Balance_TV_schoolcardID);
        Balance_TV_schoolcardBalance = findViewById(R.id.Balance_TV_schoolcardBalance);

        Balance_TV_bankcardID.setText(app.getcurrbankcard());
        Balance_TV_schoolcardID.setText(app.getCurrPayid());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Schoolbalance", null, "SchoolCard=?",
                new String[]{app.getCurrPayid()}, null, null, null);
        cursor.moveToFirst();
        {
            Balance_TV_schoolcardBalance.setText(String.valueOf(cursor.getDouble(1)));
        }
        cursor.close();
    }
}