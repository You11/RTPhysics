package ru.b7.rtphysics;

import android.os.Bundle;

/**
 * Starting activity. Fragments is needed for handbook menu. Navigation drawer enabled. Back button
 *  from this activity exits program.
 */

public class HandbookMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handbook_menu_startscreen);

        //Replaces empty default layout with fragments menu
        getFragmentManager().beginTransaction()
                .replace(R.id.menu_frame_layout, new HandbookMenuFragment()).commit();
    }

    @Override
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_HANDBOOK;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}