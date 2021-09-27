package in.co.mbs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.exceptions.ResourceNotFoundException;
import in.co.mbs.model.CinemaHall;
import in.co.mbs.model.CinemaHallSeat;
import in.co.mbs.model.Theater;
import in.co.mbs.repository.CinemaHallRepository;
import in.co.mbs.repository.TheaterRepository;

/**
 * 
 * @author santhosh
 *
 */
@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	TheaterRepository theaterRepository;
	
	@Autowired
	CinemaHallRepository cinemaHallRepository;

	@Override
	public MessageResponse addTheater(Theater theater) {
		theaterRepository.save(theater);
		return new MessageResponse("Theater "+theater.getName()+" added successfully");
	}

	@Override
	public Theater deleteTheater(String theaterId) {
		Theater theater = getTheater(theaterId);
		theaterRepository.delete(theater);
		return theater;
	}

	@Override
	public Theater updateTheater(Theater theater) {
		Optional<Theater> theaterOld = theaterRepository.findById(theater.getTheaterId());
		if(theaterOld.isEmpty())
			throw new ResourceNotFoundException("Theater", "Theater Id", theater.getTheaterId());
		return theaterRepository.save(theater);
		
	}

	@Override
	public Theater getTheater(String theaterId) {
		return theaterRepository.findById(theaterId)
				.orElseThrow(() -> new ResourceNotFoundException("Theater", "Theater Name", theaterId));
	}

	@Override
	public List<Theater> getAllTheater() {
		return theaterRepository.findAll();
	}

	@Override
	public MessageResponse addCinemaHall(String theaterId, CinemaHall cinemaHall) {
		Theater theater = getTheater(theaterId);
		List<CinemaHall> cinemaHalls = theater.getCinemaHalls();
		if(cinemaHalls == null)
			cinemaHalls = new ArrayList<CinemaHall>();
		cinemaHall.setTheater(theater);
		cinemaHalls.add(cinemaHall);
		theater.setCinemaHalls(cinemaHalls);
		theater = updateTheater(theater);
		return new MessageResponse("Cinema Hall "+cinemaHall.getDisplayName()+" sucessfully added in theater "+theater.getName()+"["+theater.getTheaterId()+"]");
	}

	@Override
	public MessageResponse addCinemaHallSeats(List<CinemaHallSeat> cinemaHallSeats, String cinemaHallName) {
		Optional<CinemaHall> cinemaHall = cinemaHallRepository.findById(cinemaHallName);
		if(cinemaHall.isEmpty())
			throw new ResourceNotFoundException("CinemaHall", "Cinema Hall Name", cinemaHallName);
		CinemaHall cinemaHall2 = cinemaHall.get();
		cinemaHallSeats.forEach(cinemaHallSeat->cinemaHallSeat.setCinemaHall(cinemaHall2));
		cinemaHall2.setSeats(cinemaHallSeats);
		cinemaHall2.setTotalSeats(cinemaHallSeats.size());
		cinemaHallRepository.save(cinemaHall2);
		return new MessageResponse("Seats successfully added to "+cinemaHallName);
	}
	
}
