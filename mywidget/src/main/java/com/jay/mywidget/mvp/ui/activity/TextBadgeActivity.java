package com.jay.mywidget.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jay.mywidget.R;
import com.jay.mywidget.widget.badge.BadgeTextView;
import com.jay.mywidget.widget.badge.MenuItemBadge;

public class TextBadgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_badge);

        BadgeTextView just_new_feature = (BadgeTextView) findViewById(R.id.just_new_feature);
        just_new_feature.setHighLightMode();

        TextView test_text = (BadgeTextView) findViewById(R.id.test_text);
        test_text.setBackgroundColor(Color.parseColor("#987aef"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItemNewFeature = menu.findItem(R.id.menu_new_feature);
        MenuItemBadge.update(this, menuItemNewFeature, new MenuItemBadge.Builder()
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_account_my_order))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#EF4738"))
                .textColor(Color.WHITE));

        MenuItem menuItemNotification = menu.findItem(R.id.menu_notification);
        MenuItemBadge.update(this, menuItemNotification, new MenuItemBadge.Builder()
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_notification))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#36B100"))
                .textColor(Color.WHITE));
        MenuItemBadge.getBadgeTextView(menuItemNotification).setBadgeCount("5");


        MenuItem menuItemMessage = menu.findItem(R.id.menu_message);
        MenuItemBadge.update(this, menuItemMessage, new MenuItemBadge.Builder()
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_email))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#EF4738"))
                .textColor(Color.WHITE));
        MenuItemBadge.getBadgeTextView(menuItemMessage).setBadgeCount(999);


        MenuItem menuItemShopCart = menu.findItem(R.id.menu_shopcart);
        MenuItemBadge.update(this, menuItemShopCart, new MenuItemBadge.Builder()
                .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_shopping_cart))
                .iconTintColor(Color.WHITE)
                .textBackgroundColor(Color.parseColor("#FB8C00"))
                .textColor(Color.WHITE));
        MenuItemBadge.getBadgeTextView(menuItemShopCart).setText("New");
        return true;
    }

    private boolean showRedIcon = false;
    private void toggleRedIconInNewFeatureMenu(MenuItem menuItemNewFeature) {
        showRedIcon = !showRedIcon;
        if (showRedIcon) {
            MenuItemBadge.getBadgeTextView(menuItemNewFeature).setHighLightMode(true);
        } else {
            MenuItemBadge.getBadgeTextView(menuItemNewFeature).clearHighLightMode();
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemNewFeature = menu.findItem(R.id.menu_new_feature);
        toggleRedIconInNewFeatureMenu(menuItemNewFeature);
        return super.onPrepareOptionsMenu(menu);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu_message) {
            Toast.makeText(this, "Click Message Menu!", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_shopcart) {
            Toast.makeText(this, "Click ShopCart Menu!", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_new_feature) {
            Toast.makeText(this, "Toggle Red Icon in New Feature Menu!", Toast.LENGTH_LONG).show();
            //noinspection RestrictedApi
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
