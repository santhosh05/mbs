package in.co.mbs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.Show;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface ShowRepository extends JpaRepository<Show, String> {

	@Query(value = "select s from Show s where s.startTime >= :startTime and s.status != 'CANCELLED'")
	List<Show> findAllShowsAfter(@Param("startTime") Date startTime);

}