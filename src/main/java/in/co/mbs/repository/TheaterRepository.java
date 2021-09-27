package in.co.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.Theater;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface TheaterRepository extends JpaRepository<Theater, String> {
}