package ru.b7.rtphysics;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Adapter, which is needed for displaying information on the tabs.
 */
public class HandbookViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numberOfTabs;
    BaseActivity Activity;
    TagSetter tag;

    public HandbookViewPagerAdapter(FragmentManager fm, CharSequence titles[], int numberOfTabs,
                                    BaseActivity Activity, TagSetter tag) {
        super(fm);

        this.titles = titles;
        this.numberOfTabs = numberOfTabs;
        this.Activity=Activity;
        this.tag=tag;

    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return (HandbookTabArticleFragment.Initialize(Activity, tag));
        } else {
            return (HandbookTabFormulasFragment.Initialize(Activity, tag));
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
