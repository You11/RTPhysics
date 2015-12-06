package ru.b7.rtphysics.Handbook;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.ScreenElements.MenuSection;

/**
 * Opens handbook menu from xml resources.
 */
public class HandbookSectionFragment extends Fragment {

    private static MenuSection Decor;

    public static HandbookSectionFragment HandbookInitialize(HandbookMenuActivity activity) {
        Decor = new MenuSection(activity);
        return new HandbookSectionFragment();

    }
    public static HandbookSectionFragment HandbookInitialize(BaseActivity activity, List<String> names){

        Decor = new MenuSection(activity,names);
        return new HandbookSectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return Decor.buildMainLayout();
    }
}
