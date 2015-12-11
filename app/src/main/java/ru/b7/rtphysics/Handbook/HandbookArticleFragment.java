package ru.b7.rtphysics.Handbook;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.FavoritesEditor;
import ru.b7.rtphysics.ScreenElements.MenuArticles;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Created by Nikita on 11.11.2015.
 */

public class HandbookArticleFragment extends Fragment {

    private static MenuArticles Decor;

    public static HandbookArticleFragment HandbookInitialize(TagSetter tag, Boolean isFavoriteSection, BaseActivity activity,List<String> names){

        if (isFavoriteSection) {
            Decor = new MenuArticles(activity);
        } else {
            Decor = new MenuArticles(activity, tag,names);
        }

        if (FavoritesEditor.GetAll() == null && isFavoriteSection)
            return null;

        return new HandbookArticleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return Decor.buildMainLayout();

    }
}
