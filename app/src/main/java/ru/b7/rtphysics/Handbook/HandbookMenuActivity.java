package ru.b7.rtphysics.Handbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.DatabaseCreator;
import ru.b7.rtphysics.R;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Starting activity. Fragments is needed for handbook menu. Navigation drawer enabled. Back button
 * from this activity exits program.
 */


public class HandbookMenuActivity extends BaseActivity {

    public static DatabaseCreator DataBase;
    boolean currentView = false;
    private static View lastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.handbook_menu_startscreen);

        DataBase = new DatabaseCreator(this);

        //Load section menu
        getFragmentManager().beginTransaction()
                .replace(R.id.menu_frame_layout, HandbookSectorFragment.HandbookInitialize(this))
                .commit();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.favoriteSection) {
            HandbookArticleFragment initializer = HandbookArticleFragment.HandbookInitialize(
                    new TagSetter(), true, this);
            if (initializer == null) {
                Toast.makeText(this, "Список пуст", Toast.LENGTH_SHORT).show();
                return;
            }

            getFragmentManager().beginTransaction()
                    .replace(R.id.menu_frame_layout, initializer)
                    .commit();
            currentView = true;
            return;
        }

        TagSetter tag = (TagSetter) v.getTag();

        switch (tag.currentTable) {
            case "Section": //if it was section => load articles (first properties on lifecycle always have "Section" )
                getFragmentManager().beginTransaction()
                        .replace(R.id.menu_frame_layout, HandbookArticleFragment.HandbookInitialize(tag, false, this))
                        .commit();
                currentView = true;
                break;

            case "Articles": //Go to next layout(Formulas and Articles paragraph)
                HandbookTabMainActivity.HandbookTabCreate(tag);
                setLastView(v);
                Intent intent = new Intent(this, HandbookTabMainActivity.class);

                this.startActivity(intent);

                break;
        }
    }

    @Override
    protected int getNavDrawerItem() {
        return NAV_DRAWER_ITEM_HANDBOOK;
    }

    @Override
    public void onBackPressed() {

        if (!currentView) {
            moveTaskToBack(true);
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.menu_frame_layout, HandbookSectorFragment.HandbookInitialize(this))
                    .commit();
            currentView = false;
        }
    }

    public static View getLastView() {
        return lastView;
    }

    public void setLastView(View v) {
        lastView = v;
    }
}