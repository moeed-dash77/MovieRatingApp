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
		
		String sqlSelect = "SELECT mov.*,rat.AverageRating from movieratingappdb.movies mov LEFT JOIN (SELECT m.title, avg(r.rating) as AverageRating FROM movieratingappdb.movies m JOIN movieratingappdb.ratings r on r.mid = m.title GROUP BY m.title) rat ON rat.title = mov.title ORDER BY AverageRating DESC";

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
					movie.setDirector(rs.getString("director"));
					movie.setAvgRating(rs.getInt("AverageRating"));
					//movie.setComment(rs.getString("Comment"));
					movies.add(movie);
					movie.setMovies(movies);
				} 
				
				
			}catch (SQLException e) {
				System.out.println("Query failed to showMovies" + e.getMessage());
			}

	}catch(Exception e) {
		System.out.println("SQL Database connection to showMovies failed" + e.getMessage());	
	}
	return movies;
}
	


	public int selectUserId(String username) {

		// Declare necessary SQL query.
		String queryUserID = "SELECT * FROM useraccount WHERE username=?";
		int userId = 0;
		// query data.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection.prepareStatement(queryUserID)) {
				psSelect.setString(1, username);
				try (ResultSet rs = psSelect.executeQuery()) {
					while(rs.next()) {
						userId = rs.getInt("id");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return userId;
		}
		return userId;
	}

	/**
	 * Inserts a new Movie in the database.
	 * 
	 * @param title 
	 * @param director
	 * @param mainActor
	 * @param publishingDate
	 * @return 
	 */
	
	public boolean addingNewMoviesToDB(String title, String director, String mainActor, Date publishingDate) {

		boolean movieAdded = false;
		// Declare SQL query to insert Movie.
		String sqlInsert = "INSERT INTO movieratingappdb.movies (title, director, mainActor, publishingDate) VALUES (?,?,?,?)";
		// Insert Movie into database.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				Date now = new Date();
				int checkDate = now.compareTo(publishingDate);
				if(!(checkMovieAlreadyExists(title))) {
				if(title!= null && director!= null && mainActor!= null && !(checkDate < 0)) {
				    ps.setString(1, title);
			    	ps.setString(2, director);
				    ps.setString(3, mainActor);
				    ps.setDate(4, new java.sql.Date(publishingDate.getTime()));
				    int rowCount = ps.executeUpdate();
				    if (rowCount>0) {
				    	movieAdded = true;
				    }else {
				    	movieAdded = false;
				    }
				    
				}
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
				movieAdded = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			movieAdded = false;
		}
		return movieAdded;

	}
	


	
	/**
	 * Inserts a new Rating in the database.
	 * 
	 * @param title 
	 * @param username
	 * @param rating
	 * @param comment
	 * @return 
	 */
	
	public boolean addingNewRatingToDB(String username, int rating, String title, String comment) {
		boolean RatingStatus = false;
		String sqlInsert = "INSERT INTO movieratingappdb.ratings (uid, rating, mid, comment) VALUES (?,?,?,?)";	
		// Insert Ratings into database.
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setString(1, username);
			    ps.setInt(2, rating);
			    ps.setString(3, title);
		    	ps.setString(4, comment); 
			    int rowCount = ps.executeUpdate();
			    if (rowCount > 0) {
			    	RatingStatus = true;
			    }
				
			} catch (SQLException e) {
				e.printStackTrace();
				RatingStatus = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			RatingStatus = false;
		}
		return RatingStatus;

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
	
	public boolean createUser(String username, String email, String password, int age) {
		boolean registerStatus = false;
		String sqlInsert = "INSERT INTO movieratingappdb.userAccount (username, email, password, age) VALUES (?,?,?,?)";
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				if(!(checkUserAlreadyExists(username))) {
					ps.setString(1, username);
				    ps.setString(2, email);
				    ps.setString(3, password);
			    	ps.setInt(4, age);
				    int rowCount = ps.executeUpdate();
				    if (rowCount > 0) {
				    	registerStatus = true;
				    }else {
				    	registerStatus = false;
				    }
				 }  
				}catch (SQLException e) {
				e.printStackTrace();
				registerStatus = false;
			
				}
			
				
		}catch (Exception e) {
			e.printStackTrace();
			registerStatus = false;
		}
		return registerStatus;

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
		String queryME = "SELECT * FROM movieratingappdb.movies WHERE title=?";

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
	
	public boolean checkUserAlreadyExists(String username) {

		// Declare necessary SQL query.
		String queryUE = "SELECT * FROM movieratingappdb.useraccount WHERE username=?";

		// query data.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection.prepareStatement(queryUE)) {
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
	
	/**
	 * Checks if the movie already rated by the user in the DB
	 * 
	 * @param title
	 * @return
	 */
	
	public boolean checkMovieAlreadyRated(String username, String movieTitle) {

		// Declare necessary SQL query.
		String queryRE = "SELECT * FROM movieratingappdb.ratings WHERE uid=? and mid =?";

		// query data.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection.prepareStatement(queryRE)) {
				psSelect.setString(1, username);
				psSelect.setString(2, movieTitle);
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
