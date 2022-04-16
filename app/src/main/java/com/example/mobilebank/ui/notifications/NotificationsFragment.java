package com.example.mobilebank.ui.notifications;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilebank.Data;
import com.example.mobilebank.R;
import com.example.mobilebank.databinding.FragmentNotificationsBinding;
import com.example.mobilebank.ui.login.DatabaseHelper;
import com.example.mobilebank.ui.login.Login_MainActivity;
import com.example.mobilebank.ui.notifications.Account.Addcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private DatabaseHelper dbHelper;
    private TextView showmoney;
    private Double summoney = 0.0;
    private Double money = 0.0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //绑定按键
        Button  addcard1= getActivity().findViewById(R.id.addcard);
        Button addin = getActivity().findViewById(R.id.addin);
        Button btn_quit = getActivity().findViewById(R.id.quit);
        listView = getActivity().findViewById(R.id.Bankcard_MLV_bancardList);
        TextView showmoney = getActivity().findViewById(R.id.showmoney);
        TextView money = getActivity().findViewById(R.id.money);
        dbHelper = new DatabaseHelper(getActivity());

        //点击添加按钮跳转增加银行卡界面
        addcard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Addcard.class);
                startActivity(intent);
            }
        });

        //退出登录按钮
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login_MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //在listview中添加银行卡信息
        list = new ArrayList<>();
        adapter = new SimpleAdapter(getActivity(), a(), R.layout.item3, new String[]{"图片", "文字1", "文字2", "文字3"},
                new int[]{R.id.itemImageView1, R.id.itemTextView1, R.id.itemTextView2, R.id.money});
        listView.setAdapter(adapter);
        showmoney.setText(summoney.toString());

        //长按银行卡解绑银行卡
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("是否解绑银行卡");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String cardtodelete;
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //搜索选中银行卡数据库中手机号
                        Cursor cursor = db.query("Card", null, "Phone=?",
                                new String[]{((Data) getActivity().getApplication()).getcurrentuser()},
                                null, null, null);
                        cursor.moveToFirst();
                        {
                            cursor.move(position);
                            cardtodelete = cursor.getString(2);
                        }
                        cursor.close();
                        db.delete("Card", "Cardid=?", new String[]{cardtodelete});//删除银行卡数据库信息
                    }
                });

                //按否键无操作
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    //显示总金额
    private List<Map<String, Object>> a() {
        ArrayList<Map<String, Object>> mapset = new ArrayList<Map<String, Object>>();
        dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //获取当前用户的手机号
        Cursor cursor = db.query("Card", null, "Phone=?",
                new String[]{((Data) getActivity().getApplication()).getcurrentuser()}, null, null, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String cardname = cursor.getString(1);
                String cardnum = cursor.getString(2);
                summoney += cursor.getDouble(3);//余额累加获取总金额
                String money = cursor.getString(3);//银行卡余额
                i++;//记录添加银行卡数量
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("图片", R.drawable.bankcard);
                map1.put("文字1", cardnum);
                map1.put("文字2", cardname);
                map1.put("文字3", "余额：" + money + "元");
                mapset.add(map1);
            } while (cursor.moveToNext());
        }
        cursor.close();

        //添加多张银行卡信息
        for (int j = 0; j < i; j++) {
            list.add(mapset.get(j));
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}