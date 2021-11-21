package comparators;

import models.Movie;

import java.util.Comparator;

public class LengthComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {

        return o1.getLengthInMinutes().compareTo(o2.getLengthInMinutes());
    }
}

