package controller;

import comparators.DirectorComparator;
import comparators.LengthComparator;
import comparators.TitleComparator;
import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import models.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.File;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ViewAllServlet", value = "/ViewAllServlet")
public class ViewAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String target = "/view-all.jsp";

        // final File inputFile = new File(getServletContext().getRealPath(WorkbookUtility.INPUT_FILE));

        try {
           // final List<Movie> movies = WorkbookUtility.retrieveMovies(inputFile);

            final MovieDao movieDao = new MovieDaoImpl();
            final List<Movie> movies = movieDao.retrieveMovies();

            String sortType = request.getParameter("sortType");

            if(null != sortType && sortType.equals("director")) {

                Collections.sort(movies, new DirectorComparator());

            }
                if (null != sortType && sortType.equals("lengthInMinutes")) {

                    Collections.sort(movies, new LengthComparator());

                } else if (null != sortType && sortType.equals("title")) {

                    Collections.sort(movies, new TitleComparator());
                }

            request.setAttribute("movies", movies);
        } catch (MovieDaoException e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher(target).forward(request, response);
    }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

