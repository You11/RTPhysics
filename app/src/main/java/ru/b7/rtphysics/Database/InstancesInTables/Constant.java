
package ru.b7.rtphysics.Database.InstancesInTables;

import java.util.Map;

/**
 * Created by Nikita on 18.10.2015.
 */
public class Constant extends SignParent {

    private final String value_double;

    public Constant(Map<String, String> params)
    {
        super(params);
        this.value_double=params.get("value_double");
    }

    public String GetValue(){
        return this.value_double;
    }


}
