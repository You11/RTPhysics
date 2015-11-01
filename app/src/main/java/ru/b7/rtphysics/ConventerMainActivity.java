package ru.b7.rtphysics;

import android.os.Bundle;
import android.support.v4.app.NavUtils;

/**
 * Created by user on 01.11.2015.
 */
public class ConventerMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conventer_main);
    }

    @Override
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_CONVENTER;
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
