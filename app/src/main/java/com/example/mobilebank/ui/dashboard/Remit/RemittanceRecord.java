package com.example.mobilebank.ui.dashboard.Remit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;

public class RemittanceRecord extends AppCompatActivity {
    private ListView Remittance_MLV_record;
    private String currentName;

    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remittance_record);
        setTitle("转账记录");
        final Data app = (Data) getApplication();

        Remittance_MLV_record = findViewById(R.id.Remittance_MLV_record);
        SQLiteDatabase mydb = dbHelper.getWritableDatabase();

        Cursor cursor = mydb.query("information", null, "Phone=?", new String[]{app.getcurrentuser()}, null, null, null);
        cursor.moveToFirst();
        currentName = cursor.getString(2);

        cursor = mydb.query("transfer", new String[]{"_id", "PayCardID", "Money", "Receiver", "GetCardID", "Attachment", "Sender", "Date"}, "Sender=? OR Receiver=?", new String[]{currentName, currentName}, null, null, null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                //转账方
                if (currentName.equals(cursor.getString(6))) {
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(RemittanceRecord.this, R.layout.item5, cursor, new String[]{"PayCardID", "Money", "Receiver", "GetCardID", "Attachment", "Sender", "Date"}, new int[]{R.id.tv_payaccount, R.id.tv_paymoney, R.id.tv_receiver, R.id.tv_getaccount, R.id.tv_attachment, R.id.tv_getsender, R.id.tv_getdate});
                    Remittance_MLV_record.setAdapter(adapter);
                }
                //收款方
                if (currentName.equals(cursor.getString(3))) {
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(RemittanceRecord.this, R.layout.item5, cursor, new String[]{"PayCardID", "Money", "Receiver", "GetCardID", "Attachment", "Sender", "Date"}, new int[]{R.id.tv_payaccount, R.id.tv_paymoney, R.id.tv_receiver, R.id.tv_getaccount, R.id.tv_attachment, R.id.tv_getsender, R.id.tv_getdate});
                    Remittance_MLV_record.setAdapter(adapter);
                }

            } while (cursor.moveToNext());
        }


    }
}