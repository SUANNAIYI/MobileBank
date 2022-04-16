package com.example.mobilebank.ui.dashboard.PayLife;


import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilebank.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 类：PhoneCode
 * 作者： qxc
 * 日期：2018/3/14.
 */
public class PhoneCode extends RelativeLayout {
    private Context context;
    private TextView Phonecode_TV_code_1;
    private TextView Phonecode_TV_code_2;
    private TextView Phonecode_TV_code_3;
    private TextView Phonecode_TV_code_4;
    private View Phonecode_V_code_1;
    private View Phonecode_V_code_2;
    private View Phonecode_V_code_3;
    private View Phonecode_V_code_4;
    private EditText Phonecode_ET_code;
    private List<String> codes = new ArrayList<>();
    private InputMethodManager imm;

    public PhoneCode(Context context) {
        super(context);
        this.context = context;
        loadView();
    }

    public PhoneCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadView();
    }

    private void loadView() {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.phone_code, this);
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        Phonecode_TV_code_1 = (TextView) view.findViewById(R.id.Phonecode_TV_code_1);
        Phonecode_TV_code_2 = (TextView) view.findViewById(R.id.Phonecode_TV_code_2);
        Phonecode_TV_code_3 = (TextView) view.findViewById(R.id.Phonecode_TV_code_3);
        Phonecode_TV_code_4 = (TextView) view.findViewById(R.id.Phonecode_TV_code_4);
        Phonecode_ET_code = (EditText) view.findViewById(R.id.Phonecode_ET_code);
        Phonecode_V_code_1 = view.findViewById(R.id.Phonecode_V_code_1);
        Phonecode_V_code_2 = view.findViewById(R.id.Phonecode_V_code_2);
        Phonecode_V_code_3 = view.findViewById(R.id.Phonecode_V_code_3);
        Phonecode_V_code_4 = view.findViewById(R.id.Phonecode_V_code_4);
    }

    private void initEvent() {
        //验证码输入
        Phonecode_ET_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    Phonecode_ET_code.setText("");
                    if (codes.size() < 4) {
                        codes.add(editable.toString());
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        Phonecode_ET_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && codes.size() > 0) {
                    codes.remove(codes.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        String code1 = "";
        String code2 = "";
        String code3 = "";
        String code4 = "";
        if (codes.size() >= 1) {
            code1 = codes.get(0);
        }
        if (codes.size() >= 2) {
            code2 = codes.get(1);
        }
        if (codes.size() >= 3) {
            code3 = codes.get(2);
        }
        if (codes.size() >= 4) {
            code4 = codes.get(3);
        }
        Phonecode_TV_code_1.setText(code1);
        Phonecode_TV_code_2.setText(code2);
        Phonecode_TV_code_3.setText(code3);
        Phonecode_TV_code_4.setText(code4);

        setColor();//设置高亮颜色
        callBack();//回调
    }

    /**
     * 设置高亮颜色
     */
    private void setColor() {
        int color_default = Color.parseColor("#999999");
        int color_focus = Color.parseColor("#3F8EED");
        Phonecode_V_code_1.setBackgroundColor(color_default);
        Phonecode_V_code_2.setBackgroundColor(color_default);
        Phonecode_V_code_3.setBackgroundColor(color_default);
        Phonecode_V_code_4.setBackgroundColor(color_default);
        if (codes.size() == 0) {
            Phonecode_V_code_1.setBackgroundColor(color_focus);
        }
        if (codes.size() == 1) {
            Phonecode_V_code_2.setBackgroundColor(color_focus);
        }
        if (codes.size() == 2) {
            Phonecode_V_code_3.setBackgroundColor(color_focus);
        }
        if (codes.size() >= 3) {
            Phonecode_V_code_4.setBackgroundColor(color_focus);
        }
    }

    /**
     * 回调
     */
    private void callBack() {
        if (onInputListener == null) {
            return;
        }
        if (codes.size() == 4) {
            onInputListener.onSucess(getPhoneCode());
        } else {
            onInputListener.onInput();
        }
    }

    //定义回调
    public interface OnInputListener {
        void onSucess(String code);

        void onInput();
    }

    private OnInputListener onInputListener;

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    /**
     * 显示键盘
     */
    public void showSoftInput() {
        //显示软键盘
        if (imm != null && Phonecode_ET_code != null) {
            Phonecode_ET_code.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(Phonecode_ET_code, 0);
                }
            }, 200);
        }
    }

    /**
     * 获得手机号验证码
     *
     * @return 验证码
     */
    public String getPhoneCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }
}