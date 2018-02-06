package com.jay.mywidget.mvp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jay.mywidget.R;
import com.jay.mywidget.widget.MaterialBadgeTextView;

public class TextBadgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_badge);

        MaterialBadgeTextView just_new_feature = (MaterialBadgeTextView) findViewById(R.id.just_new_feature);
        just_new_feature.setHighLightMode();

        TextView test_text = (MaterialBadgeTextView) findViewById(R.id.test_text);
        test_text.setBackgroundColor(Color.parseColor("#987aef"));
    }
}
