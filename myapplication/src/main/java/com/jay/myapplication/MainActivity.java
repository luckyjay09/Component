package com.jay.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.jess.arms.base.MvpBaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpBaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    vp.setCurrentItem(0);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fl, mList.get(0)).commit();

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fl, mList.get(1)).commit();
                    vp.setCurrentItem(1);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fl, mList.get(2)).commit();
//                    FragmentUtils.add(getSupportFragmentManager(), mList.get(0), R.id.fl);
                    vp.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
    private ViewPager vp;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    private List<Fragment> mList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BlankFragment f1 = BlankFragment.newInstance("e", "k");
        BlankFragment f2 = BlankFragment.newInstance("e", "k");
        BlankFragment f3 = BlankFragment.newInstance("e", "k");
        mList.add(f1);
        mList.add(f2);
        mList.add(f3);

//        FragmentUtils.add(getSupportFragmentManager(), mList, R.id.fl, 0);

        vp = ((ViewPager) findViewById(R.id.vp));
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });

        vp.setOffscreenPageLimit(mList.size());
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navigation.setSelectedItemId(R.id.navigation_home);
                } else if (position == 1) {
                    navigation.setSelectedItemId(R.id.navigation_dashboard);
                } else {
                    navigation.setSelectedItemId(R.id.navigation_notifications);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigation.setSelectedItemId(R.id.navigation_home);
    }
}
