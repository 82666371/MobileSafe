package com.example.mobilesafe.ui;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesafe.R;
import com.example.mobilesafe.adapter.DongtaiAdapter;
import com.example.mobilesafe.adapter.PageAdapter;
import com.example.mobilesafe.adapter.PopwindowsAdapter;
import com.example.mobilesafe.entity.DongtaiItm;
import com.example.mobilesafe.entity.PopItm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView tools;
    private TextView optimization;
    private TextView appmanger;
    private ImageView mTransbuar;
    private ViewPager mVpager;
    private View viewTools;
    private View viewOptimization;
    private View viewAppmanger;
    private ArrayList<View> pageList;

    private TextView curTxt;
    private ImageView more;
    private PopItm item;
    private ArrayList<PopItm> list = new ArrayList<>();

    private int wight;
    private int hight;
    private View view;
    private ListView listView;
    private PopupWindow popupWindow;
    private ListView mNew_list;
    private TextView mTextView;
    private TextView mTime;
    private TextView mNow_time;
    private ImageView mDaka;
    private TextView sb_time;
    private TextView xb_time;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        setImageParam();

        initContentView();

        initnew();
    }


    private void initnew() {
        mTextView = viewAppmanger.findViewById(R.id.moon);
        SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new    java.util.Date());
        mTextView.setText(date);

        Calendar calendar = Calendar.getInstance();
        String displayName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());

        mTime = viewAppmanger.findViewById(R.id.time);
        mTime.setText(displayName);

        SimpleDateFormat    sDateFormat1    =   new    SimpleDateFormat("hh:mm");
        String    date1    =    sDateFormat1.format(new    java.util.Date());

        mNow_time = viewAppmanger.findViewById(R.id.now_time);
        mNow_time.setText(date1);

        mDaka = viewAppmanger.findViewById(R.id.daka);
        sb_time = viewAppmanger.findViewById(R.id.sb_time);
        xb_time = viewAppmanger.findViewById(R.id.xb_time);

        mDaka.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (i == 0){
                    SimpleDateFormat    sDateFormat1    =   new    SimpleDateFormat("hh:mm:ss");
                    String    date1    =    sDateFormat1.format(new    java.util.Date());
                    sb_time.setText(date1);
                    i++;
                }else {
                    SimpleDateFormat    sDateFormat1    =   new    SimpleDateFormat("hh:mm:ss");
                    String    date1    =    sDateFormat1.format(new    java.util.Date());
                    xb_time.setText(date1);
                    i = 0;
                }

            }
        });
    }


    /**
     * 初始化内容页
     */
    private void initContentView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        viewTools = inflater.inflate(R.layout.acticitymain_tools, null);
        viewAppmanger = inflater.inflate(R.layout.activitymain_app, null);
        viewOptimization = inflater.inflate(R.layout.activitymain_op, null);

        pageList.add(viewTools);
        pageList.add(viewAppmanger);
        pageList.add(viewOptimization);

        mVpager.setAdapter(new PageAdapter(pageList));
    }
    /**
     * 初始化图片的参数
     */
    private void setImageParam() {
       int disWidth = getWindowManager().getDefaultDisplay().getWidth();

       int imgWidth = (int) (disWidth / 4 *0.75);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTransbuar.getLayoutParams();
        params.width = imgWidth;
        mTransbuar.setLayoutParams(params);

        TranslateAnimation move = new TranslateAnimation(0, (disWidth / 4 - imgWidth) / 2, 0, 0);
        move.setDuration(100);
        move.setFillAfter(true);
        mTransbuar.setAnimation(move);

    }

    /**
     * 初始化
     */
    private void initView() {
        tools = findViewById(R.id.tv_tools);
        optimization = findViewById(R.id.tv_optimization);
        appmanger = findViewById(R.id.tv_appmanger);
        mTransbuar = findViewById(R.id.trans_bar);
        mVpager = findViewById(R.id.vpager);

        tools.setOnClickListener(this);
        optimization.setOnClickListener(this);
        appmanger.setOnClickListener(this);

        mVpager.setOnPageChangeListener(this);

        pageList = new ArrayList<>();

        curTxt = tools;

        setTextColor(tools);

        more = findViewById(R.id.more);

        initMoreList();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] lacation = new int[2];
                //得到more控件的位置
                more.getLocationOnScreen(lacation);
                int x = lacation[0];
                int y = lacation[1];

                wight= x;
                hight=y;
                showPopWindows();
            }
        });

    }

    /**
     * 显示弹出菜单
     */
    private void showPopWindows() {
        view = getLayoutInflater().inflate(R.layout.pup_main, null, false);
        listView = view.findViewById(R.id.pup_list);

        listView.setAdapter(new PopwindowsAdapter(getApplicationContext(),list));

        popupWindow = new PopupWindow(view, 120, 300);
        //是否能成为焦点
        popupWindow.setFocusable(true);
        //设置它外面是否可以点击
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAsDropDown(view,wight,hight+60);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupWindow != null){
                    popupWindow.dismiss();
                }
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this,"跳转", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"跳转", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    /**
     * 初始化更多列表
     */
    private void initMoreList() {
        item = new PopItm("用户中心");
        list.add(item);
        item = new PopItm("设置选择");
        list.add(item);
        item = new PopItm("用户中心");
        list.add(item);
        item = new PopItm("用户中心");
        list.add(item);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tools:
                imgTrans(tools);
                setTextColor(tools);
                mVpager.setCurrentItem(0);
                break;
            case R.id.tv_optimization:
                imgTrans(optimization);
                setTextColor(optimization);
                mVpager.setCurrentItem(1);
                break;
            case R.id.tv_appmanger:
                imgTrans(appmanger);
                setTextColor(appmanger);
                mVpager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    /**
     * 设置切换文本颜色
     * @param endTxt
     */
    private void setTextColor(TextView endTxt) {
        switch (endTxt.getId()) {
            case R.id.tv_tools:
                tools.setTextColor(getResources().getColor(R.color.green));
                optimization.setTextColor(getResources().getColor(R.color.white));
                appmanger.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_optimization:
                tools.setTextColor(getResources().getColor(R.color.white));
                optimization.setTextColor(getResources().getColor(R.color.green));
                appmanger.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_appmanger:
                tools.setTextColor(getResources().getColor(R.color.white));
                optimization.setTextColor(getResources().getColor(R.color.white));
                appmanger.setTextColor(getResources().getColor(R.color.green));
                break;

            default:
                break;
        }
    }

    /**
     * 动画效果改变下标图片位置
     * @param endTxt
     */
    private void imgTrans(TextView endTxt) {
        int startMid = curTxt.getLeft() + curTxt.getWidth() / 2;

        int startLeft = startMid - mTransbuar.getWidth() / 2;

        int endMid = endTxt.getLeft() + endTxt.getWidth() / 2;

        int endLeft = endMid - mTransbuar.getWidth() / 2;

        TranslateAnimation move = new TranslateAnimation(startLeft, endLeft, 0, 0);
        move.setDuration(200);
        move.setFillAfter(true);
        mTransbuar.startAnimation(move);

        curTxt = endTxt;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int pageid) {
        switch (pageid) {
            case 0:
                imgTrans(tools);
                setTextColor(tools);
                break;
            case 1:
                imgTrans(optimization);
                setTextColor(optimization);
                break;
            case 2:
                imgTrans(appmanger);
                setTextColor(appmanger);
                break;

            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
