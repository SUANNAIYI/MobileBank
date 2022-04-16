package com.example.mobilebank.ui.dashboard.PayLife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bankcard extends AppCompatActivity {

    private ListView Bankcard_MLV_bancardList;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private DatabaseHelper dbHelper;
    private String currentPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
        setTitle("选择账户");

        final Data app = (Data) getApplication();
        Bankcard_MLV_bancardList = findViewById(R.id.Bankcard_MLV_bancardList);
        dbHelper = new DatabaseHelper(this);
        currentPhone = app.getcurrentuser();
        list = new ArrayList<>();
        adapter = new SimpleAdapter(this, a(), R.layout.item2, new String[]{"图片", "文字1", "文字2"}, new int[]{R.id.itemImageView1, R.id.itemTextView1, R.id.itemTextView2});
        Bankcard_MLV_bancardList.setAdapter(adapter);

        Bankcard_MLV_bancardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //设置返回的数据
                Intent intent = new Intent();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Card", null, "Phone=?",
                        new String[]{currentPhone}, null, null, null);
                cursor.moveToFirst();
                {
                    cursor.move(i);
                    String cardname = cursor.getString(1);
                    String cardnum = cursor.getString(2);
                    app.setcurrbankcard(cardnum);
                    intent.putExtra("data", cardnum + " >");
                }
                cursor.close();
                setResult(3, intent);
                //关闭当前activity
                finish();

            }
        });
    }

    private List<Map<String, Object>> a() {
        ArrayList<Map<String, Object>> mapset = new ArrayList<Map<String, Object>>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Card", null, "Phone=?",
                new String[]{currentPhone}, null, null, null);
        int i = 0;
        if (cursor.moveToFirst()) {

            do {
                String cardname = cursor.getString(1);
                String cardnum = cursor.getString(2);
                i++;
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("图片", R.drawable.bankcard);
                map1.put("文字1", cardname);
                map1.put("文字2", cardnum);
                mapset.add(map1);
            } while (cursor.moveToNext());
        }
        cursor.close();

        for (int j = 0; j < i; j++) {
            list.add(mapset.get(j));
        }
        return list;
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra("data", "请选择 >");//edtOne.getText().toString().trim()

        setResult(3, intent);
        //关闭当前activity
        finish();
        super.onBackPressed();
    }
}