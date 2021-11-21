package cvtc.edu;

import models.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import utility.WorkbookUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MoviesMain {

    public static String INPUT_FILE = "src//main//webapp//assets//Movies.xlsx";

    public static void main(String[] args) {

        File inputFile = new File(INPUT_FILE);

        try {
            List<Movie> movies = WorkbookUtility.retrieveMovies(inputFile);

            for (Movie movie: movies) {

                    System.out.println(movie);
                            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }

}
