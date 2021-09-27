package in.co.mbs.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.User;
import in.co.mbs.service.UserService;

/**
 * 
 * @author santhosh
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<MessageResponse> addUser(@RequestBody User user) {
		MessageResponse userResponse = userService.save(user);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

	}
}
