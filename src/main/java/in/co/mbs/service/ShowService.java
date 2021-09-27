package in.co.mbs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import in.co.mbs.dto.MessageResponse;
import in.co.mbs.dto.ShowRequest;
import in.co.mbs.model.BookingSeat;
import in.co.mbs.model.Show;

/**
 * 
 * @author santhosh
 *
 */
@Component
public interface ShowService {

	public MessageResponse addShow(ShowRequest show);

	public MessageResponse addShows(List<ShowRequest> show);

	public Show getShow(String showName);

	public List<Show> getAllShows();

	public List<Show> getAllCurrentAndUpcomingShows();

	public List<BookingSeat> getAllAvailalbeSeatsForShow(String showName);

	public MessageResponse deleteShow(String show);

}
