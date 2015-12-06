package ru.b7.rtphysics.ScreenElements;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.FavoritesEditor;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.R;

/**
 * Created by Nikita on 08.11.2015.
 */

public class MenuArticles extends StyleGlobal {

    private TagSetter currentTag;
    private boolean isFavoriteSection = false;
    List<String> names = new ArrayList<>();

    public MenuArticles(BaseActivity activity) {
        super(activity, "Articles");
        isFavoriteSection = true;
        names = null;

    }

    public MenuArticles(BaseActivity activity,TagSetter tag, List<String> names){
        super(activity,"Articles");

        currentTag = tag;
        this.names= names;

    }



    public View buildMainLayout(){

        //передаем id от предыдущей кнопки
        List<View> menuItems;
        if (isFavoriteSection) {
            List<Map<String,String>> list = FavoritesEditor.GetAll();
            if (list == null) {
                return null;
            }
            menuItems = buildWithFavorites(list);

        } else {
            menuItems = buildWithFavorites(Finder.ClickFinder.LoadList_Articles(currentTag.id));
        }

        return buildScrollWidgetsOnLay(menuItems, "Articles");
    }

    //Button plus Button_Favorites
    private List<View> buildWithFavorites(List<Map<String, String>> tableLines){

        List<View> articlesButtons = new ArrayList<>();

        //Convert to FormulaButton
        for (Map<String,String> item : tableLines) {
            View buttonsGroup = ButtonArticles(item);
            articlesButtons.add(buttonsGroup);

        }

        return articlesButtons;
    }


    private LinearLayout ButtonArticles(Map<String,String> item){

        LinearLayout lay = styleHorizontal(createLinearLayout(0));

        Button buttonMain = new Button(parentContext);
        View buttonFavorite = new ImageButton(parentContext);

        //style

        //initialization

        buttonMain = (Button) mainArticlesButton(buttonMain,item);
        buttonFavorite = setButtonParams(buttonFavorite, item, onClickListener);

        //params
        buttonMain.setLayoutParams(styleHorizontal(createLinearLayout(1)).getLayoutParams());
        buttonFavorite =  styleFavorites((ImageButton) buttonFavorite);

        if(names!=null&&names.contains(buttonMain.getText()))
            buttonMain.setTextColor(ContextCompat.getColor(parentContext, R.color.SearchedTextColor));

        lay.addView(buttonFavorite);
        lay.addView(buttonMain);

        return lay;
    }



    private View mainArticlesButton(Button button, Map<String,String> item) {

        button = (Button) setButtonParams(button, item, onClickListener);
        button.setText(item.get("Name"));

        return button;
    }
}
