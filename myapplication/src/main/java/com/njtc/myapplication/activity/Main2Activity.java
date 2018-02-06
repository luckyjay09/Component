package com.njtc.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.njtc.myapplication.R;

public class Main2Activity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "Main2Activity";
    private TextView mTextMessage;
    private BottomNavigationBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        initNavigationBar();
    }

    private void initNavigationBar() {
//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColorResource(R.color.colorAccent)
//                .setText("99+")
//                .setHideOnSelect(false);


        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        bottomNavigationBar.setBarBackgroundColor(R.color.transparent);
        //bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setAutoHideEnabled(true);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED_NO_TITLE);


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "呵呵").setInactiveIconResource(R.drawable.axg).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axd, "").setInactiveIconResource(R.drawable.axc).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axf, "").setInactiveIconResource(R.drawable.axe).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axb, "").setInactiveIconResource(R.drawable.axa).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axj, "").setInactiveIconResource(R.drawable.axi).setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

//        Drawable drawable = getResources().getDrawable();
//        bottomNavigationBar.getChildAt(0).setBackgroundResource(R.drawable.arrow_rt);

        for (int i = 0; i < bottomNavigationBar.getChildCount(); i++) {
            Log.d(TAG, "initNavigationBar: " + i);
        }

        FrameLayout frameLayout = ((FrameLayout) bottomNavigationBar.getChildAt(0));
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            Log.d(TAG, "FrameLayout: " + i);
        }

        FrameLayout childAt0 = (FrameLayout) frameLayout.getChildAt(0);
        for (int i = 0; i < childAt0.getChildCount(); i++) {
            Log.d(TAG, "childAt0: " + i);
        }

        LinearLayout childAt1 = (LinearLayout) frameLayout.getChildAt(1);
        for (int i = 0; i < childAt1.getChildCount(); i++) {
            Log.d(TAG, "childAt1: " + i);
        }

//        FrameLayout child = (FrameLayout) childAt1.getChildAt(0);
//        child.setBackgroundColor(getResources().getColor(R.color.transparent));
//        ViewCompat.setBackground(child, getResources().getDrawable(R.drawable.bottom_bar_ripple));

        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
