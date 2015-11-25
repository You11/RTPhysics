package ru.b7.rtphysics.Handbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Adapter, which is needed for displaying information on the tabs.
 */
public class HandbookViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numberOfTabs;
    BaseActivity Activity;
    TagSetter tag;
    List<Formula> FormulasOnPage= new ArrayList<>();
    List<Map<String,String>> FormulasInfo=new ArrayList<>();

    public HandbookViewPagerAdapter(FragmentManager fm, CharSequence titles[], int numberOfTabs,
                                    BaseActivity Activity, TagSetter tag) {
        super(fm);

        this.titles = titles;
        this.numberOfTabs = numberOfTabs;
        this.Activity = Activity;
        this.tag = tag;
        InitializingFormulas();

    }

    private void InitializingFormulas(){
            FormulasOnPage = Finder.ClickFinder.LoadList_Formula(tag.id);
            FormulasInfo = GetInfoAboutFormulas(FormulasOnPage);
    }

    private List<Map<String,String>> GetInfoAboutFormulas(List<Formula> formulas){
        List<Map<String,String>> formulaInfo= new ArrayList<>();
        for(Formula item :formulas){

            Map<String,String> info= Finder.GetByID("Formula", Integer.parseInt(item.GetID()));

            formulaInfo.add(info);

        }

        return formulaInfo;
    }


    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return (HandbookTabArticleFragment.Initialize(Activity, tag, FormulasInfo));

        } else {
            return (HandbookTabFormulasFragment.Initialize(Activity, tag, FormulasOnPage));

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
