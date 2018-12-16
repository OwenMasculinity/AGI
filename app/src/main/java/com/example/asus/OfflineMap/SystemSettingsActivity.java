package com.example.asus.OfflineMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemSettingsActivity extends Activity {
    //final ArrayList<HashMap<String, String>> listItems1 = new ArrayList<HashMap<String, String>>();
    private ListView listview1;
    private ListView listview2;
    private ListView listview3;
    private TextView PackagePathTV;
    final List<Map<String, Object>> listItems2 = new ArrayList<Map<String, Object>>();


    final List<Map<String, Object>> listItems3 = new ArrayList<Map<String, Object>>();
    private  String PackagePath=android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    //private myAdapter myAdapter;
    //private LayoutInflater inflater = null;
    //private int oldPostion = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_settings);
         listview1 = (ListView) findViewById(R.id.listView1);
         listview2 = (ListView) findViewById(R.id.listView2);
         listview3 = (ListView) findViewById(R.id.listView3);
        int[] imageId1 = new int[] { R.drawable.previous_h};
        int[] imageId2 = new int[] {R.drawable.previous_h};
        int[] imageId3 = new int[] {  R.drawable.previous_h};
        //String[] title1 = new String[] {  "任务包存储位置" };
        String[] title2 = new String[] {  "网络配置" };
        String[] title3 = new String[] {  "GPS刷新间隔" };

        //requestData();
        BaseAdapter  adapter1 = new BaseAdapter() {
            @Override
            public int getCount() {
                return 1;
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
                View view = View.inflate(SystemSettingsActivity.this, R.layout.system_settings_item_3, null);
                PackagePathTV = (TextView)view.findViewById(R.id.package_path);
                PackagePathTV.setText(PackagePath);
                //trashcan.setText("赋值");
                /*if (state) {
                    button.setVisibility(View.VISIBLE);
                }else{
                    button.setVisibility(View.GONE);
                }*/
                return view;
            }
        };
        listview1.setAdapter(adapter1);


        //final List<Map<String, Object>> listItems2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId2.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId2[i]);
            map.put("title", title2[i]);
            listItems2.add(map);
        }
        SimpleAdapter adapter2 = new SimpleAdapter(this, listItems2,
                R.layout.system_settings_item_2, new String[] { "title", "image" }, new int[] {
                R.id.title, R.id.image });
        listview2.setAdapter(adapter2);

        //final List<Map<String, Object>> listItems3 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId3.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId3[i]);
            map.put("title", title3[i]);
            listItems3.add(map);
        }
        SimpleAdapter adapter3 = new SimpleAdapter(this, listItems3,
                R.layout.system_settings_item_2, new String[] { "title", "image" }, new int[] {
                R.id.title, R.id.image });
        listview3.setAdapter(adapter3);
    }

    private void requestData() {
        ArrayList<HashMap<String, String>> listItems1 = new ArrayList<HashMap<String, String>>();
        for (int i = 1; i <= 1; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("taskType", "任务包存储位置"+ "");
            item.put("survey1", "XXX调查"+ "");
            item.put("survey2", "XXX调查"+ "");
            listItems1.add(item);
        }
        //ListView listview1 = (ListView) findViewById(R.id.listView1);
        MultiExpandAdapter adapter1 = new MultiExpandAdapter(this, listItems1);
        listview1.setAdapter(adapter1);
    }

}
