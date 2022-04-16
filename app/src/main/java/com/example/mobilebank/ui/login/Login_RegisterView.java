package com.example.mobilebank.ui.login;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilebank.R;
import com.example.mobilebank.ui.dashboard.PayLife.CardTopUp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_RegisterView extends AppCompatActivity {
    private EditText numberReg, pswReg, nameReg, codeReg;
    private Button RegisterReg, getCodeReg;
    private DatabaseHelper helper;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_view);

        init();
    }


    private void init() {
        //控件绑定
        numberReg = findViewById(R.id.numberReg);
        pswReg = findViewById(R.id.pswReg);
        nameReg = findViewById(R.id.nameReg);
        codeReg = findViewById(R.id.codeReg);

        RegisterReg = findViewById(R.id.RegisterReg);
        getCodeReg = findViewById(R.id.getCodeReg);

        helper = new DatabaseHelper(this);

        //两个按钮的点击事件绑定
        RegisterReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        getCodeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCode();
            }
        });
    }

    private void GetCode() {
        String phone;
        phone = numberReg.getText().toString();

        //随机生成四位验证码
        int r = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        code = String.valueOf(r);

        if (Pattern.matches("^1[3-9]\\d{9}$", phone)) //手机号码的正则匹配
        {
            if (SearchDB(phone)) {
                final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);//通知栏管理器（得到系统服务）
                String id = "channel_1"; //自定义设置通道ID属性
                String description = "123";//自定义设置通道描述属性
                int importance = NotificationManager.IMPORTANCE_HIGH;//通知栏管理重要提示消息声音设定
                NotificationChannel mChannel = new NotificationChannel(id, "123", importance);//建立通知栏通道类（需要有ID，重要属性）
                manager.createNotificationChannel(mChannel);//最后在notificationmanager中创建该通知渠道
                Notification notification = new Notification.Builder(Login_RegisterView.this, id)//创建Notification对象。
                        .setContentTitle("中国银行")  //设置通知标题
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知大图标
                        .setContentText("您正在进行手机注册，手机验证码为" + code)//设置通知内容
                        .setAutoCancel(true)//设置自动删除通知
                        .build();//运行

                manager.notify((int) System.currentTimeMillis(), notification); //通知栏保留多条通知

                //按钮设置为不可点（不可重复获得验证码）
                getCodeReg.setEnabled(false);
                //注册按钮可以点击
                RegisterReg.setEnabled(true);

                //防止信息被更改（获取验证码后的信息和获取前不一直问题）
                //控件设置为不可更改
                numberReg.setEnabled(false);
                nameReg.setEnabled(false);
                pswReg.setEnabled(false);
            } else //手机号已存在数据库中
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login_RegisterView.this);
                builder.setTitle("该手机号已被注册");
                builder.setMessage("是否返回登陆界面");
                //点击是后，返回主页面登录
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Back();
                    }
                });

                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        numberReg.setText("");
                        pswReg.setText("");
                        codeReg.setText("");
                        nameReg.setText("");
                    }
                });

                builder.show();
            }
        } else  //手机号正则错误
        {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
        }
    }

    private void Register() {
        String password;
        String phone;
        String name;
        String codeIn;
        SQLiteDatabase db;
        ContentValues values;

        //注册获取验证码输入
        codeIn = codeReg.getText().toString();

        //未输入验证码或验证码不匹配
        if (codeIn == null || codeIn.isEmpty() || !codeIn.equals(code)) {
            Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            getCodeReg.setEnabled(true);
            RegisterReg.setEnabled(false);
            //输错验证码则设置可以更改信息，可以重新获取验证码
            numberReg.setEnabled(true);
            nameReg.setEnabled(true);
            pswReg.setEnabled(true);

            getCodeReg.setText("重新获取验证码");
        } else  //获取值 数据库新建表项目写入
        {
            password = pswReg.getText().toString();
            name = nameReg.getText().toString();
            phone = numberReg.getText().toString();
            db = helper.getWritableDatabase();
            values = new ContentValues();
            values.put("Phone", phone);
            values.put("Password", password);
            values.put("Name", name);
            db.insert("information", null, values);
            values.clear();
            db.insert("User", null, values);
            values.clear();

            Toast.makeText(this, "注册成功！ 即将转至登陆界面", Toast.LENGTH_LONG).show();
            db.close();
            try //休眠3秒等待Toast显示完成后返回主页面登录
            {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return;
            }
            finish();
        }
    }

    //查询电话号码是否已经在数据库中
    private boolean SearchDB(String phone) {
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = helper.getReadableDatabase();
        //游标查找
        Cursor cursor = sqLiteDatabase.query("information", null, "Phone=?",
                new String[]{phone}, null, null, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //返回主页面
    private void Back() {
        Intent intent = new Intent(Login_RegisterView.this, Login_MainActivity.class);
        startActivity((intent));
        finish();
    }
}