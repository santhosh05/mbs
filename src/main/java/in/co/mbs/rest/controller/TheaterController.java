package in.co.mbs.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.CinemaHall;
import in.co.mbs.model.CinemaHallSeat;
import in.co.mbs.model.Theater;
import in.co.mbs.service.TheaterService;

/**
 * 
 * @author santhosh
 *
 */
@RestController
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	TheaterService theaterService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Theater>> getAllTheaters() {
		List<Theater> theaters = theaterService.getAllTheater();
		return new ResponseEntity<>(theaters, HttpStatus.OK);
	}

	@GetMapping("/id/{theaterName}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Theater> getTheaterByName(@PathVariable("theaterName") String theaterName) {
		Theater employee = theaterService.getTheater(theaterName);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> addTheater(@RequestBody Theater theater) {
		MessageResponse theaterResponse = theaterService.addTheater(theater);
		return new ResponseEntity<>(theaterResponse, HttpStatus.CREATED);
	}

	@PostMapping("/cinema-hall/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> addCinemaHall(@RequestBody CinemaHall cinemaHall,
			@RequestParam(value = "theaterId") String theaterId) {
		MessageResponse theaterResponse = theaterService.addCinemaHall(theaterId, cinemaHall);//
		return new ResponseEntity<>(theaterResponse, HttpStatus.CREATED);
	}

	@PostMapping("/cinema-hall/cinema-hall-seat/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<MessageResponse> addCinemaHallSeat(@RequestBody List<CinemaHallSeat> cinemaHallSeat,
			@RequestParam(value = "cinemahall") String cinemaHall) {
		MessageResponse theaterResponse = theaterService.addCinemaHallSeats(cinemaHallSeat, cinemaHall);//
		return new ResponseEntity<>(theaterResponse, HttpStatus.CREATED);
	}

}
