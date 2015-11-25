package ru.b7.rtphysics.Database.InstancesInTables;

import java.util.Map;

/**
 * Created by Nikita on 16.10.2015.
 */
public class Formula {

    final String id;
    final String Name;
    final String Information;
    final String ImageName;


    public Formula(Map<String,String> AllParams) {

        this.id = AllParams.get("_id");
        this.Name = AllParams.get("Name");
        this.Information = AllParams.get("Information");
        this.ImageName = AllParams.get("ImageName");

    }

    public Formula() {
        this.id = "Empty";
        this.Name =  "Empty";
        this.Information =  "Empty";
        this.ImageName = "";
    }

    public String GetImageName(){
        return this.ImageName;
    }

    public String GetID() {
        return this.id;
    }

    public String GetName() {
        return this.Name;
    }

    public String GetInfo() {
        return "";
    }

}
