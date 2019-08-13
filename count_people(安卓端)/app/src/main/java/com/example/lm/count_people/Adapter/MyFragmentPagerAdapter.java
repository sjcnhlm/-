package com.example.lm.count_people.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.lm.count_people.Activity.FgFunctionActivity;
import com.example.lm.count_people.Fragemnt.FragmentCountList;
import com.example.lm.count_people.Fragemnt.FragmentLightControl;
import com.example.lm.count_people.Fragemnt.FragmentLineChart;
import com.example.lm.count_people.Fragemnt.FragmentTemperControl;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 4;
//    private MyFragment1 myFragment1 = null;
//    private MyFragment2 myFragment2 = null;
//    private MyFragment3 myFragment3 = null;
//    private MyFragment4 myFragment4 = null;

    private FragmentCountList fragmentCountList = null;
    private FragmentTemperControl fragmentTemperControl = null;
    private FragmentLightControl fragmentLightControl = null;
    private FragmentLineChart fragmentLineChart = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
//        myFragment1 = new MyFragment1();
//        myFragment2 = new MyFragment2();
//        myFragment3 = new MyFragment3();
//        myFragment4 = new MyFragment4();

        fragmentCountList = new FragmentCountList();
        fragmentTemperControl = new FragmentTemperControl();
        fragmentLightControl = new FragmentLightControl();
        fragmentLineChart = new FragmentLineChart();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(@NonNull  ViewGroup container, int position, @NonNull Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case FgFunctionActivity.PAGE_ONE:
                fragment = fragmentCountList;
                break;
            case FgFunctionActivity.PAGE_TWO:
                fragment = fragmentTemperControl;
                break;
            case FgFunctionActivity.PAGE_THREE:
                fragment = fragmentLightControl;
                break;
            case FgFunctionActivity.PAGE_FOUR:
                fragment = fragmentLineChart;
                break;
        }
        return fragment;
    }
}
