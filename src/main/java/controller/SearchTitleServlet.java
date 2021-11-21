package controller;

import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import models.Movie;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SearchTitleServlet", urlPatterns = "/SearchTitle")
public class SearchTitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            MovieDao movieDao = new MovieDaoImpl();
            final List<Movie> movies = movieDao.retrieveMovies();

            // get the input from the user

            String searchTerm = request.getParameter("title");

            // filter the list to match the search term supplied by the user
            final List<Movie> filteredList = movies
                    .stream() // turn the list into a stream data source
                    .filter((m) -> m.getTitle().equals(searchTerm)) //filter the stream, returning a new stream that only contains matches
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
