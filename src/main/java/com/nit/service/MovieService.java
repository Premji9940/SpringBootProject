package com.nit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Movie;
import com.nit.exception.MovieNotFoundException;
import com.nit.repo.IMovieRepo;

@Service
public class MovieService implements IMovieService {

	Logger log = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	IMovieRepo repo;

	public MovieService(IMovieRepo repo) {
		this.repo = repo;

	}

	// 1.Save Operation
	@Override
	public String movieInsert(Movie m) {
		if (m.getMid() == null) {
			throw new MovieNotFoundException("Movie Not Found");
		}

		log.info("***********Save Operation is started from com.nit.service.MovieService class**************");
		log.info("******Record is Saved From MovieService com.nit.service.MovieService ******************");
		Movie save = repo.save(m);
		log.info("***********Save Operation is ended com.nit.service.MovieService **************");

		return "Record Inserted With " + save.getMid();

	}

	// 2.Retrieve Operation
	@Override
	public Iterable<Movie> getMovie() {
		log.info("***********Retrieve Operation is Started From com.nit.service.MovieService **************");
		log.info("***********Retrieve Operation is ended From com.nit.service.MovieService **************");

		return repo.findAll();
	}

	// 3.Delete Operation
	@Override
	public String deleteMovie(Integer id) {
		log.info("***********delete Operation is Started From com.nit.service.MovieService **************");

		if(id==null ) {
			return "input should not be null/empty";
		}
		if (repo.existsById(id)) {
			repo.deleteById(id);
			log.info("***********delete Operation is finished From com.nit.service.MovieService **************");

			return "Record Deleted";
		} else {
			log.info("***********delete Operation is ended From com.nit.service.MovieService**************");

			return "Record Is Not Available";
		}

	}

	// 4.Update Operation
	@Override
	public String updateMovie(Integer id) {
		log.info("***********update Operation is started From com.nit.service.MovieService**************");

		if(id==null) {
			return "invalid input";
		}
		java.util.Optional<Movie> findById = repo.findById(id);

		if (findById.isPresent()) {
			repo.save(findById.get());
			log.info("***********update Operation is ended From com.nit.service.MovieService**************");

			return "Record Updated";

		} else {
			log.info("***********update Operation is ended From com.nit.service.MovieService**************");

			return "Record Not Updated";
		}

	}

}
