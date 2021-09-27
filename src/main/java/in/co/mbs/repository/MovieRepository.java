package in.co.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.Movie;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}