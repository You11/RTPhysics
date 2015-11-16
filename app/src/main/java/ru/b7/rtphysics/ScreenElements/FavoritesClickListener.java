package ru.b7.rtphysics.ScreenElements;

import android.view.View;
import android.widget.Toast;

import ru.b7.rtphysics.Database.Access_API.FavoritesEditor;

/**
 * Created by Nikita on 08.11.2015.
 */
class FavoritesClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        TagSetter tag = (TagSetter) v.getTag();

        if (tag.isFavorites) {

            FavoritesEditor.DeleteSomething_ById(tag.CurrentTable, tag.id);
            tag.isFavorites=false;
            v.setTag(tag);

            Toast toast = Toast.makeText(
                    tag.context,
                    "Элемент удален",
                    Toast.LENGTH_SHORT
            );

            toast.show();

        } else {

            FavoritesEditor.AddSomething_ById(tag.CurrentTable,tag.id);
            tag.isFavorites=true;
            v.setTag(tag);


            Toast toast = Toast.makeText(
                    tag.context,
                    "Элемент добавлен в избранное",
                    Toast.LENGTH_SHORT
            );

            toast.show();

        }

    }
}



