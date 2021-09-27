package in.co.mbs.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.dto.ShowRequest;
import in.co.mbs.model.BookingSeat;
import in.co.mbs.model.Show;
import in.co.mbs.service.ShowService;

/**
 * 
 * @author santhosh
 *
 */
@RestController
@RequestMapping("/shows")
public class ShowsController {

	@Autowired
	ShowService showService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<List<Show>> getAllShows() {
		List<Show> showResponse = showService.getAllShows();//
		return new ResponseEntity<>(showResponse, HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> addShows(@RequestBody ShowRequest showdto) {
		MessageResponse showResponse = showService.addShow(showdto);//
		return new ResponseEntity<>(showResponse, HttpStatus.CREATED);
	}

	@GetMapping("/id/{showName}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Show> getShow(@PathVariable(value = "showName") String showName) {
		Show showResponse = showService.getShow(showName);//
		return new ResponseEntity<>(showResponse, HttpStatus.OK);
	}

	@GetMapping("/available-seats-for-show/{showName}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<BookingSeat>> getAvailableAllSeatsForShow(
			@PathVariable(value = "showName") String showName) {
		List<BookingSeat> allAvailalbeSeatsForShow = showService.getAllAvailalbeSeatsForShow(showName);
		return new ResponseEntity<>(allAvailalbeSeatsForShow, HttpStatus.OK);
	}

	@GetMapping("/upcoming-shows")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Show>> getAllCurrentAndUpcomingShows() {
		List<Show> showResponse = showService.getAllCurrentAndUpcomingShows();//
		return new ResponseEntity<>(showResponse, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{showName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> deleteShow(@PathVariable(value = "showName") String showName) {
		MessageResponse showResponse = showService.deleteShow(showName);//
		return new ResponseEntity<>(showResponse, HttpStatus.OK);
	}
}
