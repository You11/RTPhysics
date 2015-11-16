package ru.b7.rtphysics;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import ru.b7.rtphysics.ScreenElements.TagSetter;
import ru.b7.rtphysics.google.SlidingTabLayout;

/**
 * Tab Activity.
 */
public class HandbookTabMainActivity extends BaseActivity implements View.OnClickListener {

    ViewPager pager;
    HandbookViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence titles[] = {"Статья", "Формулы"};
    int numberOfTabs = 2;
    static TagSetter tagSetter;

    @Override
    public void onClick(View v) {

    }

    public static void HandbookTabCreate(TagSetter tag){
        tagSetter=tag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_handbook_main);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new HandbookViewPagerAdapter(getSupportFragmentManager(),
                titles, numberOfTabs, this,tagSetter);


        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);
    }
}
