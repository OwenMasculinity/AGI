package com.example.asus.OfflineMap;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SurveyLogcatActivity extends Activity {

	String start_date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
	String end_date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
	private ImageView Back;
	ImageButton Calendar1;
	ImageButton Calendar2;
	TextView StartdateText;
	TextView EnddateText;
	ListView surveylogcatlist;
	TextView logcatinformationView;
	TextView logcatdateView;

	private BaseAdapter SLAdapter = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.survey_logcat);
		Calendar1 = (ImageButton) findViewById(R.id.calendar1);
		Calendar2 = (ImageButton) findViewById(R.id.calendar2);
		Back=(ImageView)findViewById(R.id.Back);
		StartdateText=(TextView) findViewById(R.id.startdate);
		EnddateText=(TextView) findViewById(R.id.enddate);
		surveylogcatlist=(ListView)findViewById(R.id.survey_logcat_list);


		Calendar1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new PopupWindows_start(SurveyLogcatActivity.this, Calendar1);

			}
		});

		Calendar2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new PopupWindows_end(SurveyLogcatActivity.this, Calendar2);

			}
		});

		Back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		SLAdapter = new BaseAdapter() {
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
				View view = View.inflate(SurveyLogcatActivity.this, R.layout.survey_logcat_list_item, null);
				logcatinformationView = (TextView)view.findViewById(R.id.logcat_information);
				logcatdateView = (TextView)view.findViewById(R.id.logcat_date);
				initSQL();
				//trashcan.setText("赋值");
                /*if (state) {
                    button.setVisibility(View.VISIBLE);
                }else{
                    button.setVisibility(View.GONE);
                }*/
				return view;
			}
		};
		surveylogcatlist.setAdapter(SLAdapter);

		//initSQL();

	}

	private void initSQL(){
		//打开数据库输出流
		SQLdm s = new SQLdm(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/2016清镇市/RESULT/DATA.db");
		SQLiteDatabase db =s.openDatabase(getApplicationContext());

		//查询数据库中testid=1的数据
		Cursor cursor = db.rawQuery("select * from 调查日志 where DATE=?", new String[]{"2016-08-08"});
		String logcat_date = null;
		String logcat_yangfang="";
		String logcat_CUNDM="";
		String logcat_cun="";
		String logcat_jingdu=null;
		String logcat_weidu=null;
		String logcat_dikuai=null;
		if(cursor.moveToFirst()){
			logcat_date  = cursor.getString(cursor.getColumnIndex("DATE"));
			logcat_yangfang=cursor.getString(cursor.getColumnIndex("UUID"));
			logcat_jingdu=cursor.getString(cursor.getColumnIndex("LON"));
			logcat_weidu=cursor.getString(cursor.getColumnIndex("LAT"));
			logcat_dikuai=cursor.getString(cursor.getColumnIndex("PUUID"));
		}

		for(int i=0;i<12;i++)
		{
			logcat_CUNDM+=logcat_yangfang.charAt(i);
		}

		Cursor cursor1=db.rawQuery("select * from 村边界 where CUNDM=?" ,new String[]{logcat_CUNDM});

		if(cursor1.moveToFirst()){
			logcat_cun= cursor1.getString(cursor1.getColumnIndex("CUNMC"));
		}

		logcatdateView.setText(logcat_date);
		logcatinformationView.setText(logcat_cun);
		logcatinformationView.append("  ");
		logcatinformationView.append("样方：" + logcat_yangfang);
		logcatinformationView.append("\n");
		logcatinformationView.append("地块："+logcat_dikuai);
		logcatinformationView.append("\n");
		logcatinformationView.append("经度："+logcat_jingdu+"\n"+ "纬度："+logcat_weidu);
		cursor.close();
		cursor1.close();
	}


	public class PopupWindows_start extends PopupWindow {

		public PopupWindows_start(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.popupwindow_calendar,
					null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_in));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_1));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);

			int[] location =new int[2];
			parent.getLocationOnScreen(location);
			showAsDropDown(parent);
			//showAtLocation(parent, Gravity.BOTTOM, 0, 0);

			update();

			final TextView popupwindow_calendar_month = (TextView) view
					.findViewById(R.id.popupwindow_calendar_month);
			final KCalendar calendar = (KCalendar) view
					.findViewById(R.id.popupwindow_calendar);
			Button popupwindow_calendar_bt_enter = (Button) view
					.findViewById(R.id.popupwindow_calendar_bt_enter);

			popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
					+ calendar.getCalendarMonth() + "月");

			if (null != start_date) {

				int years = Integer.parseInt(start_date.substring(0,
						start_date.indexOf("-")));
				int month = Integer.parseInt(start_date.substring(
						start_date.indexOf("-") + 1, start_date.lastIndexOf("-")));
				popupwindow_calendar_month.setText(years + "年" + month + "月");

				calendar.showCalendar(years, month);
				calendar.setCalendarDayBgColor(start_date,
						R.drawable.calendar_date_focused);				
			}
			
			List<String> list = new ArrayList<String>(); //设置标记列表
			list.add("2014-04-01");
			list.add("2014-04-02");
			calendar.addMarks(list, 0);

			//监听所选中的日期
			calendar.setOnCalendarClickListener(new KCalendar.OnCalendarClickListener() {

				public void onCalendarClick(int row, int col, String dateFormat) {
					int month = Integer.parseInt(dateFormat.substring(
							dateFormat.indexOf("-") + 1,
							dateFormat.lastIndexOf("-")));
					
					if (calendar.getCalendarMonth() - month == 1//跨年跳转
							|| calendar.getCalendarMonth() - month == -11) {
						calendar.lastMonth();
						
					} else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
							|| month - calendar.getCalendarMonth() == -11) {
						calendar.nextMonth();
						
					} else {
						calendar.removeAllBgColor(); 
						calendar.setCalendarDayBgColor(dateFormat,
								R.drawable.calendar_date_focused);
						start_date = dateFormat;//最后返回给全局 start_date
					}
				}
			});

			//监听当前月份
			calendar.setOnCalendarDateChangedListener(new KCalendar.OnCalendarDateChangedListener() {
				public void onCalendarDateChanged(int year, int month) {
					popupwindow_calendar_month
							.setText(year + "年" + month + "月");
				}
			});
			
			//上月监听按钮
			RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) view
					.findViewById(R.id.popupwindow_calendar_last_month);
			popupwindow_calendar_last_month
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							calendar.lastMonth();
						}

					});
			
			//下月监听按钮
			RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) view
					.findViewById(R.id.popupwindow_calendar_next_month);
			popupwindow_calendar_next_month
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							calendar.nextMonth();
						}
					});
			
			//关闭窗口
			popupwindow_calendar_bt_enter
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							dismiss();
							StartdateText.setText(start_date);
						}
					});
		}
	}


	public class PopupWindows_end extends  PopupWindow {


		public PopupWindows_end(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.popupwindow_calendar,
					null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_in));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_1));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			//showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			int[] location2 =new int[2];
			parent.getLocationOnScreen(location2);
			showAsDropDown(parent,100,0);
			update();

			final TextView popupwindow_calendar_month = (TextView) view
					.findViewById(R.id.popupwindow_calendar_month);
			final KCalendar calendar = (KCalendar) view
					.findViewById(R.id.popupwindow_calendar);
			Button popupwindow_calendar_bt_enter = (Button) view
					.findViewById(R.id.popupwindow_calendar_bt_enter);

			popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
					+ calendar.getCalendarMonth() + "月");

			if (null != end_date) {

				int years = Integer.parseInt(end_date.substring(0,
						end_date.indexOf("-")));
				int month = Integer.parseInt(end_date.substring(
						end_date.indexOf("-") + 1, end_date.lastIndexOf("-")));
				popupwindow_calendar_month.setText(years + "年" + month + "月");

				calendar.showCalendar(years, month);
				calendar.setCalendarDayBgColor(end_date,
						R.drawable.calendar_date_focused);
			}

			List<String> list = new ArrayList<String>(); //设置标记列表
			list.add("2014-04-01");
			list.add("2014-04-02");
			calendar.addMarks(list, 0);

			//监听所选中的日期
			calendar.setOnCalendarClickListener(new KCalendar.OnCalendarClickListener() {

				public void onCalendarClick(int row, int col, String dateFormat) {
					int month = Integer.parseInt(dateFormat.substring(
							dateFormat.indexOf("-") + 1,
							dateFormat.lastIndexOf("-")));

					if (calendar.getCalendarMonth() - month == 1//跨年跳转
							|| calendar.getCalendarMonth() - month == -11) {
						calendar.lastMonth();

					} else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
							|| month - calendar.getCalendarMonth() == -11) {
						calendar.nextMonth();

					} else {
						calendar.removeAllBgColor();
						calendar.setCalendarDayBgColor(dateFormat,
								R.drawable.calendar_date_focused);
						end_date = dateFormat;//最后返回给全局 end_date
					}
				}
			});

			//监听当前月份
			calendar.setOnCalendarDateChangedListener(new KCalendar.OnCalendarDateChangedListener() {
				public void onCalendarDateChanged(int year, int month) {
					popupwindow_calendar_month
							.setText(year + "年" + month + "月");
				}
			});

			//上月监听按钮
			RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) view
					.findViewById(R.id.popupwindow_calendar_last_month);
			popupwindow_calendar_last_month
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							calendar.lastMonth();
						}

					});

			//下月监听按钮
			RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) view
					.findViewById(R.id.popupwindow_calendar_next_month);
			popupwindow_calendar_next_month
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							calendar.nextMonth();
						}
					});

			//关闭窗口
			popupwindow_calendar_bt_enter
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							dismiss();
							EnddateText.setText(end_date);
						}
					});
		}
	}

}
