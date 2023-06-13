package activejdbc.examples.simple;


import org.javalite.activejdbc.test.DBSpec;
import org.junit.Test;

import java.util.List;

public class MovieSpec extends DBSpec {

    @Test
    public void shouldList() {

        Director hitchcock = new Director("Alfred", "Hitchcock");
        hitchcock.saveIt();
        hitchcock.add(new Movie("Strangers on a Train"));
        hitchcock.add(new Movie("Robert Walker"));
        hitchcock.add(new Movie("Rear Window"));

        Director kubrick = new Director("Stanley", "Kubrick");
        kubrick.saveIt();
        kubrick.add(new Movie("2001: A Space Odyssey"));
        kubrick.add(new Movie("A Clockwork Orange"));

        //lets dump directors because;)
        System.out.println("---->>");
        Director.findAll().dump();
        System.out.println("<<----");

//        Lets use includes now:
        List<Director> directors = Director.find("id in(" + hitchcock.getId() + "," + kubrick.getId() + ")").include(Movie.class);

        for (Director director : directors) {
            System.out.println("ID:" + director.getId() + ", Director: " + director.getName() + " " + ", Movies: " + director.getAll(Movie.class).collect("title"));
        }
    }
}
