package ru.b7.rtphysics.ScreenElements;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;

/**
 * Created by Nikita on 11.11.2015.
 */
public class MenuFormulas extends StyleGlobal {

    TagSetter currentTag;
    List<Map<String,String>> FormulasInfo;
    List<Formula> Formulas;


    public MenuFormulas(BaseActivity parentContext,
                        TagSetter tag,
                        List<Formula> formulas) {

        super(parentContext, "Formula");//set listener and property for tag
        Formulas = formulas;
        currentTag = tag;
    }

    public View buildMainLayout() {

        List<View> menuItems = BuildFormulas(Formulas);
        return buildScrollWidgetsOnLay(menuItems, "Formulas");
    }

    private List<View> BuildFormulas(List<Formula> items){

        List<View> FormulaLines = new ArrayList<>();

        for (Formula item : items) {

            Map<String,String> info = Finder.GetByID("Formula", Integer.parseInt(item.GetID()));
            View formulaLine = formulaElement(item, info);
            FormulaLines.add(formulaLine);
        }

        return FormulaLines;
    }

    private View formulaElement(Formula item, Map<String,String> info){

        LinearLayout lay = createLinearLayout(0);
        lay.setOrientation(LinearLayout.HORIZONTAL);

        ImageView button = new ImageView(parentContext);
        button = (ImageView) setButtonParams(button, info, onClickListener);

        int idsrc = parentContext.getResources().getIdentifier(item.GetImageName(), "drawable", parentContext.getPackageName());
        button.setImageResource(idsrc);

        button.setLayoutParams(styleHorizontal(createLinearLayout(1)).getLayoutParams());
        lay.addView(button);

        return lay;
    }
}
