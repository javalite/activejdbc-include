package activejdbc.examples.simple;

import org.javalite.activejdbc.Model;

/**
 * @author Igor Polevoy on 11/16/15.
 */
public class Director extends Model {

    public Director() {}

    public Director(String firstName, String lastName){
        set("first_name", firstName, "last_name", lastName);
    }

    public String getName(){
        return "" + get("first_name") + " " + get("last_name");
    }
}
