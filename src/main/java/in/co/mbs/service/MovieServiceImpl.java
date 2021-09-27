package in.co.mbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.exceptions.ResourceNotFoundException;
import in.co.mbs.model.Movie;
import in.co.mbs.repository.MovieRepository;

/**
 * 
 * @author santhosh
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public MessageResponse addMovie(Movie movie) {
		movie.setMovieAddedOn(new Date());
		movieRepository.save(movie);
		return new MessageResponse("Movie " + movie.getTitle() + " added successfully");
	}

	@Override
	public MessageResponse addMovies(List<Movie> movies) {
		for (Movie movie : movies)
			movieRepository.save(movie);
		return new MessageResponse(movies.size() + " movies added successfully");
	}

	@Override
	public MessageResponse deleteMovie(String name) {
		Movie movie = getMovie(name);
		movieRepository.delete(movie);
		return new MessageResponse("Movie " + name + " deleted successfully");
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie getMovie(String name) {
		return movieRepository.findById(name).orElseThrow(() -> new ResourceNotFoundException("Movie", "Name", name));
	}
}
