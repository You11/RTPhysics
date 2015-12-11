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

    List<Formula> Formulas;
    int id = 0;


    public MenuFormulas(BaseActivity parentContext,List<Formula> formulas) {

        super(parentContext, "Formula");//set listener and property for tag
        Formulas = formulas;
    }
    
    @Override
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

        ImageView imageView = new ImageView(parentContext);
        imageView = (ImageView) setButtonParams(imageView, info, onClickListener);

        int srcId = parentContext.getResources().getIdentifier(item.GetImageName(), "drawable", parentContext.getPackageName());
        imageView.setImageResource(srcId);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageView.setId(id);
        id++;

        imageView.setOnClickListener(onClickListener);

        lay.addView(imageView);

        return lay;
    }
}
