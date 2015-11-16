package ru.b7.rtphysics.Database.Access_API;


import ru.b7.rtphysics.Database.InstancesInTables.Constant;

/**
 * Created by Nikita on 01.11.2015.
 */
  class ConstantFinder {

    static public Constant GetByID(int id){
        return new Constant(Finder.GetByID("Constants", id));
    }
    static public Constant GetByName(String Name){
        return new Constant(Finder.GetByName("Constants", Name));
    }

}
