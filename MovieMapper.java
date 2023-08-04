import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the movie mapper app.
 */
public class MovieMapper {

    public static void main(String[] args) throws FileNotFoundException {
        // load the movies from the data file
        List<IMovie> movies = (new MovieLoader("movies.xml")).loadmovies("movies.xml");
        // instantiate the backend
        IMovieMapperBackend backend = new MovieMapperBackend();
        // add all the movies to the backend
        for (IMovie movie : movies) {
            backend.addMovie(movie);
        }
        // instantiate the rating validator (to be used by the front end)
        RatingValidator ratingValidator = new RatingValidator();
        // instantiate the scanner for user input (to be used by the front end)
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        MovieMapperFrontend frontend = new MovieMapperFrontend(userInputScanner, backend, ratingValidator);
        // start the input loop of the front end
        frontend.runCommandLoop();
    }
    
}

