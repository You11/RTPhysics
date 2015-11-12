package ru.b7.rtphysics.ScreenElements;

import android.util.Log;
import android.view.View;
import android.widget.Button;
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
public class MenuSection extends Style_Global {


    public MenuSection(BaseActivity activity) {
        super(activity,"Section");//set listener and property for tag
    }

    public View BuildMainLayout(){
        return BuildWidgetsOnLay(BuildMenuButtons(Finder.Get_All("Section")));

    }

    //Methods for construct lines on main menu(contains views or views constructions)
    private List<View> BuildMenuButtons(List<Map<String, String>> tableLines){

        List<View> MenuButtons= new ArrayList<>();

        for(Map<String,String> item : tableLines){

            View menuButton=menuSectionButton(id_for_R++, item);
            MenuButtons.add(menuButton);

        }

        return MenuButtons;
    }

    private View menuSectionButton(int id,Map<String,String> item){

        Button button = new Button(parentContext);
        //style

        //end
        button=(Button)Button_SetParams(button, id, item,ActivityListener);

        button.setText(item.get("Name"));

        return button;

    }

}

