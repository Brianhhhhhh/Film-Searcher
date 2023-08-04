import java.util.ArrayList;
import java.util.List;


public class MovieMapperBackend implements IMovieMapperBackend{
	
	private AEplaceholder<Movie> hold = new AEplaceholder<Movie>();

	@Override
	public List<IMovie> searchByTitle(String keyword) {
		 List<IMovie> filteredMovies = new ArrayList<IMovie>();
		    // check if the word passed in is null
		    if(keyword == null) {
		      return filteredMovies;
		    }
		    // loop through the hashmap of books
		    for (IMovie movie : hold) {// How can I use the iterable here
		      // check if title contains the string word
		      if (movie.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
		          filteredMovies.add(movie);
		        }
		      }
		    return filteredMovies;
	}

	@Override
	public List<IMovie> searchByRating(double rating) {
		List<IMovie> filteredMovies = new ArrayList<IMovie>();
	    // loop through the hashmap of books
	    for (IMovie movie : hold) {// How can I use the iterable here
	      // check if title contains the string word
	      if (movie.getRating() >= rating) {
	          filteredMovies.add(movie);
	        }
	      }
	    return filteredMovies;
	}

	@Override
	public List<IMovie> searchByGenre(String genre) {
		List<IMovie> filteredMovies = new ArrayList<IMovie>();
	    // check if the word passed in is null
	    if(genre == null) {
	      return filteredMovies;
	    }
	    // loop through the hashmap of books
	    for (IMovie movie : hold) {// How can I use the iterable here
	      // check if title contains the string word
	      if (movie.getGenre().toLowerCase().equals(genre.toLowerCase())) {
	          filteredMovies.add(movie);
	        }
	      }
	    return filteredMovies;
	}

	@Override
	public void addMovie(IMovie movie) {
		// TODO Auto-generated method stub
		hold.tree.insert((Movie) movie);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return hold.tree.size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return hold.tree.isEmpty();
	}

}

