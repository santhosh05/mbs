package in.co.mbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.exceptions.ResourceNotFoundException;
import in.co.mbs.model.User;
import in.co.mbs.repository.UserRepository;

/**
 * 
 * @author santhosh
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public MessageResponse save(User user) {
		userRepository.save(user);
		return new MessageResponse("User " + user.getUsername() + " added successfully");

	}

	@Override
	public User getUser(String username) {
		return userRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "name", username));
	}

}
