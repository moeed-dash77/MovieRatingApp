package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

//import datatypes.AddressData;
//import datatypes.GuestData;
//import interfaces.IHolidayOffer;
import interfaces.IMovie;
import interfaces.IUserAcc;
import dbadapter.Movies;
import dbadapter.UserAccount;



/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns. Exceptions thrown in this class will be
 * catched with a 500 error page.
 * 
 * @author swe.uni-due.de
 *
 */
public class DBFacade implements IMovie, IUserAcc {
	private static DBFacade instance;

	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}

	/**
	 * Function that returns all Movies from the database..
	 * @return Arraylist of all Movie objects.
	 */
	
	public ArrayList<Movies> getMoviesInDB() {
		
	
		ArrayList<Movies> movies = new ArrayList<Movies>();

		// Declare the necessary SQL queries.
		
		String sqlSelect = "SELECT title, publishingDate, director, mainActor, DECIMAL(AVG(rating),5,2) as Avg_Rating, comment  "
				+ "FROM Movie GROUP BY title ORDER BY Avg_rating";


		// Query all movies
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement ps = connection.prepareStatement(sqlSelect);
					ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Movies movie= new Movies();
					movie.setTitle(rs.getString("title"));
					movie.setPublishingDate(rs.getDate("publishingDate"));
					movie.setMainActor(rs.getString("mainActor"));
					movie.setAvgRating(rs.getInt("Avg_Rating"));
					movie.setComment(rs.getString("Comment"));
					movies.add(movie);
					movie.setMovies(movies);
				} 
				
				
			}catch (SQLException e) {
				System.out.println("Query failed to showMovies" + e.getMessage());
			}

	}catch(Exception e) {
		System.out.println("SQL Database connection to showMovies failed" + e.getMessage());	}
		return movies;
}
	
