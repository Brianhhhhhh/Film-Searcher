import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BackendDeveloperTest {

	@Test
	/**
	 * This test examines the functionality of searching the movies by title. It should return a
	 * list of movies which contains the keyword of the title.
	 */
	void testWithSearchingTitle() {
		IMovie a = new Movie("Test1",2022,5.5,"Test");
		MovieMapperBackend m = new MovieMapperBackend();
		List<IMovie> list = m.searchByTitle("Test1");
		assertEquals(list.get(0).getTitle(),a.getTitle());
		assertEquals(list.get(0).getYear(),a.getYear());
		assertEquals(list.get(0).getRating(),a.getRating());
		assertEquals(list.size(),1);
	}

	@Test
	/**
	 * This test examines the functionality of searching the movies by rating. It should return a
	 * list of movies which has the rating equal or higher than the input rating.
	 */
	void testWithSearchingRating() {
		IMovie b = new Movie("Test2",2021,8.0,"Test");
		IMovie c = new Movie("Test3",2020,8.0,"NotTest");
		MovieMapperBackend m = new MovieMapperBackend();
		List<IMovie> list = m.searchByRating(10.0);
		assertEquals(list.size(),0);
		List<IMovie> list2 = m.searchByRating(8.0);
		assertEquals(list2.get(0).getTitle(),b.getTitle());
		assertEquals(list2.get(1).getYear(),c.getYear());
		assertEquals(list2.get(0).getRating(),c.getRating());
		assertEquals(list2.size(),2);
	}

	@Test
	/**
	 * This test examines the functionality of searching the movies by genre. It should return a
	 * list of movies which has the genre type of the input type.
	 */
	void testWithSearchingGenre() {
		Movie c = new Movie("Test3",2020,8.0,"NotTest");
		MovieMapperBackend m = new MovieMapperBackend();
		List<IMovie> list = m.searchByGenre("Test");
		assertEquals(list.size(),2);
		List<IMovie> list2 = m.searchByGenre("NotTest");
		assertEquals(list2.get(0).getTitle(),c.getTitle());
		assertEquals(list2.size(),1);
	}
	
	@Test
	/**
	 * This test examines the functionality of adding the movie into the red black tree. It should
	 *  output the correct size of tree for both before and after insertion.
	 */
	void testWithAddingMovie() {
		IMovie d = new Movie("Test4",2019,5.8,"NotTest");
		MovieMapperBackend m = new MovieMapperBackend();
		m.addMovie(d);
		List<IMovie> list = m.searchByTitle("Test4");
		assertEquals(list.size(),1);
		assertEquals(list.get(0).getRating(),d.getRating());
	}
	
	@Test
	/**
	 * This test examines the functionality of if the implementation can correctly reflect the
	 * size of the red black tree if it's empty and after insertion.
	 */
	void testWithGeneralImplementation() {
		Movie a = new Movie("Test1",2022,5.5,"Test");
		Movie b = new Movie("Test2",2021,8.0,"Test");
		Movie c = new Movie("Test3",2020,8.0,"NotTest");
		Movie d = new Movie("Test4",2019,5.8,"NotTest");
		MovieMapperBackend m = new MovieMapperBackend();
		assertEquals(m.size(),0);
		m.addMovie(b);
		m.addMovie(c);
		m.addMovie(d);
		assertEquals(m.size(),3);
		m.addMovie(a);
		List<IMovie> list = m.searchByTitle("Test");
		assertEquals(list.size(),4);
	}
}

	
