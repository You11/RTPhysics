package ru.b7.rtphysics.Handbook;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.ScreenElements.MenuParagraph;
import ru.b7.rtphysics.ScreenElements.TagSetter;


/**
 * First tab, article fragment.
 */
public class HandbookTabArticleFragment extends android.support.v4.app.Fragment {

    static MenuParagraph Decor;
    static List<Map<String,String>> FormulasInfo;

    public static HandbookTabArticleFragment Initialize(BaseActivity Activity,
                                                        TagSetter tag,
                                                        List<Map<String,String>> formulasInfo){

        Decor = new MenuParagraph(Activity, tag, formulasInfo);
        FormulasInfo = formulasInfo;
        return new HandbookTabArticleFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return Decor.buildMainLayout();
    }
}
