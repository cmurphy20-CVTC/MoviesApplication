package controller;

import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import models.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SearchDirectorServlet", urlPatterns = "/SearchDirector")
public class SearchDirectorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            MovieDao movieDao = new MovieDaoImpl();
            final List<Movie> movies = movieDao.retrieveMovies();

            // get the input from the user

            String searchTerm = request.getParameter("director");

            // filter the list to match the search term supplied by the user
            final List<Movie> filteredList = movies
                    .stream() // turn the list into a stream data source
                    .filter((m) -> m.getDirector().equals(searchTerm)) //filter the stream, returning a new stream that only contains matches
                    .collect(Collectors.toList());  // collect the results from the stream and turn them back into a list

            request.setAttribute("movies", filteredList);

        } catch (MovieDaoException e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/view-all.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
