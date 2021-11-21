package utility;

import com.sun.media.sound.InvalidFormatException;
import models.Movie;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkbookUtility {

    public final static String INPUT_FILE = "//assets//Movies.xlsx";

    public static List<Movie>retrieveMovies(final File inputFile) throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

        final List<Movie> movies = new ArrayList<Movie>();

        // do some code
        final Workbook workbook = WorkbookFactory.create(inputFile);

        final Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {

            final Cell titleCell = row.getCell(0);
            final Cell directorCell = row.getCell(1);
            final Cell lengthInMinutesCell = row.getCell(2);

            final Movie movie = new Movie();
            movie.setTitle(titleCell.getStringCellValue());
            movie.setDirector(directorCell.getStringCellValue());
            movie.setLengthInMinutes((int)lengthInMinutesCell.getNumericCellValue());

            movies.add(movie);
        }
        return movies;
    }

}