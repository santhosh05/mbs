package in.co.mbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.dto.ShowRequest;
import in.co.mbs.enums.ShowStatus;
import in.co.mbs.exceptions.ResourceNotFoundException;
import in.co.mbs.model.BookingSeat;
import in.co.mbs.model.CinemaHall;
import in.co.mbs.model.CinemaHallSeat;
import in.co.mbs.model.Movie;
import in.co.mbs.model.Show;
import in.co.mbs.model.Theater;
import in.co.mbs.repository.BookingCheckoutRepository;
import in.co.mbs.repository.BookingRepository;
import in.co.mbs.repository.BookingSeatRepository;
import in.co.mbs.repository.PaymentRepository;
import in.co.mbs.repository.ShowRepository;
import in.co.mbs.utilities.DateFormat;
import in.co.mbs.utilities.Utility;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	ShowRepository showRepository;

	@Autowired
	BookingCheckoutRepository bookingCheckoutRepository;

	@Autowired
	BookingSeatRepository bookingSeatRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	TheaterService theaterService;

	@Autowired
	MovieService movieService;

	@Autowired
	UserService userService;

	@Autowired
	private Environment env;

	@Override
	public MessageResponse addShow(ShowRequest showRequest) {
		Show show = toShows(showRequest);
		show.setStatus(ShowStatus.AS_PER_SCHEDULE);
		addShow(show);
		return new MessageResponse("Show " + show.getShowName() + "  added");
	}

	private MessageResponse addShow(Show show) {
		showRepository.save(show);
		List<CinemaHallSeat> seats = show.getPlayedAt().getSeats();
		for (CinemaHallSeat seat : seats) {
			BookingSeat bookingSeat = new BookingSeat();
			bookingSeat.setSeatColumn(seat.getSeatColumn());
			bookingSeat.setSeatRow(seat.getSeatRow());
			bookingSeat.setSeatNumber(seat.getSeatNumber());
			bookingSeat.setSeatType(seat.getSeatType());
			bookingSeat.setCinemaHall(seat.getCinemaHall());
			bookingSeat.setIsReserved(false);
			String price = env.getProperty("seat." + bookingSeat.getSeatType().toString().toLowerCase() + ".price");
			bookingSeat.setPrice(Double.valueOf(price));
			bookingSeat.setShow(show);
			bookingSeatRepository.save(bookingSeat);
		}
		return new MessageResponse("Show " + show.getShowName() + " added");
	}

	@Override
	public MessageResponse addShows(List<ShowRequest> shows) {
		for (ShowRequest showDTO : shows) {
			Show show = toShows(showDTO);
			show.setStatus(ShowStatus.AS_PER_SCHEDULE);
			addShow(show);
		}
		return new MessageResponse(shows.size() + " Shows added");
	}

	@Override
	public MessageResponse deleteShow(String showName) {
		showRepository.deleteById(showName);
		return new MessageResponse(showName + " deleted Successfully");
	}

	
	private Show toShows(ShowRequest showsdto) {
		Show show = new Show();
		Theater theater = theaterService.getTheater(showsdto.getTheater());
		CinemaHall cinemaHall = theater.getCinemaHalls().stream()
				.filter(hall -> showsdto.getCinemaHall().equals(hall.getName())).findAny().orElse(null);
		Movie movie = movieService.getMovie(showsdto.getMovie());
		show.setStartTime(showsdto.getStartTime());
		show.setEndTime(
				Utility.getInstance().addMinutesToDate(showsdto.getStartTime(), movie.getMovieDurationInMins()));
		show.setPlayedAt(cinemaHall);
		show.setMovie(movie);
		show.setShowName(showsdto.getTheater() + "-" + showsdto.getCinemaHall() + "-" + showsdto.getMovie() + "-"
				+ Utility.getInstance().formatDate(showsdto.getStartTime(), DateFormat.DD_MM_YYYY));
		
		show.setTimeAdded(new Date());
		return show;
	}

	@Override
	public Show getShow(String showName) {
		return showRepository.findById(showName)
				.orElseThrow(() -> new ResourceNotFoundException("Show", "Show Name", showName));
	}

	@Override
	public List<Show> getAllShows() {
		return showRepository.findAll();
	}

	@Override
	public List<Show> getAllCurrentAndUpcomingShows() {
		return showRepository.findAllShowsAfter(new Date());
	}

	@Override
	public List<BookingSeat> getAllAvailalbeSeatsForShow(String showName) {
		Show show = getShow(showName);
		Date reducedTime = Utility.getInstance().reduceMinutesToDate(new Date(),
				Integer.valueOf(env.getProperty("seat.locking.timeInMinutes")));
		return bookingSeatRepository.findAllAvailableSeats(show, reducedTime);
	}

		
}
