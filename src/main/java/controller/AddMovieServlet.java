package controller;

import com.google.common.base.Strings;
import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import models.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddMovieServlet", urlPatterns = "/AddMovie")
public class AddMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Target JSP
        String target = "/add-movie.jsp";

        try {
        // Get submitted information form the request
        final String title = request.getParameter("title");
        final String director = request.getParameter("director");
        final String lengthInMinutesString = request.getParameter("lengthInMinutes");
        // final Integer lengthInMinutes = Integer.parseInt(request.getParameter("lengthInMinutes"));

        if (Strings.isNullOrEmpty(title) || Strings.isNullOrEmpty(director) || Strings.isNullOrEmpty(lengthInMinutesString)) {

            // User did not submit all the necessary information
            request.setAttribute("message", "You must complete all fields to submit the form.");
        } else {
            // the user submitted all necessary data
            final int lengthInMinutes = Integer.parseInt(lengthInMinutesString);

            final MovieDao movieDao = new MovieDaoImpl();

            final Movie movie = new Movie(title, director, lengthInMinutes);

                movieDao.insertMovie(movie);
                request.setAttribute("message", "The movie was added.");
            }

        } catch (MovieDaoException e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
        }

        // redirect to the target JSP
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}

