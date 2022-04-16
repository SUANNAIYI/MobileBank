package com.example.mobilebank.ui.dashboard.BillingRecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;
import com.example.mobilebank.ui.login.Login_MainActivity;
import com.example.mobilebank.ui.login.Login_RegisterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Record extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private ListView listView;

    private Button Record_BTN_startTime;
    private Button Record_BTN_endTime;
    private TextView Record_TV_startTime;
    private TextView Record_TV_endTime;

    private Spinner Record_SP_cardType;
    private Spinner Record_SP_billType;
    private EditText Record_ET_minMoney;
    private EditText Record_ET_maxMoney;

    private Button Record_BTN_reset;
    private Button Record_BTN_confirm;

    private String startTime;
    private String endTime;
    private double minMoney;
    private double maxMoney;
    private String cardType;
    private String billType;
    public int press_flag = 0;
    public boolean initial_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setTitle("账单记录");

        Record_TV_startTime = findViewById(R.id.Record_TV_startTime);
        Record_TV_endTime = findViewById(R.id.Record_TV_endTime);
        Record_BTN_startTime = findViewById(R.id.Record_BTN_startTime);
        Record_BTN_endTime = findViewById(R.id.Record_BTN_endTime);

        Record_SP_cardType = findViewById(R.id.Record_SP_cardType);
        Record_SP_billType = findViewById(R.id.Record_SP_billType);
        Record_ET_minMoney = findViewById(R.id.Record_ET_minMoney);
        Record_ET_maxMoney = findViewById(R.id.Record_ET_maxMoney);

        Record_BTN_reset = findViewById(R.id.Record_BTN_reset);
        Record_BTN_confirm = findViewById(R.id.Record_BTN_confirm);


        Calendar ca = Calendar.getInstance();
        int mYear = ca.get(Calendar.YEAR);
        int mMonth = ca.get(Calendar.MONTH);
        int mDay = ca.get(Calendar.DAY_OF_MONTH);

        listView = findViewById(R.id.Record_MLV_recordList);
        dbHelper = new DatabaseHelper(this);

        Record_BTN_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Record.this, onDateSetListener, mYear, mMonth, mDay).show();
                press_flag = 1;
            }
        });

        Record_BTN_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Record.this, onDateSetListener, mYear, mMonth, mDay).show();
                press_flag = 2;
            }
        });

        Record_BTN_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Record_TV_startTime.getText().length() == 0 || Record_TV_endTime.getText().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Record.this);
                    builder.setTitle("警告");
                    builder.setMessage("请输入确定的时间范围");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                } else if (Record_ET_minMoney.getText().length() == 0 || Record_ET_maxMoney.getText().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Record.this);
                    builder.setTitle("警告");
                    builder.setMessage("请输入确定的金额范围");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                } else {
                    minMoney = Double.parseDouble(Record_ET_minMoney.getText().toString());
                    maxMoney = Double.parseDouble(Record_ET_maxMoney.getText().toString());
                    cardType = (String) Record_SP_cardType.getSelectedItem();
                    billType = (String) Record_SP_billType.getSelectedItem();
                    list = new ArrayList<>();
                    initial_flag = false;
                    adapter = new SimpleAdapter(
                            Record.this,
                            a(),
                            R.layout.item4,
                            new String[]{"文字1", "文字2", "文字3", "文字4"},
                            new int[]{R.id.itemTextView1, R.id.itemTextView2, R.id.itemTextView3, R.id.itemTextView4});
                    listView.setAdapter(adapter);
                }
            }
        });
        Record_BTN_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Record_TV_startTime.setText("");
                Record_TV_endTime.setText("");
                Record_SP_cardType.setSelection(0, true);
                Record_SP_billType.setSelection(0, true);
                Record_ET_minMoney.setText("");
                Record_ET_maxMoney.setText("");
                list = new ArrayList<>();
                initial_flag = true;
                adapter = new SimpleAdapter(
                        Record.this,
                        a(),
                        R.layout.item4,
                        new String[]{"文字1", "文字2", "文字3", "文字4"},
                        new int[]{R.id.itemTextView1, R.id.itemTextView2, R.id.itemTextView3, R.id.itemTextView4});
                listView.setAdapter(adapter);
            }
        });

        list = new ArrayList<>();
        adapter = new SimpleAdapter(
                this,
                a(),
                R.layout.item4,
                new String[]{"文字1", "文字2", "文字3", "文字4"},
                new int[]{R.id.itemTextView1, R.id.itemTextView2, R.id.itemTextView3, R.id.itemTextView4});
        listView.setAdapter(adapter);
    }

    private List<Map<String, Object>> a() {
        final Data app = (Data) getApplication();
        ArrayList<Map<String, Object>> mapset = new ArrayList<Map<String, Object>>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        if (initial_flag) {
            cursor = db.query("bill", null, "Phone=?",
                    new String[]{app.getcurrentuser()}, null, null, null);
        } else if (cardType.equals("全部")) {
            if (billType.equals("全部")) {
                cursor = db.query("bill", null, "Phone=? AND Money>=? AND Money<=? AND Date between ? AND ?",
                        new String[]{app.getcurrentuser(), String.valueOf(minMoney), String.valueOf(maxMoney), startTime, endTime}, null, null, null);
            } else {
                cursor = db.query("bill", null, "Phone=? AND BillAttach=? AND Money>=? AND Money<=? AND Date between ? AND ?",
                        new String[]{app.getcurrentuser(), billType, String.valueOf(minMoney), String.valueOf(maxMoney), startTime, endTime}, null, null, null);
            }
        } else {
            if (billType.equals("全部")) {
                cursor = db.query("bill", null, "Phone=? AND cardType=? AND Money>=? AND Money<=? AND Date between ? AND ?",
                        new String[]{app.getcurrentuser(), "中国银行" + cardType, String.valueOf(minMoney), String.valueOf(maxMoney), startTime, endTime}, null, null, null);
            } else {
                cursor = db.query("bill", null, "Phone=? AND BillAttach=?  AND cardType=? AND Money>=? AND Money<=? AND Date between ? AND ?",
                        new String[]{app.getcurrentuser(), billType, "中国银行" + cardType, String.valueOf(minMoney), String.valueOf(maxMoney), startTime, endTime}, null, null, null);
            }
        }

        int i = 0;

        if (cursor.moveToFirst()) {
            do {

                String billType = cursor.getString(4);
                String cardID = cursor.getString(5);
                String date = cursor.getString(3);
                String money = cursor.getString(2);
                String cardType = cursor.getString(6);
                cardType = cardType.substring(cardType.length() - 3, cardType.length());


                i++;
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("文字1", billType);
                map1.put("文字2",
                        cardType
                                + "(" +
                                cardID.substring(cardID.length() - 4, cardID.length())
                                + ")");
                map1.put("文字3", date);
                if (cursor.getString(7).equals("收入")) {
                    map1.put("文字4", "+" + money);
                } else {
                    map1.put("文字4", "-" + money);
                }

                mapset.add(map1);

            } while (cursor.moveToNext());
        }
        cursor.close();

        for (int j = 0; j < i; j++) {
            list.add(mapset.get(j));
        }
        return list;
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            String days;
            String formatday;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                    formatday = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                    formatday = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }
            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                    formatday = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                    formatday = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }
            }
            if (press_flag == 1) {
                Record_TV_startTime.setText(days);
                startTime = formatday + " 00:00:00";
                Log.d("day", startTime);
            }
            if (press_flag == 2) {
                Record_TV_endTime.setText(days);
                endTime = formatday + " 23:59:59";
            }
        }
    };
}