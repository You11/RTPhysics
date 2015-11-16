package ru.b7.rtphysics.Database.InstancesInTables;

import java.util.Map;

/**
 * Created by Nikita on 28.10.2015.
 */
public class SignParent implements BaseParametrs {
    final String id;
    final String Name;
    final String Dimension;
    final String Sign;
    public SignParent(Map<String, String> params){
        this.id = params.get("_id");
        this.Name = params.get("Name");
        this.Dimension = params.get("Dimension");
        this.Sign = params.get("Sign");
    }

    public String GetID(){
        return  this.id;
    }
    public String GetName(){
        return  this.Name;
    }
    public String GetDimension(){
        return this.Dimension;
    }
    public String GetSign(){
        return this.Sign;
    }

}
