package ru.b7.rtphysics.Database.InstancesInTables;

import java.util.List;
import java.util.Map;

/**
 * Created by Nikita on 16.10.2015.
 */
public class Formula implements BaseParametrs {

    final String id;
    final String Name;
    final boolean IsFavorites;
    final List<Sign> List_Of_Signs;    //выборка из таблицы знаков
    final List<Constant> List_Of_Constants;//выборка из талицы констант

    public Formula(
            Map<String, String> AllParams,
            List<Sign> AllSigns,
            List<Constant> AllConstants)
    {

        this.id = AllParams.get("_id");
        this.Name = AllParams.get("Name");
        this.IsFavorites = (Integer.parseInt(AllParams.get("IsFavorites"))==1);
        this.List_Of_Signs = AllSigns;
        this.List_Of_Constants = AllConstants;

    }

    public String GetID() {
        return this.id;
    }

    public String GetName() {
        return this.Name;
    }

    public String GetInfo() {
        StringBuilder result= new StringBuilder();
        for(Constant n : this.List_Of_Constants){
            result.append(n.GetSign()+" "+n.GetDimension()+" - "+n.GetName()+"\n");
        }
        for(Sign n:this.List_Of_Signs){
            result.append( n.GetSign()+" "+n.GetDimension()+" - "+n.GetName()+"\n");
        }
        return  result.toString();
    }

    public boolean GetFavorites(){
        return this.IsFavorites;
    }

    public List<Constant> GetAllConstants() {
        return this.List_Of_Constants;
    }

    public List<Sign> GetAllSigns() {
        return this.List_Of_Signs;
    }


}
