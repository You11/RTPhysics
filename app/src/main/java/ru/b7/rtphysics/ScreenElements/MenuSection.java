package ru.b7.rtphysics.ScreenElements;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.R;

/**
 * Created by Nikita on 11.11.2015.
 */
public class MenuSection extends StyleGlobal {


    public MenuSection(BaseActivity activity) {
        super(activity, "Section");//set listener and property for tag
    }

    public View buildMainLayout(){

        return buildScrollWidgetsOnLay(BuildMenuButtons(Finder.Get_All("Section")), "Section");
    }

    //Methods for construct lines on main menu(contains views or views constructions)
    private List<View> BuildMenuButtons(List<Map<String, String>> tableLines){

        List<View> MenuButtons = new ArrayList<>();

        for (Map<String,String> item : tableLines){
            LinearLayout linearLayout = createLinearLayout(0);

            View menuButton = menuSectionButton(item);
            linearLayout.addView(menuButton);

            MenuButtons.add(linearLayout);
        }

        LinearLayout linearLayout = createLinearLayout(0);

        Button favoriteButton = new Button(parentContext);
        favoriteButton.setOnClickListener(onClickListener);
        favoriteButton.setText("Избранное");
        favoriteButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        favoriteButton.setId(R.id.favoriteSection);
        favoriteButton.setTextSize(20);

        linearLayout.addView(favoriteButton);

        MenuButtons.add(linearLayout);

        return MenuButtons;
    }

    private View menuSectionButton(Map<String,String> item){

        Button button = new Button(parentContext);

        button = (Button) setButtonParams(button, item, onClickListener);

        button.setText(item.get("Name"));
        button.setTextSize(20);

        return button;
    }
}
