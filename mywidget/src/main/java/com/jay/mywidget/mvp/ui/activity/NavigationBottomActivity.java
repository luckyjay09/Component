package com.jay.mywidget.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jay.mywidget.R;

import java.lang.reflect.Field;

public class NavigationBottomActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "NavigationBottomActivit";

    private TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bottom);
        tv_msg = (TextView) findViewById(R.id.tv_msg);


        initNavigationBottomView();

        initRadioGroup();

        initLinearLayout();

        initRelativeLayout();
    }

    private LinearLayout ll1;
    private void initLinearLayout() {
        ll1 = (LinearLayout) findViewById(R.id.ll_main_bottom);
        for (int i = 0; i < ll1.getChildCount(); i++) {
            ll1.getChildAt(i).setOnClickListener(mOnLLClickListener1);
        }
        mOnLLClickListener1.onClick(ll1.getChildAt(0));
    }

    private View.OnClickListener mOnLLClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setSelected(true);
            v.setClickable(false);
            for (int i = 0; i < ll1.getChildCount(); i++) {
                if (ll1.getChildAt(i) != v){
                    ll1.getChildAt(i).setSelected(false);
                    ll1.getChildAt(i).setClickable(true);
                } else {
                    tv_msg.setText("LinearLayout + LinearLayout: " + i);
                }
            }
        }
    };

    //------------------------------LinearLayout RelativeLayout--------------------------------
    private LinearLayout ll2;
    private void initRelativeLayout() {
        ll2 = (LinearLayout) findViewById(R.id.ll_main);
        for (int i = 0; i < ll2.getChildCount(); i++) {
            ll2.getChildAt(i).setOnClickListener(mOnLLClickListener2);
        }
        mOnLLClickListener2.onClick(ll2.getChildAt(0));
    }

    private View.OnClickListener mOnLLClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setSelected(true);
            v.setClickable(false);
            for (int i = 0; i < ll2.getChildCount(); i++) {
                if (ll2.getChildAt(i) != v){
                    ll2.getChildAt(i).setSelected(false);
                    ll2.getChildAt(i).setClickable(true);
                } else {
                    tv_msg.setText("LinearLayout + RelativeLayout: " + i);
                }
            }
        }
    };

    //------------------------------RadioGroup--------------------------------
    private void initRadioGroup() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_main);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.rb_home);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = group.indexOfChild(group.findViewById(checkedId));
        tv_msg.setText("RadioGroup: " + String.valueOf(index));
    }


    //------------------------------NavigationBottomView--------------------------------
    private void initNavigationBottomView() {

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    tv_msg.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    tv_msg.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    tv_msg.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_info:
                    tv_msg.setText(R.string.title_info);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

}
