# Movie Booking system
A simple movie ticket booking system.

#### API DOCUMENT 

* All the api's have been published. please click below link for detailed information

 *  [API'S](https://documenter.getpostman.com/view/17659078/UUxzA7pW)

 
#### Available features & Restrictions
 
 * All the api's are secured with oauth. In order to maintain security and have multiple instance of the application the access token's and other security related credentials are saved in db.
 * Multiple Users can book tickets for a same movie show. System also supports multiple halls,movies and shows.
 * A user can select upto 6 seats at max for a ticket.
 * A user has to pay within 2 minutes for the selected seats else the seats will be released to other users. 
 * Seats are blocked on first come first serve basis. if unfortunately the same seat is chosen  by more than one user the one who blocked most number of tickets is allowed for booking. If there is a tie then the user is selected randomly.
 * Single DB instance is enough for maintaining the state of the application, irrespective of application instance 
count.
 * Currently assumed Payment API is sending either SUCCESS or FAILURE