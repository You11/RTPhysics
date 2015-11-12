package ru.b7.rtphysics;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.b7.rtphysics.ScreenElements.MenuArticles;
import ru.b7.rtphysics.ScreenElements.TagSetter;


/**
 * First tab, article fragment.
 */
public class HandbookTabArticleFragment extends android.support.v4.app.Fragment {

    static MenuArticles Decor;

    public static HandbookTabArticleFragment Initialize(BaseActivity Activity,
                                                        TagSetter tag){
      Decor = new MenuArticles(Activity,tag);
      return new  HandbookTabArticleFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return Decor.BuildMainLayout();
    }
}
