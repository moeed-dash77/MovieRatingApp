package application;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import dbadapter.UserAccount;
import dbadapter.DBFacade;
import dbadapter.Movies;
import interfaces.UserCmds;



public class MRApplication implements UserCmds{

	private static MRApplication instance;

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static MRApplication getInstance() {
		if (instance == null) {
			instance = new MRApplication();
		}

		return instance;
	}
	
	
	

	/**
	 * Insert method to Create a user account with the given
	 * parameters in the database
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @param age
	 * @return
	 */
	
	public boolean create(String username, String email, String password, String age) {
        
		 assert (!checkUserAlreadyExists(username)) : "User already exists, Precondition not satisfied";

			// Create result object
			boolean registeredStatus = false;

		// Parse inputs to correct datatypes
		try {
			
			int ageSQL = Integer.parseInt(age);
			if(username != null && ageSQL >= 17 && email != null && password != null) {
				registeredStatus = DBFacade.getInstance().createUser(username, email, password, ageSQL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return registeredStatus;
	}
	
	
	/**
	 * Forwards a creating movie request to the database and waits for the new movie to be added
	 * This will be returned to the GUI after.
	 * 
	 * @param title
	 * @param director
	 * @param actors
	 * @param publishingDate
	 * @return
	 */
	
	public boolean addMovieToDB(String username, String title, String director, String mainActor, String publishingDate) {
		
		
		// pre: check if the movie being added already exists in the database or not
		assert (!movieAlreadyExists(title)) : "Precondition not satisfied";
		
		// Create result object
		boolean movieConfirmation = false;

		// Parse inputs to correct datatypes
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date publishingDateSQL = dateFormat.parse(publishingDate);

			movieConfirmation = DBFacade.getInstance().addingNewMoviesToDB(title, director, mainActor, publishingDateSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieConfirmation;
	}
	
	/**
	 * Forwards new rating given by the user to the database and waits for the new rating to be added
	 * This will be returned to the GUI after.
	 * 
	 * @param rating
	 * @param title
	 * @param comment
	 * @param username
	 * @return
	 */
	
     public boolean createRating(String username, String rating, String title, String comment) {
		
		
		// pre: check if the movie being added is already rated by the user 
		assert (!movieAlreadyRated(username,title)) : "Precondition not satisfied";
		
		// Create result object
		boolean RatingOk = false;

		// Parse inputs to correct datatypes
		try {
			
			int ratingSQL = Integer.parseInt(rating);
			if(ratingSQL != 0 &&  ratingSQL > 0 && ratingSQL < 11 ) {
				RatingOk = DBFacade.getInstance().addingNewRatingToDB(username, ratingSQL, title, comment);
			}else {
				RatingOk = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RatingOk;
	}
     
     /**
 	 * Initiates deleting of Ratings if the user wants to delete.
 	 */
     
 	public void deleteRating( String username, String title) {
 		DBFacade.getInstance().setNotRated(username, title);
 	}
 	
 	
 	/**
	 * Calls DBFacace method to retrieve all Movies to the given.
	 * parameters:
	 * None needed!
	 */
	public ArrayList<Movies> getMoviesInDB() {
		
		ArrayList<Movies> result = null;
		try {
			result = DBFacade.getInstance().getMoviesInDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	private boolean movieAlreadyExists(String title) {
		return DBFacade.getInstance().checkMovieAlreadyExists(title);
	}
	
	private boolean movieAlreadyRated(String username,String movieTitle) {
		return DBFacade.getInstance().checkMovieAlreadyRated(username,movieTitle);
	}
	
	private boolean checkUserAlreadyExists(String username) {
		return DBFacade.getInstance().checkUserAlreadyExists(username);
	}
	
	
}
