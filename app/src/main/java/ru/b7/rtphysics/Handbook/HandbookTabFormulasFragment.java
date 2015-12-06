package ru.b7.rtphysics.Handbook;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;
import ru.b7.rtphysics.ScreenElements.MenuFormulas;

/**
 * Second tab, formulas fragment.
 */
public class HandbookTabFormulasFragment extends android.support.v4.app.Fragment {

    static MenuFormulas Decor;

    public static HandbookTabFormulasFragment Initialize(
            BaseActivity Activity,
            List<Formula> formulas)
    {
        Decor = new MenuFormulas(Activity,  formulas);
        return new HandbookTabFormulasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return Decor.buildMainLayout();
    }
}
