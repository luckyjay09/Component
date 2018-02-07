package com.jay.mywidget.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jay.mywidget.R;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    private String[] mStr = new String[]{"底部导航栏创建的几种方式",
            "自定义Badge"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStr));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, NavigationBottomActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TextBadgeActivity.class));

                        break;
                    default:
                        break;
                }
            }
        });
    }

}
