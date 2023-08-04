
/**
 * This interface defines getter methods for each movie's data attributes
 * and is implemented by classes that represent a movie and its associated
 * data.
 */
public interface IMovie{

    /**
     * Returns the title of the movie.
     * @return title of the book
     */
    String getTitle();

    /**
     * Returns the year of the movie.
     * @return year of the movie
     */
    int getYear();

    /**
     * Returns the genre of the movie.
     * @return genre of the movie
     */
    String getGenre();
    
    
    /**
     * Returns the rating of the movie
     * @return rating of the movie
     */
    double getRating();

}