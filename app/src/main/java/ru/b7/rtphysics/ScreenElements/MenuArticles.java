package ru.b7.rtphysics.ScreenElements;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;

/**
 * Created by Nikita on 08.11.2015.
 */
 public class MenuArticles extends Style_Global {

    private TagSetter currentTag;

    public MenuArticles(BaseActivity activity, TagSetter tag) {
        super(activity,"Articles");//set listener and property for tag
        currentTag=tag;
    }

    public View BuildMainLayout(){

        //передаем id от предыдущей кнопки
        List<View> menuItems= BuildWithFavorites(Finder.ClickFinder.LoadList_Articles(currentTag.id));
        return BuildWidgetsOnLay(menuItems);

    }

    //Button plus Button_Favorites
    private List<View> BuildWithFavorites(List<Map<String, String>> tableLines){

        List<View> articlesButtons= new ArrayList<>();

        //Convert to FormulaButton
        for(Map<String,String> item : tableLines){

            View buttonsGroup = ButtonArticles(id_for_R++,id_for_R++,item);
            articlesButtons.add(buttonsGroup);

        }
        return articlesButtons;
    }

    private LinearLayout ButtonArticles(int ID_M,int ID_F,Map<String,String> item){

        LinearLayout lay = Style_Horizontal(CreateLinearLayout(0));

        Button buttonMain = new Button(parentContext);
        View buttonFavorite = new Button(parentContext);
        //style


        //initialization
        buttonMain = (Button)mainArticlesButton(buttonMain,ID_M, item);
        buttonFavorite = Button_SetParams(buttonFavorite, ID_F, item, ActivityListener);

        //params
        buttonMain.setLayoutParams(Style_Horizontal(CreateLinearLayout(1)).getLayoutParams());
        buttonFavorite =  Style_Favorites((Button)buttonFavorite);

        lay.addView(buttonFavorite);
        lay.addView(buttonMain);
        //lay.removeView(lay);
        return lay;

    }

    private View mainArticlesButton(Button button,int id,Map<String,String> item){

        button= (Button)Button_SetParams(button, id, item, ActivityListener);
        button.setText(item.get("Name"));

        return button;

    }
}
