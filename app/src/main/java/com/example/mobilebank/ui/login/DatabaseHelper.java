package com.example.mobilebank.ui.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String CreateCard = "create table Card("
            + "Phone VARCHAR(20) ,"
            + "Cardname VARCHAR(30),"
            + "Cardid text PRIMARY KEY, "
            + "Balance DOUBLE) ";

    private static final String CreateSchoolcard = "create table School("
            + "SchoolCard VARCHAR(20) PRIMARY KEY,"
            + "Phone VARCHAR(20)) ";

    private static final String Createbalance = "create table Schoolbalance("
            + "SchoolCard VARCHAR(20) PRIMARY KEY,"
            + "money DOUBLE) ";

    private static final String Createspend = "create table SchoolSpend("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "TIME VARCHAR(20),"
            + "DAYTIME VARCHAR(20),"
            + "SchoolCard VARCHAR(20),"
            + "Money VARCHAR(20)) ";

    private static final String Createwaterfee = "create table Waterfee("
            + "Waternum VARCHAR(20) PRIMARY KEY,"
            + "Watertopay DOUBLE) ";

    private static final String Createlectrfee = "create table electricityfee("
            + "Electrnum VARCHAR(20) PRIMARY KEY,"
            + "Electrtopay DOUBLE) ";

    private static final String Createschoolfare = "create table schoolfare("
            + "Studentnum VARCHAR(20),"
            + "Year VARCHAR(20),"
            + "Fare DOUBLE)";

    private static final String Createbill = "create table bill("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "Phone VARCHAR(20),"
            + "Money DOUBLE,"
            + "Date DATETIME,"
            + "billType VARCHAR(20),"
            + "CardID VARCHAR(20),"
            + "cardType VARCHAR(20) ,"
            + "BillAttach VARCHAR(10))";

    private static final String Createtransfer = "create table transfer("
            + "_id VARCHAR(20),"
            + "PayCardID VARCHAR(20)PRIMARY KEY,"
            + "Money DOUBLE,"
            + "Receiver VARCHAR(20) ,"
            + "GetCardID VARCHAR(20),"
            + "Attachment VARCHAR(20),"
            + "Sender VARCHAR(20),"
            + "Date DATETIME) ";

    public DatabaseHelper(Context context) {
        super(context, "info.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //?????????????????????
        sqLiteDatabase.execSQL("CREATE TABLE information(" +
                "Phone VARCHAR(20) PRIMARY KEY, Password VARCHAR(20),Name VARCHAR(20))");
        //??????????????????
        sqLiteDatabase.execSQL(CreateCard);
        //??????????????????????????????
        sqLiteDatabase.execSQL(CreateSchoolcard);
        //?????????????????????
        sqLiteDatabase.execSQL(Createbalance);
        //??????????????????
        sqLiteDatabase.execSQL(Createspend);
        //????????????????????????
        sqLiteDatabase.execSQL(Createwaterfee);
        //????????????????????????
        sqLiteDatabase.execSQL(Createlectrfee);
        //????????????????????????
        sqLiteDatabase.execSQL(Createschoolfare);
        //??????????????????
        sqLiteDatabase.execSQL(Createbill);
        //??????????????????
        sqLiteDatabase.execSQL(Createtransfer);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
