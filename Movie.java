
/**
 * A movie class for movie objects
 * 
 * @author Haochen Wang
 *
 */
public class Movie implements IMovie {

  // data of the movie
  private final String TITLE;
  private final int YEAR;
  private final String GENRE;
  private final double RATING;

  /**
   * Constructor for a Movie object
   * 
   * @param title
   * @param year
   * @param genre
   * @param rating
   */
  public Movie(String title, int year, String genre, double rating) {
    this.TITLE = title;
    this.YEAR = year;
    this.GENRE = genre;
    this.RATING = rating;
  }

  /**
   * Compare two movies based on the alphabetic order of their titles
   * 
   * @return return negative number if this movie should precede the other, 0 if of the same order,
   *         and positive if should succeed
   */
  @Override
  public int compareTo(IMovie o) {
    return (this.TITLE.compareTo(o.getTitle()));
  }

  /**
   * Getter for the title
   * 
   * @return title of the movie
   */
  @Override
  public String getTitle() {
    return this.TITLE;
  }

  /**
   * Getter for the year
   * 
   * @return year of the movie
   */
  @Override
  public Integer getYear() {
    return this.YEAR;
  }

  /**
   * Getter for the genre
   * 
   * @return genre of the movie
   */
  @Override
  public String getGenre() {
    return this.GENRE;
  }

  /**
   * Getter for the rating
   * 
   * @return rating of the movie
   */
  @Override
  public Double getRating() {
    return this.RATING;
  }

}