/*	public ArrayList<HolidayOffer> getAvailableHolidayOffers(Timestamp arrivalTime, Timestamp departureTime,
			int persons) {
		ArrayList<HolidayOffer> result = new ArrayList<HolidayOffer>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM HolidayOffer WHERE starttime <= ? AND endtime >= ? AND capacity >= ?";
		String sqlSelectB = "SELECT * FROM Booking WHERE hid = ?";

		// Query all offers that fits to the given criteria.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect);
					PreparedStatement psSelectB = connection.prepareStatement(sqlSelectB)) {
				ps.setTimestamp(1, arrivalTime);
				ps.setTimestamp(2, departureTime);
				ps.setInt(3, persons);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						HolidayOffer temp = new HolidayOffer(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3),
								new AddressData(rs.getString(4), rs.getString(5)), rs.getInt(6), rs.getDouble(7));
						psSelectB.setInt(1, temp.getId());

						// Query all bookings for the offer to check if its
						// available.
						try (ResultSet brs = psSelectB.executeQuery()) {
							ArrayList<Booking> bookings = new ArrayList<Booking>();
							while (brs.next()) {
								bookings.add(new Booking(brs.getInt(1), brs.getTimestamp(2), brs.getTimestamp(3),
										brs.getTimestamp(4), brs.getBoolean(5),
										new GuestData(brs.getString(6), brs.getString(7)), brs.getDouble(8),
										brs.getInt(9)));
							}
							temp.setBookings(bookings);
						}
						if (temp.available(arrivalTime, departureTime))
							result.add(temp);
					}
					;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}*/

	/**
	 * Inserts a new Movie in the database.
	 * 
	 * @param title 
	 * @param director
	 * @param mainActor
	 * @param publishingDate
	 * @return 
	 */
	
	public Movies addingNewMoviesToDB(String username, String title, String director, String mainActor, Date publishingDate) {

		// Declare SQL query to insert Movie.
		String sqlInsert = "INSERT INTO Moive (username, title, director, mainActor, publishingDate) VALUES (?,?,?,?,?)";
        
		//return type movie object
		
		Movies movie = new Movies(username, title, director, mainActor, publishingDate);
		
		// Insert Movie into database.
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
			
				//Timestamp creationDate = new Timestamp(new Date().getTime());
				
				Date now = new Date();
				int checkDate = now.compareTo(publishingDate);
				
				if(title!= null && director!= null && mainActor!= null && !(checkDate < 0)) {

					
				    ps.setString(1, movie.getUsername());
				    ps.setString(2, movie.getTitle());
			    	ps.setString(3, movie.getDirector());
				    ps.setString(4, movie.getMainActor());
				    ps.setDate(5, (java.sql.Date) movie.getPublishingDate());
				    ps.executeUpdate();
				    
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;

	}
	
//	public void addingNewMoviesToDB(Timestamp startTime, Timestamp endTime, AddressData address, int capacity, double fee) {
//
//		// Declare SQL query to insert offer.
//		String sqlInsert = "INSERT INTO HolidayOffer (startTime,endTime,street,town,capacity,fee) VALUES (?,?,?,?,?,?)";
//
//		// Insert offer into database.
//		try (Connection connection = DriverManager
//				.getConnection(
//						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
//								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
//						Configuration.getUser(), Configuration.getPassword())) {
//
//			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
//				ps.setTimestamp(1, startTime);
//				ps.setTimestamp(2, endTime);
//				ps.setString(3, address.getStreet());
//				ps.setString(4, address.getTown());
//				ps.setInt(5, capacity);
//				ps.setDouble(6, fee);
//				ps.executeUpdate();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	/**
//	 * Inserts a booking into the database if there are enough capacities
//	 * 
//	 * @param arrivalTime
//	 * @param departureTime
//	 * @param hid
//	 * @param guestData
//	 * @param persons
//	 * @return new booking object if available or null if not available
//	 */
//	public Booking bookingHolidayOffer(Timestamp arrivalTime, Timestamp departureTime, int hid, GuestData guestData,
//			int persons) {
//		HolidayOffer ho = null;
//		ArrayList<Booking> bookings = new ArrayList<Booking>();
//		Booking booking = null;
//
//		// Declare necessary SQL queries.
//		String sqlSelectHO = "SELECT * FROM HolidayOffer WHERE id=?";
//		String sqlInsertBooking = "INSERT INTO Booking (creationDate,arrivalTime,departureTime,paid,name,email,price,hid) VALUES (?,?,?,?,?,?,?,?)";
//		String sqlSelectB = "SELECT * FROM Booking WHERE hid=?";
//
//		// Get selected offer
//		try (Connection connection = DriverManager
//				.getConnection(
//						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
//								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
//						Configuration.getUser(), Configuration.getPassword())) {
//			try (PreparedStatement psSelect = connection.prepareStatement(sqlSelectHO);
//					PreparedStatement psSelectB = connection.prepareStatement(sqlSelectB);
//					PreparedStatement psInsert = connection.prepareStatement(sqlInsertBooking,
//							PreparedStatement.RETURN_GENERATED_KEYS)) {
//				psSelect.setInt(1, hid);
//				try (ResultSet hors = psSelect.executeQuery()) {
//					if (hors.next()) {
//						ho = new HolidayOffer(hors.getInt(1), hors.getTimestamp(2), hors.getTimestamp(3),
//								new AddressData(hors.getString(4), hors.getString(5)), hors.getInt(6),
//								hors.getDouble(7));
//					}
//				}
//
//				// Check if offer is still available
//				if (ho != null) {
//					psSelectB.setInt(1, hid);
//					try (ResultSet brs = psSelectB.executeQuery()) {
//						while (brs.next()) {
//							bookings.add(new Booking(brs.getInt(1), brs.getTimestamp(2), brs.getTimestamp(3),
//									brs.getTimestamp(4), brs.getBoolean(5),
//									new GuestData(brs.getString(6), brs.getString(7)), brs.getDouble(8),
//									brs.getInt(9)));
//						}
//						ho.setBookings(bookings);
//					}
//
//					// Insert new booking
//					if (ho.available(arrivalTime, departureTime) && ho.getCapacity() >= persons) {
//						Timestamp creationDate = new Timestamp(new Date().getTime());
//						booking = new Booking(0, new Timestamp(creationDate.getTime()), arrivalTime, departureTime,
//								false, guestData, calculatePrice(arrivalTime, departureTime, ho.getFee()), ho.getId());
//						psInsert.setTimestamp(1, booking.getCreationDate());
//						psInsert.setTimestamp(2, booking.getArrivalTime());
//						psInsert.setTimestamp(3, booking.getDepartureTime());
//						psInsert.setBoolean(4, booking.isPaid());
//						psInsert.setString(5, booking.getGuestData().getName());
//						psInsert.setString(6, booking.getGuestData().getEmail());
//						psInsert.setDouble(7, booking.getPrice());
//						psInsert.setInt(8, booking.getHid());
//						psInsert.executeUpdate();
//						try (ResultSet generatedKeys = psInsert.getGeneratedKeys()) {
//							if (generatedKeys.next()) {
//								booking.setId(generatedKeys.getInt(1));
//							}
//						}
//
//					} else
//						ho = null;
//
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return booking;
//	}
//	
	
	/**
	 * Inserts a new Rating in the database.
	 * 
	 * @param title 
	 * @param username
	 * @param rating
	 * @param comment
	 * @return 
	 */
	
	public Movies addingNewRatingToDB(String username, int rating, String title, String comment) {

		// Declare SQL query to insert new rating.
		
		String sqlInsert = "INSERT INTO Moive (username, rating,  title,  comment) VALUES (?,?,?,?)";

		Movies movie = new Movies(username, rating,  title,  comment);
		
		
		// Insert Ratings into database.
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
			
				
				if(rating!= 0 &&  rating > 0 && rating < 11 ) {

					
					
					ps.setString(1, movie.getUsername());
				    ps.setInt(2, movie.getRating());
				    ps.setString(3, movie.getTitle());
			    	ps.setString(4, movie.getComment());
				    
				    
				    ps.executeUpdate();
				 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;

	}
	
	
	/**
	 * Inserts a new User in the database.
	 * 
	 * @param email 
	 * @param username
	 * @param password
	 * @param age
	 * @return 
	 */
	
	public UserAccount createUser(String username, String email, String password, int age) {

		// Declare SQL query to insert user.
		String sqlInsert = "INSERT INTO User (username, email,  password,  age) VALUES (?,?,?,?)";

		
		UserAccount ua = new UserAccount( username,  email, password,  age);
		
		
		// Insert UserAccont into database.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
			;
				
				if(username!= null && age< 17 && email != null && password != null   ) {

					
					
					ps.setString(1, ua.getUsername());
				    ps.setString(2, ua.getEmail());
				    ps.setString(3,ua.getPassword());
			    	ps.setInt(4, ua.getAge());
				    
				    
				    ps.executeUpdate();
				  
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ua;

	}
	
	
	/**
	 * Delete user ratings for a movie. 
	 * @param title
	 * @param username
	 */
	public void setNotRated(String username, String title) {

		// Declare necessary SQL statement.
		String deleteMR = "DELETE rating FROM Movie WHERE username=? and title=?";

		// Update Database.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psDelete = connection.prepareStatement(deleteMR)) {
				
				psDelete.setString(1, username);
				psDelete.setString(2, title);
				
				psDelete.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

//	/**
//	 * Delete all bookings not paid and older than 14 days.
//	 */
//	public void setAvailableHolidayOffer() {
//
//		// Declare necessary SQL statement.
//		String deleteBO = "DELETE FROM Booking WHERE (paid = FALSE AND (TIMESTAMPADD( SQL_TSI_DAY , 14, creationData) < CURRENT_TIMESTAMP))";
//
//		// Update Database.
//		try (Connection connection = DriverManager
//				.getConnection(
//						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
//								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
//						Configuration.getUser(), Configuration.getPassword())) {
//			try (PreparedStatement psDelete = connection.prepareStatement(deleteBO)) {
//				psDelete.executeUpdate();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


	/**
	 * Checks if the movie already exists in the DB
	 * 
	 * @param title
	 * @return
	 */
	
	public boolean checkMovieAlreadyExists(String title) {

		// Declare necessary SQL query.
		String queryME = "SELECT FROM Movie WHERE title=?";

		// query data.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection.prepareStatement(queryME)) {
				psSelect.setString(1, title);
				try (ResultSet rs = psSelect.executeQuery()) {
					return rs.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Checks if the user already in the DB
	 * 
	 * @param email
	 * @return
	 */
	
	public boolean checkUserAlreadyExists(String email) {

		// Declare necessary SQL query.
		String queryUE = "SELECT FROM User WHERE email=?";

		// query data.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection.prepareStatement(queryUE)) {
				psSelect.setString(1, email);
				try (ResultSet rs = psSelect.executeQuery()) {
					return rs.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Checks if the movie already rated by the user in the DB
	 * 
	 * @param title
	 * @return
	 */
	
	public boolean checkMovieAlreadyRated(String username) {

		// Declare necessary SQL query.
		String queryRE = "SELECT rating FROM Movie WHERE username=?";

		// query data.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection.prepareStatement(queryRE)) {
				psSelect.setString(1, username);
				try (ResultSet rs = psSelect.executeQuery()) {
					return rs.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
   
//	/**
//	 * Checks if offer with given id exists.
//	 * 
//	 * @param hid
//	 * @return
//	 */
//	public boolean checkHolidayOfferById(int hid) {
//
//		// Declare necessary SQL query.
//		String queryHO = "SELECT FROM HolidayOffer WHERE id=?";
//
//		// query data.
//		try (Connection connection = DriverManager
//				.getConnection(
//						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
//								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
//						Configuration.getUser(), Configuration.getPassword())) {
//			try (PreparedStatement psSelect = connection.prepareStatement(queryHO)) {
//				psSelect.setInt(1, hid);
//				try (ResultSet rs = psSelect.executeQuery()) {
//					return rs.next();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

//	/**
//	 * Function used to calculate the price for a booking.
//	 * 
//	 * @param date1 arrival date
//	 * @param date2 departure date
//	 * @param fee   price per night for the offer
//	 * @return
//	 */
//	private double calculatePrice(Timestamp date1, Timestamp date2, double fee) {
//		long dayDifference = (date2.getTime() - date1.getTime()) / 1000 / 60 / 60 / 24;
//
//		return dayDifference * fee;
//	}
//}
