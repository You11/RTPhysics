package ru.b7.rtphysics.ScreenElements;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Nikita on 07.11.2015.
 */
public class TagSetter {

    public final int id;
    public final String NameOfButton;
    //in other class
    Context context;
    public  boolean isFavorites;
    public final String CurrentTable;


    //private constructor for Screen Elements  processing
    TagSetter(int IdInTable, String Name, boolean isFavorites, String CurrentTable,Context context){

        this.context=context;
        this.id=IdInTable;
        this.NameOfButton=Name;
        this.isFavorites=isFavorites;
        this.CurrentTable =CurrentTable;

    }

    //default tag for open start menu
    public TagSetter(){

        id=0;
        NameOfButton="";
        isFavorites=false;
        this.CurrentTable ="";

    }
}
