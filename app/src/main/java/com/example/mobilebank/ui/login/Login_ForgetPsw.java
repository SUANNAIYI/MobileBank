package com.example.mobilebank.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.mobilebank.R;

import java.util.regex.Pattern;

public class Login_ForgetPsw extends AppCompatActivity {
    private EditText Phone, NewPassword, codeFor;
    private DatabaseHelper helper;
    private Button Forget, getCodeFor;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget_psw);

        init();
    }

    private void init() {
        //控件绑定
        Phone = findViewById(R.id.numberFor);
        NewPassword = findViewById(R.id.newpsw);
        codeFor = findViewById(R.id.codeFor);
        Forget = findViewById(R.id.ForgetFor);
        getCodeFor = findViewById(R.id.getCodeFor);

        helper = new DatabaseHelper(this);

        //按钮触发事件
        Forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetPsw();
            }
        });

        getCodeFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNum = Phone.getText().toString();
                String NewPsw = NewPassword.getText().toString();

                int r = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
                code = String.valueOf(r);

                //判空提醒
                if (PhoneNum.isEmpty() || !Pattern.matches("^1[3-9]\\d{9}$", PhoneNum)) {
                    Toast.makeText(Login_ForgetPsw.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                } else if (NewPsw.isEmpty()) {
                    Toast.makeText(Login_ForgetPsw.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                } else {
                    final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);//通知栏管理器（得到系统服务）
                    String id = "channel_1"; //自定义设置通道ID属性
                    int importance = NotificationManager.IMPORTANCE_HIGH;//通知栏管理重要提示消息声音设定
                    NotificationChannel mChannel = new NotificationChannel(id, "123", importance);//建立通知栏通道类（需要有ID，重要属性）
                    manager.createNotificationChannel(mChannel);//最后在notificationmanager中创建该通知渠道
                    Notification notification = new Notification.Builder(Login_ForgetPsw.this, id)//创建Notification对象。
                            .setContentTitle("中国银行")  //设置通知标题
                            .setSmallIcon(R.mipmap.ic_launcher)//设置通知小图标
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知大图标
                            .setContentText("您正在进行手机注册，手机验证码为" + code)//设置通知内容
                            .setAutoCancel(true)//设置自动删除通知
                            .build();//运行

                    manager.notify((int) System.currentTimeMillis(), notification); //通知栏保留多条通知

                    //按钮设置可用
                    getCodeFor.setEnabled(false);
                    Forget.setEnabled(true);

                    //锁定文本框的值为不可更改
                    Phone.setEnabled(false);
                    NewPassword.setEnabled(false);
                }
            }
        });

    }

    //重置密码
    private void forgetPsw() {
        String PhoneNum = Phone.getText().toString();
        String NewPsw = NewPassword.getText().toString();
        SQLiteDatabase db;

        if (!codeFor.getText().toString().equals(code)) {
            Toast.makeText(Login_ForgetPsw.this, "请输入正确的验证码", Toast.LENGTH_SHORT)
                    .show();
            getCodeFor.setEnabled(true);
            getCodeFor.setText("重新获取验证码");

            //EditText文本框可以更改
            Phone.setEnabled(true);
            NewPassword.setEnabled(true);
        } else //找到对应数据库数据行
        {
            db = helper.getWritableDatabase();
            Cursor cursor = db.query("information", null, "Phone=?",
                    new String[]{PhoneNum}, null, null, null);
            cursor.moveToFirst();
            if (cursor.getCount() == 0) {
                cursor.close();
                AlertDialog.Builder builder = new AlertDialog.Builder(Login_ForgetPsw.this);
                builder.setTitle("该手机号未注册");
                builder.setMessage("是否前往注册界面");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    //转到注册界面
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Login_ForgetPsw.this,
                                Login_RegisterView.class);
                        startActivity((intent));
                        finish();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    //清空电话号码和新密码
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Phone.setText("");
                        NewPassword.setText("");
                        codeFor.setText("");
                    }
                });
                builder.show();
            } else {
                ContentValues values = new ContentValues();
                values.put("Password", NewPsw);
                db.update("information", values, "Phone=?", new String[]{PhoneNum});
                db.close();
                cursor.close();
                Toast.makeText(this, "更改成功，即将转回登陆页面", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    return;
                }
                finish();
            }
        }
    }
}