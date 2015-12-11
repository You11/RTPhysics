package ru.b7.rtphysics.ScreenElements;

import android.content.Context;

/**
 * Created by Nikita on 07.11.2015.
 */
public class TagSetter {

    public final int id;
    public final String nameOfButton;
    //in other class
    Context context;
    public  boolean isFavorites;
    public final String currentTable;


    //private constructor for Screen Elements  processing
    public TagSetter(int idInTable, String name, boolean isFavorites, String currentTable, Context context) {

        this.context = context;
        this.id = idInTable;
        this.nameOfButton = name;
        this.isFavorites = isFavorites;
        this.currentTable = currentTable;

    }

    //default tag for open start menu
    public TagSetter() {
        id = 0;
        nameOfButton = null;
        isFavorites = false;
        this.currentTable = "StartTable";

    }
}
