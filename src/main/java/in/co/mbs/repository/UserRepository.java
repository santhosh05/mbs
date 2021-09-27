package in.co.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.User;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public User findByUsername(String username);
}