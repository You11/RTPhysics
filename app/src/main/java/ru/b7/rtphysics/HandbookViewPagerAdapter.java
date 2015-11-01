package ru.b7.rtphysics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Adapter, which is needed for displaying information on the tabs.
 */
public class HandbookViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numberOfTabs;

    public HandbookViewPagerAdapter(FragmentManager fm, CharSequence titles[], int numberOfTabs) {
        super(fm);

        this.titles = titles;
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return (new HandbookTabArticleFragment());
        } else {
            return (new HandbookTabFormulasFragment());
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
