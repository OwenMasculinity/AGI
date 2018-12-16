package com.example.asus.OfflineMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 张凌霄 on 2016/11/6.
 */

public class UserLoginActivity extends Activity {

    AutoCompleteTextView cardNumAuto;
    EditText passwordET;
    Button logBT;
    TextView WebSetting;

    CheckBox savePasswordCB;
    SharedPreferences sp;
    String cardNumStr;
    String passwordStr;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        cardNumAuto = (AutoCompleteTextView) findViewById(R.id.cardNumAuto);
        passwordET = (EditText) findViewById(R.id.passwordET);
        logBT = (Button) findViewById(R.id.logBT);
        WebSetting=(TextView)findViewById(R.id.web);

        sp = this.getSharedPreferences("passwordFile", MODE_PRIVATE);
        savePasswordCB = (CheckBox) findViewById(R.id.savePasswordCB);
        savePasswordCB.setChecked(true);// 默认为记住密码
        cardNumAuto.setThreshold(1);// 输入1个字母就开始自动提示
        passwordET.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        // 隐藏密码为InputType.TYPE_TEXT_VARIATION_PASSWORD，也就是0x81
        // 显示密码为InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD，也就是0x91

        cardNumAuto.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                String[] allUserName = new String[sp.getAll().size()];// sp.getAll().size()返回的是有多少个键值对
                allUserName = sp.getAll().keySet().toArray(new String[0]);
                // sp.getAll()返回一张hash map
                // keySet()得到的是a set of the keys.
                // hash map是由key-value组成的

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        UserLoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        allUserName);

                cardNumAuto.setAdapter(adapter);// 设置数据适配器

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                passwordET.setText(sp.getString(cardNumAuto.getText()
                        .toString(), ""));// 自动输入密码

            }
        });

        // 登陆
        logBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                cardNumStr = cardNumAuto.getText().toString();
                passwordStr = passwordET.getText().toString();

                if (!((cardNumStr.equals("test")) && (passwordStr
                        .equals("test")))) {
                    Toast.makeText(UserLoginActivity.this, "密码错误，请重新输入",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (savePasswordCB.isChecked()) {// 登陆成功才保存密码
                        sp.edit().putString(cardNumStr, passwordStr).commit();
                    }
                    Toast.makeText(UserLoginActivity.this, "登陆成功，正在获取用户数据……",
                            Toast.LENGTH_SHORT).show();
                    // 跳转到另一个Activity
                    // do something

                }

            }
        });

        WebSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Web_Intent = new Intent(UserLoginActivity.this,
                        WebSettingActivity.class);
                startActivity(Web_Intent);
            }
        });

    }

}
