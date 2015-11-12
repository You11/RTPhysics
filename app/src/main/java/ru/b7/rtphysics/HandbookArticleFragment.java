package ru.b7.rtphysics;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.b7.rtphysics.ScreenElements.MenuArticles;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Created by Nikita on 11.11.2015.
 */
public class HandbookArticleFragment extends Fragment {


    private static MenuArticles Decor;

    public static HandbookArticleFragment HandbookInitialize(TagSetter tag, BaseActivity activity){

        Decor = new MenuArticles(activity,tag);
        return new HandbookArticleFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      return Decor.BuildMainLayout();

    }
}
