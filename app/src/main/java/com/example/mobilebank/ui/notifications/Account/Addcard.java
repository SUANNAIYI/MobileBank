package com.example.mobilebank.ui.notifications.Account;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.ui.login.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Addcard extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText Addcard_ET_usercard;
    private Spinner Addcard_SP_cardType;
    private Button Addcard_BTN_addin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        init();
    }

    private void init() {
        //绑定按键
        Addcard_ET_usercard = findViewById(R.id.Addcard_ET_usercard);
        Addcard_SP_cardType = findViewById(R.id.Addcard_SP_cardType);
        Addcard_BTN_addin = findViewById(R.id.Addcard_BTN_addin);
        dbHelper = new DatabaseHelper(this);
        //对添加按钮添加点击事件
        Addcard_BTN_addin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addin();
            }
        });
    }

    private void addin() {
        String phone;
        String cardnum;
        SQLiteDatabase db;
        String type;
        final Data app = (Data) getApplication();
        phone = app.getcurrentuser();//获取用户输入手机号
        cardnum = Addcard_ET_usercard.getText().toString();//获取用户输入卡号
        type = (String) Addcard_SP_cardType.getSelectedItem();
        Pattern p = Pattern.compile("\\d{18}$");//正则表达式判断银行卡为18位
        Matcher m = p.matcher(cardnum);
        if (m.matches()) {
            //判断银行卡是否已经添加,未添加则写入数据库
            if (SearchDB(cardnum)) {
                db = dbHelper.getWritableDatabase();
                ContentValues values;
                values = new ContentValues();
                //写入数据库
                values.put("Phone", phone);//手机号
                values.put("Cardname", "中国银行" + type);//卡名
                values.put("Cardid", cardnum);//卡号
                values.put("Balance", 0);//余额
                db.insert("Card", null, values);
                db.close();
                //提示添加成功
                Toast.makeText(this, "添加成功！", Toast.LENGTH_LONG).show();
                Back();
            }
            //若数据库中银行卡已存在则报错
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Addcard.this);
                builder.setTitle("该银行卡号已添加！");
                builder.setMessage("是否返回我的界面");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Back();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        }
        //银行卡号不符合规范报错
        else {
            Toast.makeText(this, "请输入正确银行卡号", Toast.LENGTH_SHORT).show();
        }
    }

    //搜索数据库银行卡表中卡号（关键字）
    private boolean SearchDB(String cardnum) {
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("Card", null, "Crdid=?",
                new String[]{cardnum}, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    private void Back() {
        this.finish();
    }
}