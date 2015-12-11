package ru.b7.rtphysics.Handbook;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.ScreenElements.MenuParagraph;
import ru.b7.rtphysics.ScreenElements.TagSetter;


/**
 * First tab, article fragment.
 */
public class HandbookTabArticleFragment extends android.support.v4.app.Fragment {

    static MenuParagraph Decor;

    public static HandbookTabArticleFragment Initialize(BaseActivity activity,TagSetter tag){
        Decor = new MenuParagraph(activity, tag);
        return new HandbookTabArticleFragment();

    }

    public static HandbookTabArticleFragment Initialize(BaseActivity activity ,String content){

        Decor = new MenuParagraph(activity,content);
        return new HandbookTabArticleFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return Decor.buildMainLayout();
    }



}
