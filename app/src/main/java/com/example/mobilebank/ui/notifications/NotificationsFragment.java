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
        //????????????
        Button  addcard1= getActivity().findViewById(R.id.addcard);
        Button addin = getActivity().findViewById(R.id.addin);
        Button btn_quit = getActivity().findViewById(R.id.quit);
        listView = getActivity().findViewById(R.id.Bankcard_MLV_bancardList);
        TextView showmoney = getActivity().findViewById(R.id.showmoney);
        TextView money = getActivity().findViewById(R.id.money);
        dbHelper = new DatabaseHelper(getActivity());

        //?????????????????????????????????????????????
        addcard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Addcard.class);
                startActivity(intent);
            }
        });

        //??????????????????
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login_MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //???listview????????????????????????
        list = new ArrayList<>();
        adapter = new SimpleAdapter(getActivity(), a(), R.layout.item3, new String[]{"??????", "??????1", "??????2", "??????3"},
                new int[]{R.id.itemImageView1, R.id.itemTextView1, R.id.itemTextView2, R.id.money});
        listView.setAdapter(adapter);
        showmoney.setText(summoney.toString());

        //??????????????????????????????
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("??????");
                builder.setMessage("?????????????????????");
                builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String cardtodelete;
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //??????????????????????????????????????????
                        Cursor cursor = db.query("Card", null, "Phone=?",
                                new String[]{((Data) getActivity().getApplication()).getcurrentuser()},
                                null, null, null);
                        cursor.moveToFirst();
                        {
                            cursor.move(position);
                            cardtodelete = cursor.getString(2);
                        }
                        cursor.close();
                        db.delete("Card", "Cardid=?", new String[]{cardtodelete});//??????????????????????????????
                    }
                });

                //??????????????????
                builder.setNegativeButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    //???????????????
    private List<Map<String, Object>> a() {
        ArrayList<Map<String, Object>> mapset = new ArrayList<Map<String, Object>>();
        dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //??????????????????????????????
        Cursor cursor = db.query("Card", null, "Phone=?",
                new String[]{((Data) getActivity().getApplication()).getcurrentuser()}, null, null, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String cardname = cursor.getString(1);
                String cardnum = cursor.getString(2);
                summoney += cursor.getDouble(3);//???????????????????????????
                String money = cursor.getString(3);//???????????????
                i++;//???????????????????????????
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("??????", R.drawable.bankcard);
                map1.put("??????1", cardnum);
                map1.put("??????2", cardname);
                map1.put("??????3", "?????????" + money + "???");
                mapset.add(map1);
            } while (cursor.moveToNext());
        }
        cursor.close();

        //???????????????????????????
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