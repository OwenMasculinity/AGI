package com.example.asus.OfflineMap;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by asus on 2016/11/12.
 */
public class SurveyTableActivity extends Activity implements RadioGroup.OnCheckedChangeListener{

    private final String SQLPath_read=android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/2016清镇市/DEMAND/DATA.db";
    private final String SQLPath_write=android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/2016清镇市/RESULT/DATA.db";
    private RadioGroup Survey;
    private LinearLayout People_layout;
    private LinearLayout Equipment_layout;
    private TextView administration_regionTV;
    private TextView block_numberTV;
    private TextView sample_numberTV;
    private TextView picture_typeTV;
    private TextView picture_areaTV;
    private String administration_region;
    private String block_number;
    private String sample_number;
    private String picture_type;
    private String picture_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_table);
        Survey=(RadioGroup)findViewById(R.id.survey_imformation);
        Survey.setOnCheckedChangeListener(this);
        People_layout=(LinearLayout)findViewById(R.id.people_layout);
        Equipment_layout=(LinearLayout)findViewById(R.id.equipment_layout);

        administration_regionTV=(TextView)findViewById(R.id.administration_region);
        block_numberTV=(TextView)findViewById(R.id.block_number);
        sample_numberTV=(TextView)findViewById(R.id.sample_number);
        picture_areaTV=(TextView)findViewById(R.id.picture_area);
        picture_typeTV=(TextView)findViewById(R.id.picture_type);


        Equipment_layout.setVisibility(View.VISIBLE);
        People_layout.setVisibility(View.GONE);

        initSQL();
    }

    private void initSQL()
    {
        SQLdm s_read = new SQLdm(SQLPath_read);
        SQLiteDatabase db_read =s_read.openDatabase(getApplicationContext());
        Cursor cursor_read = db_read.rawQuery("select * from 样方自然地块 order by DKBHU", null);
        cursor_read.moveToFirst();
        block_number=cursor_read.getString(cursor_read.getColumnIndex("DKBHU"));//地块编号
        sample_number=cursor_read.getString(cursor_read.getColumnIndex("YFBHU"));//样方编号
        administration_region=cursor_read.getString(cursor_read.getColumnIndex("CUNMC"));//行政区域
        picture_area=cursor_read.getString(cursor_read.getColumnIndex("TBMJ"));//图斑面积
        picture_type=cursor_read.getString(cursor_read.getColumnIndex("TBLXMC"));//图斑类型

        block_numberTV.append(block_number);
        sample_numberTV.append(sample_number);
        administration_regionTV.append(administration_region);
        picture_areaTV.setText(picture_area);
        picture_typeTV.append(picture_type);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.people_information:
                People_layout.setVisibility(View.VISIBLE);
                Equipment_layout.setVisibility(View.GONE);
                //Log.i("tag", "你选着已调查按钮");
                break;
            case R.id.equipment_information:
                Equipment_layout.setVisibility(View.VISIBLE);
                People_layout.setVisibility(View.GONE);
                //Log.i("tag", "你选着未调查按钮");
                break;
        }
    }

}
