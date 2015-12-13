package ru.b7.rtphysics;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.b7.rtphysics.Calculator.CalculatorMainActivity;
import ru.b7.rtphysics.Handbook.HandbookMenuActivity;

/**
 * Base class for all activities in project
 */
abstract public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    /**
     *  Navigation drawer constants, invalid is a default value for every activity.
     *  [0..3] is also elements of array with navigation drawer strings.
     */
    protected final int NAV_DRAWER_ITEM_INVALID = -1;

    protected final int NAV_DRAWER_ITEM_HANDBOOK = 0;

    protected final int NAV_DRAWER_ITEM_CALCULATOR = 1;

    protected final int NAV_DRAWER_ITEM_SEARCH = 3;
    //navigation drawer menu except exit, order is necessary
    private final int[] NAV_DRAWER_LIST = new int[]{
        R.id.nav_handbook,
        R.id.nav_calculator,
        R.id.nav_search
    };
    public boolean isSearchRun = false;
    //handbook is our starting activity
    private int item = NAV_DRAWER_LIST[0];

    private Toolbar toolbar;

    /**
     * Slightly less to write in main activities. Layout and toolbar setup in one method,
     * since current toolbar for now is made for all activities.
     */
    @Override
    public void setContentView(int layoutID) {
        super.setContentView(layoutID);
        getActionBarToolbar();
    }

    protected Toolbar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            setSupportActionBar(toolbar);
        }
        return toolbar;
    }

    @Override
    public abstract void onClick(View v);

    @Override
    protected void onResume() {
        super.onResume();
        setupNavDrawer();
    }

    //For navigation drawer start
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    //Override this in your activity if you need to have navigation drawer.
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_INVALID;
    }

    private void setupNavDrawer() {
        int item = getNavDrawerItem();

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            return;
        }

        if (item == NAV_DRAWER_ITEM_INVALID) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
            return;
        } else {
            setMenuId(NAV_DRAWER_LIST[item]);
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.nav_drawer_menu_text));
        navigationView.setItemTextAppearance(R.style.NavDrawerMenuTextStyle);

        navigationView.getMenu().getItem(item).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getMenuId() {
        return item;
    }

    private void setMenuId(int item) {
        this.item = item;
    }

    //Navigation in drawer
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            return false;
        }

        int id = item.getItemId();

        //If user chose to open already present activity
        if (id == getMenuId()) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        setMenuId(id);

        Intent intent;

        switch (id) {
            case R.id.nav_handbook: {
                intent = new Intent(this, HandbookMenuActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.nav_calculator: {
                intent = new Intent(this, CalculatorMainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.nav_search: {
                intent = new Intent(this, SearchMainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.nav_exit: {
                finish();
                break;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
