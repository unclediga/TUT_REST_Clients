package ru.unclediga.example.openejb;

import junit.framework.TestCase;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.List;
import java.util.Properties;

/**
 * @version $Revision: 607077 $ $Date: 2007-12-27 06:55:23 -0800 (Thu, 27 Dec 2007) $
 * https://tomee.apache.org/tomee-7.1/examples/jpa-eclipselink.html
 */
public class MoviesTest extends TestCase {

    public void test() throws Exception {
        Properties p = new Properties();

        p.put("movieDatabase", "new://Resource?type=DataSource");
        p.put("movieDatabase.JdbcDriver", "org.h2.Driver");
        p.put("movieDatabase.JdbcUrl", "jdbc:h2:mem:moviedb;DB_CLOSE_DELAY=-1");

        final Context context = EJBContainer.createEJBContainer(p).getContext();

        Movies movies = (Movies) context.lookup("java:global/TUT_REST_Clients/Movies");

        movies.addMovie(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
        movies.addMovie(new Movie("Joel Coen", "Fargo", 1996));
        movies.addMovie(new Movie("Joel Coen", "The Big Lebowski", 1998));

        List<Movie> list = movies.getMovies();
        assertEquals("List.size()", 3, list.size());

        for (Movie movie : list) {
            movies.deleteMovie(movie);
        }

        assertEquals("Movies.getMovies()", 0, movies.getMovies().size());
    }
}