package ru.b7.rtphysics;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;

/**
 * Search Activity. Navigation drawer enabled, back button goes to HandbookMenuActivity.
 */
public class SearchMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_SEARCH;
    }


    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);


    }

}
