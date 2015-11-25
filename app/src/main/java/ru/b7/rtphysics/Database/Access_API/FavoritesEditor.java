package ru.b7.rtphysics.Database.Access_API;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.Database.DatabaseCreator;
import ru.b7.rtphysics.Handbook.HandbookMenuActivity;

/**
 * Created by Nikita on 01.11.2015.
 */

public class FavoritesEditor {

    static public List<Map<String,String>> GetAll(){
        return Finder.Get_All("Articles", null, "IsFavorites = ?", new String[]{"1"}, null, null, null);
    }

    static public void addArticlesById(int id){

        ContentValues cv = new ContentValues();

        cv.put("IsFavorites", 1);

        DatabaseCreator.myDB = HandbookMenuActivity.DataBase.getWritableDatabase();
        DatabaseCreator.myDB.update("Articles", cv, "_id=?", new String[]{String.valueOf(id)});

        DatabaseCreator.myDB.close();
    }

    static public void deleteArticlesById(int id){

        ContentValues cv = new ContentValues();
        cv.put("IsFavorites", 0);
        DatabaseCreator.myDB = HandbookMenuActivity.DataBase.getWritableDatabase();

        DatabaseCreator.myDB.update("Articles", cv, "_id=?", new String[]{String.valueOf(id)});

        DatabaseCreator.myDB.close();
    }

    static public void AddSomething_ByName(String TableName,String Name){
        DatabaseCreator.myDB = HandbookMenuActivity.DataBase.getWritableDatabase();
        DatabaseCreator.myDB.rawQuery(
                "UPDATE " + TableName + " SET IsFavorites = 1 WHERE Name = ?",
                new String[]{String.valueOf(Name)}
        );

        DatabaseCreator.myDB.close();
    }

    static public void DeleteSomething_ByName(String TableName,String Name){
        DatabaseCreator.myDB = HandbookMenuActivity.DataBase.getWritableDatabase();
        DatabaseCreator.myDB.rawQuery(
                "UPDATE " + TableName + " SET IsFavorites = 0 WHERE Name = ?",
                new String[]{String.valueOf(Name)}
        );

        DatabaseCreator.myDB.close();

    }
}
