package ru.b7.rtphysics.Database.Access_API;

import android.database.Cursor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.Database.DatabaseCreator;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;
import ru.b7.rtphysics.HandbookMenuActivity;


/**
 * Created by Nikita on 01.11.2015.
 */
     public class Finder {

        public static List<Map<String,String>> Get_All(String Table_name,
                String[] columns,
                String selection,
                String[] SelectionArgs,
                String groupBy,
                String having,
                String orderBy)
        {

            DatabaseCreator.myDB = HandbookMenuActivity.DataBase.getWritableDatabase();

            ////выходные данные : List строк, в каждой строке несколько таблиц в виде Map(/*имя таблицы*/,/*Значение*/)
            List<Map<String,String>> send = new ArrayList<Map<String,String>>();

            Cursor c = DatabaseCreator.myDB.query(Table_name, columns, selection, SelectionArgs, groupBy, having, orderBy);

            c.moveToFirst();

            int length= c.getColumnNames().length;

            do {
                Map<String,String> sept=new HashMap<String,String>();

                for (int i = 0; i < length; i++)
                {
                    sept.put(c.getColumnName(i), c.getString(i));
                }
                send.add(sept);

            } while (c.moveToNext());
            c.close();
            DatabaseCreator.myDB.close();
            return send;
        }

        public static List<Map<String,String>> Get_All(String TableName){
          return  Get_All(TableName,null,null,null,null,null,null);
        }
        //Получить  из всей таблицы несколько columns
        //возвращает 1 найденую строчку по id
        public static  Map<String,String> GetByID(String Table_name, int ID) {
            return Get_All(Table_name, null, "_id = ?", new String[]{Integer.toString(ID)}, null, null, null).get(0);
        }
        public static  Map<String,String> GetByName(String Table_name, String Name) {
            return Get_All(Table_name, null, "Name = ?", new String[]{Name}, null, null, null).get(0);
        }

       public static class ClickFinder {
           public static List<Map<String, String>> LoadList_Articles(int idOfClickedButton) {

               return Get_All("Articles",null,"Section_id = ?",new String[]{String.valueOf(idOfClickedButton)},null,null,null);

           }
           public static List<Formula> LoadList_Formula(int idOfClickedButton){

               List<Formula> menuList= new ArrayList<>();
               List<Map<String,String>> allFormulaId =
                       Get_All("Formula",null,"Articles_id = ?",new String[]{String.valueOf(idOfClickedButton)},null,null,null);

               for(Map<String,String> item :allFormulaId){
                   menuList.add(FormulaFinder.GetByID(Integer.parseInt(item.get("_id"))));
               }

               return menuList;

           }
       }
    }



