package ru.b7.rtphysics.ScreenElements;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;

/**
 * Created by Nikita on 06.11.2015.
 */
abstract class StyleGlobal {

    protected final Context parentContext;
    protected final BaseActivity currentActivity;
    private String currentTable;
    static int idForR = 0;
    private FavoritesClickListener favoritesClickListener = new FavoritesClickListener();
    public OnClickListener onClickListener;


    abstract public View buildMainLayout();

    protected StyleGlobal(BaseActivity parentContext, String currentTable) {
        this.currentTable = currentTable;
        currentActivity=parentContext;
        this.parentContext = parentContext;
        onClickListener = parentContext;
    }

    protected LinearLayout buildScrollWidgetsOnLay(List<View> viewList, String type){

        LinearLayout lay = createLinearLayout(0);

        LinearLayout mainLay = mainLinearLayout();
        mainLay.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(parentContext);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if (type.equals("Formulas")) {
            for (View item : viewList) {
                item.setPadding(0, 20, 0, 0);
                mainLay.addView(item);
            }
        } else {
            for (View item : viewList) {
                mainLay.addView(item);
            }
        }

        scroll.addView(mainLay);

        lay.addView(scroll);

        return lay;
    }

    protected LinearLayout buildWidgetsOnLay(List<View> viewList){

        LinearLayout mainLay = mainLinearLayout();
        mainLay.setOrientation(LinearLayout.VERTICAL);

        for(View item : viewList){
            //searching column in table by his name
            mainLay.addView(item, mainLay.getLayoutParams());
        }

        return mainLay;
    }

    protected LinearLayout createLinearLayout(int weight){

        LinearLayout lay = new LinearLayout(this.parentContext);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        weight
                );

        lay.setLayoutParams(params);

        return lay;
    }

    protected LinearLayout styleHorizontal(LinearLayout layout){

        layout.setOrientation(LinearLayout.HORIZONTAL);
        return layout;

    }

    //main layout on fragment
    private LinearLayout mainLinearLayout(){

        LinearLayout lay = createLinearLayout(0);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)lay.getLayoutParams();
        params.setMargins(100, 2, 100, 2);
        lay.setLayoutParams(params);

        return lay;
    }

    protected View styleFavorites(ImageButton button){

        button.setLayoutParams(styleHorizontal(createLinearLayout(8)).getLayoutParams());
        button.setOnClickListener(favoritesClickListener);

        return button;
    }

    protected View setButtonParams(View button, Map<String, String> item, OnClickListener activityListener){

        button.setId(idForR++);
        button.setTag(SetTag(item));
        button.setOnClickListener(activityListener);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        button.setLayoutParams(layoutParams);

        return button;
    }

    private TagSetter SetTag(Map<String, String> item){

        boolean favorites = IsFavorite(item);

        return new TagSetter(
                Integer.parseInt(item.get("_id")),
                item.get("Name"),
                favorites,
                currentTable,
                parentContext
        );

    }

    private boolean IsFavorite(Map<String, String> item){
        return (item.containsKey("IsFavorites")&&item.get("IsFavorites").equals("1"));

    }
}
