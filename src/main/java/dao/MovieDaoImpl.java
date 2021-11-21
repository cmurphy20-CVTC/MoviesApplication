package dao;

import com.sun.media.sound.InvalidFormatException;
import com.sun.media.sound.ModelIdentifier;
import models.Movie;
import utility.DbUtility;
import utility.WorkbookUtility;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

    private static final String DROP_TABLE_MOVIE = "drop table if exists movie;";
    private static final String CREATE_TABLE_MOVIE = "create table movie (id integer primary key autoincrement, title text, director text, lengthInMinutes integer);";
    private static final String SELECT_ALL_FROM_MOVIE = "select * from movie;";


    @Override
    public void populate(String filePath) throws MovieDaoException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DbUtility.createConnection();
            statement = connection.createStatement();

            statement.setQueryTimeout(DbUtility.TIMEOUT);

            statement.executeUpdate(DROP_TABLE_MOVIE);
            statement.executeUpdate(CREATE_TABLE_MOVIE);

            final File inputFile = new File(filePath);

            final List<Movie> movies = WorkbookUtility.retrieveMovies(inputFile);

            for(final Movie movie: movies) {

                final String insertValues = "insert into movie (title, director, lengthInMinutes)" +
                        "values ('" + movie.getTitle() + "', '"
                        + movie.getDirector() + "', "
                         + movie.getLengthInMinutes() + ");";

                System.out.println(insertValues);

                statement.executeUpdate(insertValues);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new MovieDaoException("Something went wrong with the SQL");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw new MovieDaoException("Something went wrong with the SQL");
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
            throw new MovieDaoException("Something went wrong with the SQL");
        } catch (IOException e) {
            e.printStackTrace();
            throw new MovieDaoException("IO Exception");
        }
    }

    @Override
    public List<Movie>retrieveMovies() throws MovieDaoException {

        List<Movie> movies = new ArrayList<Movie>();

        Connection connection = null;
        Statement statement = null;


        try {
            connection = DbUtility.createConnection();
            statement = connection.createStatement();

            statement.setQueryTimeout(DbUtility.TIMEOUT);

            final ResultSet results = statement.executeQuery(SELECT_ALL_FROM_MOVIE);

            while (results.next()) {

                final String title = results.getString("title");
                final String director = results.getString("director");
                final Integer lengthInMinutes = results.getInt("lengthInMinutes");

                movies.add(new Movie(title, director, lengthInMinutes));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new MovieDaoException("Error: Uable to retrieve list of movies");
        }

        return movies;
    }

    @Override
    public void insertMovie(Movie movie) throws MovieDaoException{

        Connection connection = null;
        PreparedStatement insertstatement = null;

        try {
            connection = DbUtility.createConnection();

            final String sqlString = "insert into movie(title, director, lengthInMinutes) values (?, ?, ?);";

            insertstatement = connection.prepareStatement(sqlString);

            insertstatement.setString(1, movie.getTitle());
            insertstatement.setString(2, movie.getDirector());
            insertstatement.setInt(3, movie.getLengthInMinutes());


            insertstatement.setQueryTimeout(DbUtility.TIMEOUT);

            insertstatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new MovieDaoException("Error: Uable to retrieve list of movies");
        }

    }
}

