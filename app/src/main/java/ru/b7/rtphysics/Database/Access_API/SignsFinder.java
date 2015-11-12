package ru.b7.rtphysics.Database.Access_API;


import android.util.Log;

import ru.b7.rtphysics.Database.InstancesInTables.Sign;

/**
 * Created by Nikita on 01.11.2015.
 */
 class SignsFinder {
    static public Sign GetByID(int id){
        return new Sign(Finder.GetByID("All_Signs", id));
    }
    static public Sign GetByName(String Name){
        return new Sign(Finder.GetByName("All_Signs", Name));
    }
}
