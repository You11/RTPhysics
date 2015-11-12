package ru.b7.rtphysics.ScreenElements;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;

/**
 * Created by Nikita on 06.11.2015.
 */
class Style_Global {

    Context parentContext;
    String CurrentTable;
    static int id_for_R = 0;
    FavoritesClickListener FavoriteListener = new  FavoritesClickListener();
    OnClickListener ActivityListener;


    public Style_Global(BaseActivity parentContext,String currentTable ){

        this.CurrentTable=currentTable;
        this.parentContext = parentContext;
        ActivityListener = parentContext;

    }

    public LinearLayout BuildWidgetsOnLay(List<View> viewList){

        LinearLayout mainLay = LinearStyleLayout();
        mainLay.setOrientation(LinearLayout.VERTICAL);
        for(View item : viewList){
            //searching column in table by his name
            mainLay.addView(item, mainLay.getLayoutParams());
        }

        return mainLay;
    }

    public LinearLayout CreateLinearLayout(int weight){

        LinearLayout lay = new LinearLayout(this.parentContext);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        weight
                );
        lay.setLayoutParams(params);

        return lay;

    }

    public LinearLayout Style_Horizontal(LinearLayout layout){

        layout.setOrientation(LinearLayout.HORIZONTAL);
        return layout;

    }

    //main layout on fragment
    private LinearLayout LinearStyleLayout(){

        LinearLayout lay= CreateLinearLayout(0);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)lay.getLayoutParams();

        params.setMargins(100, 2, 100, 2);
        lay.setLayoutParams(params);

        return lay;
    }

    public View Style_Favorites(Button button){

        button.setLayoutParams(Style_Horizontal(CreateLinearLayout(8)).getLayoutParams());
        button.setOnClickListener(FavoriteListener);


        return button;
    }

    public View Button_SetParams(View button, int id, Map<String, String> item, OnClickListener activityListener){

        button.setId(id);
        button.setTag(SetTag(item));
        button.setOnClickListener(activityListener);

        return button;
    }

    public TagSetter SetTag(Map<String,String> item){

        boolean favorites = IsFavorite(item);

        return new TagSetter(
                Integer.parseInt(item.get("_id")),
                item.get("Name"),
                favorites,
                CurrentTable,
                parentContext
        );

    }

    public boolean IsFavorite(Map<String,String> item){
        return (item.containsKey("IsFavorites")&&item.get("IsFavorites").equals("1"));

    }
}
