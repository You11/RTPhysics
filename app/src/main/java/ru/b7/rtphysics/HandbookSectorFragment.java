package ru.b7.rtphysics;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.b7.rtphysics.ScreenElements.MenuSection;

/**
 * Opens handbook menu from xml resources.
 */
public class HandbookSectorFragment extends Fragment{

    private static MenuSection Decor;

    public static HandbookSectorFragment HandbookInitialize(HandbookMenuActivity activity) {
        Decor = new MenuSection(activity);
        return  new HandbookSectorFragment();
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
