package com.example.lm.count_people.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lm.count_people.Adapter.MyFragmentPagerAdapter;
import com.example.lm.count_people.R;

public class FgFunctionActivity  extends BaseCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_count;
    private RadioButton rb_temp;
    private RadioButton rb_light;
    private RadioButton rb_linechart;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    public String username;
    private TextView your_username;

    private NavigationView fg_navigation_view;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_main);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_count.setChecked(true);


        fg_navigation_view = findViewById(R.id.fg_navigation_view);
        username = getIntent().getStringExtra("username");
        //获取抽屉菜单的头部布局
        View headerView = fg_navigation_view.getHeaderView(0);
        your_username =  headerView.findViewById(R.id.your_username);
        your_username.setText(username);


        fg_navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_myinfo:
                        Intent intent = new Intent(FgFunctionActivity.this,MyInfoActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                        break;
                    case R.id.nav_explore:
                        Intent intent1 = new Intent(FgFunctionActivity.this,WebViewActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(FgFunctionActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.menu_chat:
                        Intent intent3 = new Intent(FgFunctionActivity.this,ChatActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.menu_exit:

                        AlertDialog dialog = new AlertDialog.Builder(FgFunctionActivity.this)
                                .setTitle("退出登录")//设置对话框的标题
                                .setMessage("您确定要推出登录吗")//设置对话框的内容
                                //设置对话框的按钮
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent("loginout");
                                        sendBroadcast(intent4);
                                        dialog.dismiss();
                                    }
                                }).create();
                        dialog.show();


                        break;

                }
                return false;
            }
        });

    }

    private void bindViews() {

        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_count = (RadioButton) findViewById(R.id.rb_count);
        rb_temp = (RadioButton) findViewById(R.id.rb_temp);
        rb_light= (RadioButton) findViewById(R.id.rb_light);
        rb_linechart = (RadioButton) findViewById(R.id.rb_linechart);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_count:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_temp:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_light:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_linechart:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_count.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_temp.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_light.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_linechart.setChecked(true);
                    break;
            }
        }
    }




}
