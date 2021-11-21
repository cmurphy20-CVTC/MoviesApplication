package dao;

import models.Movie;

import java.util.List;

public interface MovieDao {

    void populate(String filePath) throws MovieDaoException;

    List<Movie> retrieveMovies() throws MovieDaoException;

    void insertMovie(Movie movie) throws MovieDaoException;


}
