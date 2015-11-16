package ru.b7.rtphysics;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.b7.rtphysics.ScreenElements.MenuArticles;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Second tab, formulas fragment.
 */
public class HandbookTabFormulasFragment extends android.support.v4.app.Fragment {

    static MenuArticles Decor;

    public static HandbookTabFormulasFragment Initialize(
            BaseActivity Activity, TagSetter tag)
    {
       Decor=new MenuArticles(Activity, tag);
       return new HandbookTabFormulasFragment();
    }


    @Override
      public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return Decor.BuildMainLayout();
    }
}
