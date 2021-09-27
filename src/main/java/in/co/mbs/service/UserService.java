package in.co.mbs.service;

import org.springframework.stereotype.Component;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.User;

/**
 * 
 * @author santhosh
 *
 */
@Component
public interface UserService {

	public MessageResponse save(User user);

	public User getUser(String username);

}
