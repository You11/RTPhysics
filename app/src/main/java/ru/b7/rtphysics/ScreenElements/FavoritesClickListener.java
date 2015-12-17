package ru.b7.rtphysics.ScreenElements;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import ru.b7.rtphysics.Database.Access_API.FavoritesEditor;
import ru.b7.rtphysics.R;

/**
 * Created by Nikita on 08.11.2015.
 */

public class FavoritesClickListener implements View.OnClickListener {

    public static void onClickFavorite(View v) {


        TagSetter tag = (TagSetter) v.getTag();

        if(v instanceof ImageButton) {

            ImageButton button = (ImageButton) v;
            Log.d("hello", "Wow");
            if (tag.isFavorites) {
                FavoritesEditor.deleteArticlesById(tag.id);

                tag.isFavorites = false;
                button.setImageDrawable(tag.context.getResources().getDrawable(R.mipmap.fav_ic));

                v.setTag(tag);
                Toast.makeText(tag.context, "Элемент удален", Toast.LENGTH_SHORT).show();

            } else {

                FavoritesEditor.addArticlesById(tag.id);

                tag.isFavorites = true;
                button.setImageDrawable(tag.context.getResources().getDrawable(R.mipmap.fav_ic_true));

                v.setTag(tag);
                Toast.makeText(tag.context, "Элемент добавлен в избранное", Toast.LENGTH_SHORT).show();
            }
        }else {

            Log.d("hello", "Wow");
            if (tag.isFavorites) {
                FavoritesEditor.deleteArticlesById(tag.id);

                tag.isFavorites = false;

                v.setTag(tag);
                Toast.makeText(tag.context, "Элемент удален", Toast.LENGTH_SHORT).show();

            } else {

                FavoritesEditor.addArticlesById(tag.id);

                tag.isFavorites = true;

                v.setTag(tag);
                Toast.makeText(tag.context, "Элемент добавлен в избранное", Toast.LENGTH_SHORT).show();


            }
        }

    }

    @Override
    public void onClick(View v) {

        onClickFavorite(v);
    }


}




