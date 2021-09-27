package in.co.mbs.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.Movie;
import in.co.mbs.service.MovieService;

/**
 * 
 * @author santhosh
 *
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movieResponse = movieService.getAllMovies();//
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> addMovies(@RequestBody Movie movie) {
		MessageResponse movieResponse = movieService.addMovie(movie);//
		return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);
	}

	@GetMapping("/get/{movieName}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Movie> getMovie(@PathVariable(value = "movieName") String movieName) {
		Movie movieResponse = movieService.getMovie(movieName);//
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{movieName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> deleteMovie(@PathVariable(value = "movieName") String movieName) {
		MessageResponse movieResponse = movieService.deleteMovie(movieName);//
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);
	}
}
