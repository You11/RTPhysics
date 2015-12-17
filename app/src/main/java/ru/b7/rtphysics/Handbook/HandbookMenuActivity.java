package ru.b7.rtphysics.Handbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.BaseActivity;
import ru.b7.rtphysics.Database.Access_API.Finder;
import ru.b7.rtphysics.Database.DatabaseCreator;
import ru.b7.rtphysics.R;
import ru.b7.rtphysics.ScreenElements.TagSetter;
import ru.b7.rtphysics.Search.ISearchPagesNames;
import ru.b7.rtphysics.Search.SearchDialog;

/**
 * Starting activity. Fragments is needed for handbook menu. Navigation drawer enabled. Back button
 * from this activity exits program.
 */

public class HandbookMenuActivity extends BaseActivity implements ISearchPagesNames {

    public static DatabaseCreator DataBase;
    protected static List<String> articlesNamesPool= new ArrayList<>();
    protected static List<String> sectionNamesPool = new ArrayList<>();
    protected static String request =null;
    private static View lastView;
    private static View sectionView;
    boolean currentView = false;
    private TagSetter currentTag;

    public static View getLastView() {
        return lastView;
    }

    public void setLastView(View v) {
        lastView = v;
    }

    public static View getSectionView() {
        return sectionView;
    }

    public void setSectionView(View v) {sectionView = v; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.handbook_menu_startscreen);
        DataBase = new DatabaseCreator(this);

        //Load section menu
        currentTag = new TagSetter();

        getFragmentManager().beginTransaction()
                .replace(R.id.menu_frame_layout, HandbookSectionFragment.HandbookInitialize(this))
                .commit();
    }

    @Override
    public void onClick(View v) {

        currentTag = (TagSetter) v.getTag();

        if (v.getId() == R.id.favoriteSection) {
            HandbookArticleFragment initializer =
                    HandbookArticleFragment.HandbookInitialize(new TagSetter(), true, this, articlesNamesPool);

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

        switch (currentTag.currentTable) {
            case "StartTable":
                getFragmentManager().beginTransaction()
                        .replace(R.id.menu_frame_layout,
                                HandbookSectionFragment.HandbookInitialize(this, sectionNamesPool))
                        .commit();
                break;

            case "Section": //if it was section => load articles (first properties on lifecycle always have "Section" )
                setSectionView(v);
                getFragmentManager().beginTransaction()
                        .replace(R.id.menu_frame_layout,
                                HandbookArticleFragment.HandbookInitialize(currentTag, false, this, articlesNamesPool))
                        .commit();
                currentView = true;

                break;

            case "Articles": //Go to next layout(Formulas and Articles paragraph)
                HandbookTabMainActivity.HandbookTabCreate(currentTag, request);
                setLastView(v);
                Intent intent = new Intent(this, HandbookTabMainActivity.class);
                this.startActivity(intent);
                this.currentTag = new TagSetter(currentTag.id, currentTag.nameOfButton, currentTag.isFavorites, "Section", this);
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

            currentTag = new TagSetter();
            ResetPage();
            currentView = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_search:
                if (isSearchRun) {
                    item.setIcon(R.mipmap.search_ic);

                    request = null;
                    isSearchRun = false;
                    articlesNamesPool =new ArrayList<>();
                    sectionNamesPool = new ArrayList<>();
                    ResetPage();

                    return true;

                } else {
                    item.setIcon(R.drawable.closeicon);
                    isSearchRun = true;
                    new SearchDialog(this);

                    return true;
                }
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public List<Map<String, String>> GetSearchSpace() {

        switch (currentTag.currentTable) {
            case "Section":
                return Finder.ClickFinder.LoadList_Articles(currentTag.id);

            case "StartTable":
                return Finder.Get_All("Articles");

        }
        System.out.println(currentTag.currentTable);
        return null;
    }

    @Override
    public void SearchCallBack(SearchDialog dialog) {

        boolean isEmpty = true;
        request = dialog.GetRequest();

        for (String name: dialog.GetAllSuccessArticles()) {
            if(name!=null) {
                isEmpty = false;
                break;
            }
        }

        if(isEmpty){
            Toast.makeText(this,"Текст не найден",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Совпадение!", Toast.LENGTH_LONG).show();

            if (currentTag.currentTable.equals("StartTable")) {

                HandbookMenuActivity.sectionNamesPool = dialog.GetAllSuccessSection();
                HandbookMenuActivity.articlesNamesPool = dialog.GetAllSuccessArticles();
                ResetPage();

            } else {

                HandbookMenuActivity.sectionNamesPool = dialog.GetAllSuccessSection();
                HandbookMenuActivity.articlesNamesPool = dialog.GetAllSuccessArticles();

                ResetPage();
            }
        }

    }

    private void ResetPage(){
        Button reset = new Button(this);
        reset.setTag(currentTag);
        onClick(reset);

    }
}