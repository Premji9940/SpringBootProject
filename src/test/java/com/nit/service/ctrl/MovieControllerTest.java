package com.nit.service.ctrl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nit.ctrl.MovieController;
import com.nit.entity.Movie;
import com.nit.exception.MovieNotFoundException;
import com.nit.service.MovieService;
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	MockMvc mvc;
	@MockBean
	MovieService service;

	public MovieControllerTest() {
		MockitoAnnotations.openMocks(this);

	}

	@BeforeEach
	void initServie() {
		System.out.println("MovieControllerTest.initServie() "+service);
		
	}

	@Test
	@DisplayName("Save Operaion for positive test")
	void saveMoviePositiveTest() throws Exception {
		Movie a = new Movie(1001, "prabhas", "kalki", "santhosh narayana", "nag ashwin");

		ObjectMapper o=new ObjectMapper();
		o.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter writer = o.writer().withDefaultPrettyPrinter();
		String data = writer.writeValueAsString(a);
		when(service.movieInsert(a)).thenReturn("success");
		mvc.perform(post("/movie/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(data)).andDo(print()).andExpect(status().isCreated());
}

	@Test
	@DisplayName("save operation for negative test")
	void saveMovieNegativeTest() throws Exception {
		Movie a = new Movie(1001, "prabhas", "kalki", "santhosh narayana", "nag ashwin");

		ObjectMapper o=new ObjectMapper();
		o.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter writer = o.writer().withDefaultPrettyPrinter();
		String data = writer.writeValueAsString(a);
		when(service.movieInsert(a)).thenReturn("success");
		mvc.perform(post("/movie/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")).andDo(print()).andExpect(status().is4xxClientError());
	
	}

		// 2.Retrieve Test
	@Test
	@DisplayName("Retrieve Test For positive")
	public void retrieveTestForPositive() throws Exception {
		Movie a = new Movie(1001, "prabhas", "kalki", "santhosh narayana", "nag ashwin");
		Movie b = new Movie(1002, "ram charan", "game changer", "thaman", "shankar");
		Movie c = new Movie(1003, "Allu Arjun", "pushpa", "Devi sri prasad", "sukumar");
		List<Movie> list = List.of(a, b, c);
		when(service.getMovie()).thenReturn((Iterable<Movie>)list);
		mvc.perform(get("/movie/get")).andDo(print()).andExpect(status().isOk());
		}

	@Test
	@DisplayName("Retrieve Test For Negative")
	public void retrieveTestForNegative() {
		}

	@Test
	@DisplayName("Retrieve Test For EmptyData")
	public void retrieveTestForEmptyData() {
		
	}
	// 3.Test Cases for Delete

	@Test
	@DisplayName("test Case for delete with Positve")
	public void deleteTestPositive() throws Exception {
		when(service.deleteMovie(anyInt())).thenReturn("deleted");
		mvc.perform(delete("/movie/delete/1234")).andDo(print()).andExpect(status().isOk());
		
		
	
	}

	@Test
	@DisplayName("test Case for delete with Negative")
	public void deleteTestNegative() {
		
		
	}

	@Test
	@DisplayName("test Case for delete with Null input")
	public void deleteTestNullInput() {
	}

	// 4.Test Cases for update
	@Test
	@DisplayName("test case for update with positive")
	public void updateTestPositive() throws Exception {
		when(service.updateMovie(anyInt())).thenReturn("updated");
		mvc.perform(put("/movie/update/1234")).andDo(print()).andExpect(status().isOk());
	}

	@Test @DisplayName("test case for update with Negative")
	public void updateTestNegative() {
	
		
	}

	@Test
	@DisplayName("test Case for update with Null input")
	public void updateTestNullInput() {
	}
}
