package com.example.asus.OfflineMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.baidu.mapapi.map.offline.MKOLUpdateElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by asus on 2016/11/12.
 */

public class TaskManageActivity extends Activity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup TaskDownload;
    private LinearLayout Download_layout;
    private LinearLayout NoDownload_layout;
    private ArrayList<HashMap<String,Object>> localTaskPackageList = null;
    private ListView downloadTasklist;
    private BaseAdapter LTAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_manage);

        TaskDownload = (RadioGroup) findViewById(R.id.TaskDownload);
        Download_layout=(LinearLayout)findViewById(R.id.download_layout);
        NoDownload_layout=(LinearLayout)findViewById(R.id.nodownload_layout);
        downloadTasklist=(ListView)findViewById(R.id.download_task_list);
        LTAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object getItem(int index) {
                return index;
            }

            @Override
            public long getItemId(int index) {
                return index;
            }

            @Override
            //赋值操作都再getview中执行
            public View getView(int arg0, View arg1, ViewGroup arg2) {
                View view = View.inflate(TaskManageActivity.this, R.layout.task_manage_list_item, null);
                //ImageView trashcan = (ImageView) view.findViewById(R.id.trash_can);
                //trashcan.setText("赋值");
                /*if (state) {
                    button.setVisibility(View.VISIBLE);
                }else{
                    button.setVisibility(View.GONE);
                }*/
                return view;
            }
        };

        downloadTasklist.setAdapter(LTAdapter);
        TaskDownload.setOnCheckedChangeListener(this);


    }

    /*
    修改按键显示
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.download:
                Download_layout.setVisibility(View.VISIBLE);
                NoDownload_layout.setVisibility(View.GONE);
                Log.i("tag", "你选着已下载按钮");
                break;
            case R.id.nodownload:
                NoDownload_layout.setVisibility(View.VISIBLE);
                Download_layout.setVisibility(View.GONE);
                Log.i("tag", "你选着未下载按钮");
                break;
        }
    }




}
