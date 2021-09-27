package in.co.mbs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.CinemaHall;
import in.co.mbs.model.CinemaHallSeat;
import in.co.mbs.model.Theater;

/**
 * 
 * @author santhosh
 *
 */
@Component
public interface TheaterService {

	public MessageResponse addTheater(Theater theater);

	public Theater deleteTheater(String theaterName);

	public Theater updateTheater(Theater theater);

	public Theater getTheater(String name);

	public List<Theater> getAllTheater();

	public MessageResponse addCinemaHall(String theaterId, CinemaHall cinemaHall);

	public MessageResponse addCinemaHallSeats(List<CinemaHallSeat> cinemaHallSeat, String cinemaHall);

}
