package ru.b7.rtphysics.Handbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Adapter, which is needed for displaying information on the tabs.
 */
public class HandbookViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[];
    private int numberOfTabs;
    private BaseActivity Activity;
    private TagSetter tag;
    private List<Formula> FormulasOnPage= new ArrayList<>();
    private String content;

    public HandbookViewPagerAdapter(FragmentManager fm, CharSequence titles[], int numberOfTabs, BaseActivity Activity, TagSetter tag,String content){
        super(fm);
        this.titles = titles;
        this.numberOfTabs = numberOfTabs;
        this.Activity = Activity;
        this.tag = tag;
        this.content=content;

        InitializingFormulas();

    }


    private void InitializingFormulas(){
            FormulasOnPage = Finder.ClickFinder.LoadList_Formula(tag.id);
    }


    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return  content==null
                    ? (HandbookTabArticleFragment.Initialize(Activity, tag))
                    : (HandbookTabArticleFragment.Initialize(Activity,content));
        } else {
            return (HandbookTabFormulasFragment.Initialize(Activity, FormulasOnPage));
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
