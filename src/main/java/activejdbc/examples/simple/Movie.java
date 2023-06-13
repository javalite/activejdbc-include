package activejdbc.examples.simple;

import org.javalite.activejdbc.Model;

/**
 * @author Igor Polevoy on 11/16/15.
 */
public class Movie extends Model {

    public Movie() {}

    public Movie(String title) {
        set("title", title);
    }
}
