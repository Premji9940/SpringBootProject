package com.nit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.nit.entity.Movie;
import com.nit.exception.MovieNotFoundException;
import com.nit.repo.IMovieRepo;

public class MovieServiceTest {

	
	public MovieService service;
	public IMovieRepo repo;
	

	public MovieServiceTest() {
		MockitoAnnotations.openMocks(this);

	}

	@BeforeEach
	void initServie() {
		repo = Mockito.mock(IMovieRepo.class);
		service = new MovieService(repo);
	}

	@Test
	@DisplayName("Save Operaion for positive test")
	void saveMoviePositiveTest() {

		IMovieRepo repo = Mockito.mock(IMovieRepo.class);
		service = new MovieService(repo);

		Movie movie = new Movie();
		movie.setMid(10001);
		movie.setActor("prem");
		movie.setMusic_director("Dsp");
		movie.setName("RRR");
		movie.setDirector("RajaMouli");
		when(repo.save(movie)).thenReturn(movie);

		assertEquals(service.movieInsert(movie), "Record Inserted With " + movie.getMid());

	}

	@Test
	@DisplayName("save operation for negative test")
	void saveMovieNegativeTest() {

		Movie movie = new Movie();
		movie.setMid(10001);
		movie.setActor("prem");
		movie.setMusic_director("Dsp");
		movie.setName("RRR");
		movie.setDirector("RajaMouli");
		when(repo.save(movie)).thenReturn(movie);
		assertNotEquals(service.movieInsert(movie), "Record Inserted With " + 1001);

	}

	@Test
	@DisplayName("save operation for exception test")
	void saveMovieExceptionTest() {

		Movie movie = new Movie();
		assertThrows(MovieNotFoundException.class, () -> {

			service.movieInsert(movie);
		});

	}

	// 2.Retrieve Test
	@Test
	@DisplayName("Retrieve Test For positive")
	public void retrieveTestForPositive() {
		Movie a = new Movie(1001, "prabhas", "kalki", "santhosh narayana", "nag ashwin");
		Movie b = new Movie(1002, "ram charan", "game changer", "thaman", "shankar");
		Movie c = new Movie(1003, "Allu Arjun", "pushpa", "Devi sri prasad", "sukumar");
		List<Movie> list = List.of(a, b, c);
		when(repo.findAll()).thenReturn((Iterable<Movie>) list);
		assertThat(((List<Movie>) service.getMovie()).size()).isEqualTo(3);
	}

	@Test
	@DisplayName("Retrieve Test For Negative")
	public void retrieveTestForNegative() {
		Movie a = new Movie(1001, "prabhas", "kalki", "santhosh narayana", "nag ashwin");
		Movie b = new Movie(1002, "ram charan", "game changer", "thaman", "shankar");
		Movie c = new Movie(1003, "Allu Arjun", "pushpa", "Devi sri prasad", "sukumar");
		List<Movie> list = List.of(a, b, c);
		when(repo.findAll()).thenReturn((Iterable<Movie>) list);
		assertThat(((List<Movie>) service.getMovie()).size()).isNotEqualTo(1);
	}

	@Test
	@DisplayName("Retrieve Test For EmptyData")
	public void retrieveTestForEmptyData() {
		/*
		 * Movie a=new Movie(1001,"prabhas","kalki","santhosh narayana","nag ashwin");
		 * Movie b=new Movie(1002,"ram charan","game changer","thaman","shankar"); Movie
		 * c=new Movie(1003,"Allu Arjun","pushpa","Devi sri prasad","sukumar");
		 */
		List<Movie> list = List.of();
		when(repo.findAll()).thenReturn((Iterable<Movie>) list);
		assertThat(((List<Movie>) service.getMovie()).size()).isEqualTo(0);
	}
	// 3.Test Cases for Delete

	@Test
	@DisplayName("test Case for delete with Positve")
	public void deleteTestPositive() {
		when(repo.existsById(1001)).thenReturn(true);
		doNothing().when(repo).deleteById(1001);
		
		service.deleteMovie(1001);
		verify(repo,times(1)).deleteById(anyInt());
		
		assertEquals(service.deleteMovie(1001), "Record Deleted");
		
	}

	@Test
	@DisplayName("test Case for delete with Negative")
	public void deleteTestNegative() {
		when(repo.existsById(1002)).thenReturn(false);
		assertEquals(service.deleteMovie(1002), "Record Is Not Available");
		
	}

	@Test
	@DisplayName("test Case for delete with Null input")
	public void deleteTestNullInput() {
		assertEquals(service.deleteMovie(null), "input should not be null/empty");
	}

	// 4.Test Cases for update
	@Test
	@DisplayName("test case for update with positive")
	public void updateTestPositive() {
		Movie a = new Movie(1001, "prabhas", "kalki", "santhosh narayana", "nag ashwin");
		Optional<Movie> m = Optional.of(a);
		when(repo.findById(1001)).thenReturn(m);

		when(repo.save(repo.findById(1001).get())).thenReturn(a);

		assertEquals(service.updateMovie(1001), "Record Updated");
	}

	@Test @DisplayName("test case for update with Negative")
	public void updateTestNegative() {
	
		 when(repo.findById(1001)).thenReturn(Optional.empty());
		 
		 
		 assertEquals(service.updateMovie(1001), "Record Not Updated");
	}

	@Test
	@DisplayName("test Case for update with Null input")
	public void updateTestNullInput() {
		assertEquals(service.updateMovie(null), "invalid input");
	}
}
