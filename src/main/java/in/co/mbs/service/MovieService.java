package in.co.mbs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.Movie;

/**
 * 
 * @author santhosh
 *
 */
@Component
public interface MovieService {

	public MessageResponse addMovie(Movie movie);

	public MessageResponse addMovies(List<Movie> movies);

	public MessageResponse deleteMovie(String name);

	public List<Movie> getAllMovies();

	public Movie getMovie(String name);
	
}
