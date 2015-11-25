package ru.b7.rtphysics.Database.Access_API;


import android.database.CursorIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.b7.rtphysics.Database.InstancesInTables.Formula;

/**
 * This ApiUnit can return only one formula and return it as Instance
 */

public class FormulaFinder {

    static public Formula GetByID(int ID) {

        Map<String, String> firstQuery =
                Finder.GetByID("Formula", ID);

        return new Formula(firstQuery);
    }

    static public Formula GetByName(String Formula_Name) {
        Map<String, String> firstQuery =
                Finder.GetByName("Formula", Formula_Name);

        return new Formula(firstQuery);
    }

}

