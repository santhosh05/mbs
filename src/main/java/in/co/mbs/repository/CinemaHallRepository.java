package in.co.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.CinemaHall;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, String> {
}