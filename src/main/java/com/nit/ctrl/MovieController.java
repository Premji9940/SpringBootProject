package com.nit.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Movie;
import com.nit.service.IMovieService;



@RequestMapping("/movie")
@RestController
public class MovieController {

	private static Logger log = LoggerFactory.getLogger(MovieController.class);


	@Autowired
	IMovieService service;

	// 1.save Operation
	@PostMapping("/save")
	public ResponseEntity<String> movieInsert(@RequestBody Movie m) {
		System.out.println("data:  "+m);
		log.info("com.nit.ctrl.MovieController----Save Operaion is started....");
		log.info("com.nit.ctrl.MovieController contacting service method of MovieInsert()");
		String msg = service.movieInsert(m);
		log.info("com.nit.ctrl.MovieController----Save Operaion is ended....");

		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	// 2.Retrieve Operation
	@GetMapping("/get")
	public ResponseEntity<Iterable<Movie>> getMovies() {
		log.info("com.nit.ctrl.MovieController----Retrieve Operaion is started....");
		log.info("com.nit.ctrl.MovieController contacting service method of getMovie()");

		Iterable<Movie> movie = service.getMovie();
		log.info("com.nit.ctrl.MovieController----Retrieve Operaion is ended....");

		return new ResponseEntity<Iterable<Movie>>(movie, HttpStatus.OK);

	}

	// 3.Delete Operation
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
		log.info("com.nit.ctrl.MovieController----delete Operaion is started....");
		log.info("com.nit.ctrl.MovieController contacting service method of deleteMovie()");
		String msg = service.deleteMovie(id);
		log.info("com.nit.ctrl.MovieController----delete Operaion is ended....");

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	// 4.Update Operation
	@PutMapping("/update/{id}")

	public ResponseEntity<String> updateMovie(Integer id) {
		log.info("com.nit.ctrl.MovieController----update Operaion is started....");
		log.info("com.nit.ctrl.MovieController contacting service method of updateMovie()");
		String msg = service.updateMovie(id);
		log.info("com.nit.ctrl.MovieController----update Operaion is ended....");

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
}
