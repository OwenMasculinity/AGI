package com.example.asus.OfflineMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.asus.OfflineMap.bean.Node;
import com.example.asus.OfflineMap.bean.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultActivity extends Activity implements RadioGroup.OnCheckedChangeListener{

    Intent intent_SurveyResult;
    private final String SQLPath=android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/2016清镇市/RESULT/DATA.db";
    private RadioGroup download;
    private LinearLayout SurveyLayout;
    private LinearLayout NoSurveyLayout;
    private ImageView Back;

    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;
    private ArrayList<String> result_cun=new ArrayList<>();
    private ArrayList<String> result_yangfang=new ArrayList<>();
    //private String[] result_cun=new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_result);
        download=(RadioGroup)findViewById(R.id.switch_btn_id);
        SurveyLayout=(LinearLayout)findViewById(R.id.survey_layout);
        NoSurveyLayout=(LinearLayout)findViewById(R.id.nosurvey_layout);

        SurveyLayout.setVisibility(View.VISIBLE);
        NoSurveyLayout.setVisibility(View.GONE);

        Back=(ImageView)findViewById(R.id.Back);

        mTree = (ListView) findViewById(R.id.survey_list);

        initDatas();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try
        {
            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener()
            {
                @Override
                public void onClick(Node node, int position)
                {
                    if (node.isLeaf())
                    {
                        intent_SurveyResult = new Intent(SurveyResultActivity.this,
                                SurveyTableActivity.class);
                        startActivity(intent_SurveyResult);

                        Toast.makeText(getApplicationContext(), node.getName(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

            });

            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    private void initDatas()
    {
        String Temp_cun=null;
        String Temp_yangfang=null;
        String New_yangfang=null;
        String Temp_dikuai=null;
        String New_dikuai=null;
        int cun_pid=0;
        int cun_number;
        int yangfang_pid;
        int yangfang_number;
        int dikuai_number;
        SQLdm s = new SQLdm(SQLPath);
        download.setOnCheckedChangeListener(this);

        SQLiteDatabase db =s.openDatabase(getApplicationContext());

        //第一级目录，村名称
        Cursor cursor_cun = db.rawQuery("select distinct CUNMC from 样方自然地块 order by DKBHU", null);
        cursor_cun.moveToFirst();
        cun_number=cursor_cun.getCount();
        for (int i=0;i<cun_number;i++) {
            //result_cun[i] = cursor.getString(cursor.getColumnIndex("CUNMC"));
            //result_cun.add(cursor_cun.getString(cursor_cun.getColumnIndex("CUNMC")));
            mDatas.add(new FileBean(1+i, 0, cursor_cun.getString(cursor_cun.getColumnIndex("CUNMC"))));
            cursor_cun.moveToNext();
        }
        cursor_cun.close();

        //第二级目录，样方编号
        Cursor cursor_yangfang=db.rawQuery("select distinct CUNMC,YFBHU from 样方自然地块 order by DKBHU", null);
        cursor_yangfang.moveToFirst();
        yangfang_number=cursor_yangfang.getCount();
        for(int i =0;i<yangfang_number;i++)
        {
            New_yangfang=cursor_yangfang.getString(cursor_yangfang.getColumnIndex("CUNMC"));
            if(!New_yangfang.equals(Temp_yangfang))
            {
                cun_pid++;
            }
            mDatas.add(new FileBean(1+i+cun_number,cun_pid , "样方"+cursor_yangfang.getString(cursor_yangfang.getColumnIndex("YFBHU"))));
            Temp_yangfang=New_yangfang;
            /*if(cursor.isAfterLast())
            {
                break;
            }*/
            cursor_yangfang.moveToNext();
        }
        cursor_yangfang.close();


        //第三级目录。具体调查信息
        Cursor cursor_dikuai = db.rawQuery("select distinct * from 样方自然地块 order by DKBHU", null);
        cursor_dikuai.moveToFirst();
        dikuai_number=cursor_dikuai.getCount();
        Cursor cursor1_dikuai = db.rawQuery("select distinct * from 作物 order by DKBHU", null);
        cursor1_dikuai.moveToFirst();
        yangfang_pid=0+cun_number;
        // id , pid , label , 其他属性
        for(int i=0;i<dikuai_number;i++)
        {
            New_dikuai=cursor_dikuai.getString(cursor_dikuai.getColumnIndex("YFBHU"));
            if(!New_dikuai.equals(Temp_dikuai))
            {
                yangfang_pid++;
            }
            mDatas.add(new FileBean(1+i+cun_number+yangfang_number, yangfang_pid , cursor_dikuai.getString(cursor_dikuai.getColumnIndex("YFDKBH"))
                    +"   "+cursor_dikuai.getString(cursor_dikuai.getColumnIndex("TBLXMC"))
                    +"   "+cursor1_dikuai.getString(cursor1_dikuai.getColumnIndex("ZW"))
                    +"   "+cursor1_dikuai.getString(cursor1_dikuai.getColumnIndex("ZWMJ"))+"亩"));
            Temp_dikuai=New_dikuai;
            cursor_dikuai.moveToNext();
            cursor1_dikuai.moveToNext();

        //mDatas.add(new FileBean(1+i, 0, result_cun.get(i)));


        }

        //mDatas.add(new FileBean(100, 1, "样方1"));
        //mDatas.add(new FileBean(4, 1, "样方2"));
        //mDatas.add(new FileBean(4, 1, "样方3"));

        //mDatas.add(new FileBean(3, 2, "11"));
        //mDatas.add(new FileBean(6, 2, "20"));
        //mDatas.add(new FileBean(7, 2, "21"));


        }

@Override
public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
        case R.id.survey_pass_id:
        SurveyLayout.setVisibility(View.VISIBLE);
        NoSurveyLayout.setVisibility(View.GONE);
        Log.i("tag", "你选着已调查按钮");
        break;
        case R.id.nosurvey_pass_id:
        NoSurveyLayout.setVisibility(View.VISIBLE);
        SurveyLayout.setVisibility(View.GONE);
        Log.i("tag", "你选着未调查按钮");
        break;
        }
        }

}