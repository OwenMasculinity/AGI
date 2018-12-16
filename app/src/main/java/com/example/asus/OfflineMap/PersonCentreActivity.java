package com.example.asus.OfflineMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PersonCentreActivity extends Activity {
    Button save;
    EditText oldpwdET;
    String oldpwd;
    EditText newpwdET;
    String newpwd;
    EditText repwdET;
    String repwd;
    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_centre);

        Back=(ImageView)findViewById(R.id.Back);
        save=(Button)findViewById(R.id.save);
        oldpwdET = (EditText) findViewById(R.id.oldpassword);    //旧密码
        oldpwdET.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        oldpwd = oldpwdET.getText().toString();

        newpwdET = (EditText) findViewById(R.id.newpassword);    //新密码
        newpwdET.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        repwdET = (EditText) findViewById(R.id.repassword);    //确认密码
        repwdET.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpwd = newpwdET.getText().toString();
                repwd = repwdET.getText().toString();
                if (!newpwd.equals(repwd)) {    //判断两次密码是否一致
                    Toast.makeText(PersonCentreActivity.this, "两次输出密码不一致，请重新输入", Toast.LENGTH_LONG).show();
                    ((EditText) findViewById(R.id.newpassword)).setText("");    //清空密码编辑框
                    ((EditText) findViewById(R.id.repassword)).setText("");    //清空确认密码编辑框
                    ((EditText) findViewById(R.id.newpassword)).requestFocus();    //让密码编辑框获得焦点
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}


