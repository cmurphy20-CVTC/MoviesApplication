package controller;

import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PopulateServlet", urlPatterns = "/Populate")
public class PopulateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get the file path for the file we want to get data from
        final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);

        // call the populate method of our dao
        final MovieDao movieDao = new MovieDaoImpl();

        String message = "";
        String messageColor = "";

        try {
            movieDao.populate(filePath);
            message = "The database was successfully populated.";
            messageColor = "green";
        } catch (MovieDaoException e) {
            e.printStackTrace();
            message = "There was an error that prevented the database from being populated.";
            messageColor = "red";
        }
        request.setAttribute("message", message);
        request.setAttribute("color", messageColor);
        getServletContext().getRequestDispatcher("/populate.jsp").forward(request, response);
    }
}
