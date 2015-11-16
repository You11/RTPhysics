package ru.b7.rtphysics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import ru.b7.rtphysics.Database.DatabaseCreator;
import ru.b7.rtphysics.ScreenElements.TagSetter;

/**
 * Starting activity. Fragments is needed for handbook menu. Navigation drawer enabled. Back button
 * from this activity exits program.
 */


public class HandbookMenuActivity extends BaseActivity {

    public static DatabaseCreator DataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handbook_menu_startscreen);

        DataBase= new DatabaseCreator(this);

        //Load section menu
        getFragmentManager().beginTransaction()
                .replace(R.id.menu_frame_layout, HandbookSectorFragment.HandbookInitialize(this))
                .commit();
    }

    @Override
    public void onClick(View v) {
        TagSetter tag=(TagSetter)v.getTag();
        switch(tag.CurrentTable) {

            case "Section": //Load ar
                getFragmentManager().beginTransaction()
                        .replace(R.id.menu_frame_layout, HandbookArticleFragment.HandbookInitialize(tag, this))
                        .commit();
                break;

            case "Articles"://Go to next layout
                HandbookTabMainActivity.HandbookTabCreate(tag);
                Intent intent =new Intent(this,HandbookTabMainActivity.class);
                this.startActivity(intent);
                break;
        }

    }

    @Override
    protected int getNavDrawerItem() {
        return  NAV_DRAWER_ITEM_HANDBOOK;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);

    }
}