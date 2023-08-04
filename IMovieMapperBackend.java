import java.util.List;

public interface IMovieMapperBackend {

    /**
	 * Find the movies using keyword.
	 *
	 * @param String keyword
	 * @returns List contains movie titles
	 */
	public List<IMovie> searchByTitle(String keyword);
	
	/**
	 * Find the movies using rating.
	 *
	 * @param int rating
	 * @returns List contains movie ratings equals or higher than the rating input.
	 */
	public List<IMovie> searchByRating(double rating);
	
	/**
	 * Filter the movies using genre.
	 *
	 * @param GenreCategories genre
	 */
	public List<IMovie> searchByGenre(String genre);
    
	/**
	 * Adding the movies to the movie list.
	 *
	 * @param String movieTitle, int year, GenreCategories genre, int rating
	 */
	public void addMovie(IMovie movie);
	
	/**
	 * Return the current size
	 *
	 * @returns the size in integer
	 */
	public int size();

	/**
	 * Return the boolean value of whether the current red-black tree is empty
	 *
	 * @returns true if the tree is empty, otherwise false
	 */
        public boolean isEmpty();
}

