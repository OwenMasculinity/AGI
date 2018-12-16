package com.example.asus.OfflineMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 张凌霄 on 2016/11/12.
 */
public class MainActivity extends Activity {

    Button  SurveyResult;
    Button  SurveyLogcat;
    Button  UserLogin;
    Button  SystemSettings;
    Button  OfflineMap;
    Button  PersonCentre;
    Button TaskManage;
    Button SurveyTable;
    Button Camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurveyResult=(Button)findViewById(R.id.SurveyResult);
        SurveyLogcat=(Button)findViewById(R.id.SurveyLogcat);
        UserLogin=(Button)findViewById(R.id.UserLogin);
        SystemSettings=(Button)findViewById(R.id.SystemSettings);
        OfflineMap=(Button)findViewById(R.id.OfflineMap);
        PersonCentre=(Button)findViewById(R.id.PersonCentre);
        TaskManage=(Button) findViewById(R.id.TaskManage);
        SurveyTable=(Button)findViewById(R.id.SurveyTable);
        Camera=(Button)findViewById(R.id.TakePhoto);

        OfflineMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Offline_Intent = new Intent(MainActivity.this,
                        OfflineDemo.class);
                startActivity(Offline_Intent);
            }
        });

        SurveyLogcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SurveyLogcat_Intent = new Intent(MainActivity.this,
                        SurveyLogcatActivity.class);
                startActivity(SurveyLogcat_Intent);
            }
        });

        SurveyResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SurveyResult_Intent = new Intent(MainActivity.this,
                        SurveyResultActivity.class);
                startActivity(SurveyResult_Intent);

            }
        });

        SurveyTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SurveyTable_Intent = new Intent(MainActivity.this,
                        SurveyTableActivity.class);
                startActivity(SurveyTable_Intent);
            }
        });

        PersonCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PersonCentre_Intent = new Intent(MainActivity.this,
                        PersonCentreActivity.class);
                startActivity(PersonCentre_Intent);
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserLogin_Intent = new Intent(MainActivity.this,
                        UserLoginActivity.class);
                startActivity(UserLogin_Intent);
            }
        });

        SystemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SystemSettings_Intent = new Intent(MainActivity.this,
                        SystemSettingsActivity.class);
                startActivity(SystemSettings_Intent);
            }
        });

        TaskManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TaskManage_Intent = new Intent(MainActivity.this,
                        TaskManageActivity.class);
                startActivity(TaskManage_Intent);
            }
        });

       Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Camera_Intent = new Intent(MainActivity.this,
                       CameraActivity.class);
                startActivity(Camera_Intent);
            }
        });
    }
}
