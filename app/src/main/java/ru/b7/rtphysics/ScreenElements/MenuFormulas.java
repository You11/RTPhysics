package ru.b7.rtphysics.ScreenElements;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;

/**
 * Created by Nikita on 11.11.2015.
 */
public class MenuFormulas extends Style_Global {

    TagSetter currentTag;

    public MenuFormulas(BaseActivity parentContext,TagSetter tag) {
        super(parentContext, "Formula");//set listener and property for tag

        currentTag=tag;
    }

    private List<View> BuildFormulas(List<Formula> items ){

        List<View> FormulaLines= new ArrayList<>();

        for(Formula item :items){

            Map<String,String> info= Finder.GetByID("Formula", Integer.parseInt(item.GetID()));
            View formulaLine = formulaElement(item, id_for_R++, info);
            FormulaLines.add(formulaLine);

        }

        return FormulaLines;
    }

    public View formulaElement(Formula item,int id,Map<String,String> info){


        LinearLayout lay = CreateLinearLayout(0);

        Button button = new Button(parentContext);
        TextView text= new TextView(parentContext);
        //style

        //end
        button = (Button) this.Button_SetParams(button,id, info, ActivityListener);

        button.setText(item.GetName());
        text.setText(item.GetInfo());

        lay.addView(button);
        lay.addView(text);

        return new View(parentContext);
    }

    public View paragraphElement(String paragraph){
        return new View(parentContext);

    }


}
