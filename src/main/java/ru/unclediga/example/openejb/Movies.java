package ru.unclediga.example.openejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

import javax.inject.Inject;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Any;

@Stateful
public class Movies {

    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private NumberGenerator numberGenerator;

    public void addMovie(Movie movie) throws Exception {
        movie.setImdbId(numberGenerator.generateNumber());
        entityManager.persist(movie);
    }

    public void deleteMovie(Movie movie) throws Exception {
        entityManager.remove(movie);
    }

    public List<Movie> getMovies() throws Exception {
        Query query = entityManager.createQuery("SELECT m from Movie as m");
        return query.getResultList();
    }
}