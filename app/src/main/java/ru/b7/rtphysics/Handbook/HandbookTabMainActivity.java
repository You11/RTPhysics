package ru.b7.rtphysics.Handbook;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.FavoritesEditor;
import ru.b7.rtphysics.R;
import ru.b7.rtphysics.ScreenElements.FavoritesClickListener;
import ru.b7.rtphysics.ScreenElements.TagSetter;
import ru.b7.rtphysics.google.SlidingTabLayout;

/**
 * Tab Activity.
 */
public class HandbookTabMainActivity extends BaseActivity implements View.OnClickListener {

    public SlidingTabLayout tabs;
    private CharSequence titles[] = {"Статья", "Формулы"};

    int numberOfTabs = 2;
    static TagSetter tagSetter;
    static int LowId;


    @Override
    public void onClick(View v) {

    }

    public static void HandbookTabCreate(TagSetter tag)
    {
        tagSetter = tag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_handbook_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HandbookViewPagerAdapter adapter = new HandbookViewPagerAdapter(getSupportFragmentManager(),
                titles, numberOfTabs, this, tagSetter);


        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.handbook_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                View v = HandbookMenuActivity.getLastView();
                FavoritesClickListener.onClickFavorite(v);

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
