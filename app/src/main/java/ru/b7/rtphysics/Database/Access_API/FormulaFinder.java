package ru.b7.rtphysics.Database.Access_API;


import android.database.CursorIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.Database.InstancesInTables.Constant;
import ru.b7.rtphysics.Database.InstancesInTables.Formula;
import ru.b7.rtphysics.Database.InstancesInTables.Sign;

/**
 * Created by Nikita on 01.11.2015.
 */

    //this ApiUnit can return only one formula and return it as Instance
    public class FormulaFinder {
        static public Formula GetByID(int ID) {
            //достаем формулу
            Map<String, String> firstQuery =
                    Finder.GetByID("Formula", ID);

            //достаем все символы в ней
            // (берем ID. находим все строки в указанной таблице с указанным столбцом
            List<Sign> secondQuery =
                    GetInnerSigns(ID,"Formula_All_Signs","All_Signs_id");

            //все константы в ней
            List<Constant> thirtyQuery =
                    GetInnerConstants(ID,"Formula_Constants","Constants_id");

            return new Formula(firstQuery, secondQuery, thirtyQuery);
        }

        static public Formula GetByName(String Formula_Name){

            //получаем id формулы из файла
            Map<String, String> firstQuery =
                    Finder.GetByName("Formula", Formula_Name);

            int id=Integer.parseInt(firstQuery.get("_id"));

            //получаем содержимое формулы
            List<Sign> secondQuery =
                    GetInnerSigns(
                            id,
                            "Formula_All_Signs",
                            "All_Signs_id"
                    );

            //получаем
            List<Constant> thirtyQuery =
                    GetInnerConstants(
                            id,
                            "Formula_Constants",
                            "Constants_id"
                    );


            return new Formula(
                    firstQuery,
                    secondQuery,
                    thirtyQuery);
        }


        static private List<Sign> GetInnerSigns(int ID,String TableManyMany,String Column){
            //достаем все Id, которые соответствуют Id формулы
            List<String> AllCatchedId =CatchValueInMany(ID, TableManyMany, Column);

            //по ним  достаем  соответствующий объект соотвествующего класса
            //и отправляем все, что достали
            List<Sign> result= new ArrayList<Sign>();

            if(!AllCatchedId.isEmpty()){//получаем эти элементы
                for(String n : AllCatchedId) {
                    result.add(SignsFinder.GetByID(Integer.parseInt(n)));
                }
            }
            return result;

        }

        static private List<Constant> GetInnerConstants(int ID,String TableManyMany,String Column){

            List<String> AllCatchedId= CatchValueInMany(ID, TableManyMany, Column);

            List<Constant> result= new ArrayList<Constant>();

            if(!AllCatchedId.isEmpty()){//получаем эти элементы

                for(String n : AllCatchedId) {
                    result.add(ConstantFinder.GetByID(Integer.parseInt(n)));
                }
            }
            return result;

        }

        static private List<String> CatchValueInMany(int ID,String TableManyMany,String Column) {

            try {
                List<Map<String, String>> Catched = Finder.Get_All(
                        TableManyMany,new String[]{Column},
                        "Formula_id = ?", new String[]{Integer.toString(ID)},
                        null, null, null
                );


                List<String> AllCatchedId = new ArrayList<String>();
                // получаем id элементов таблицы Signs которым соответствует id формлуы
                for (Map<String, String> n : Catched) {
                    //id-название таблицы
                    AllCatchedId.add(n.get(Column));
                }
                return AllCatchedId;

            } catch (CursorIndexOutOfBoundsException exc) {
                List<String> request=new ArrayList<String>();
                request.add("1");
                return request;
            }
        }
    }


