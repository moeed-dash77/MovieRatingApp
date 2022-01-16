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
	
	public UserAccount create(String username, String email, String password, String age) {
        
		 assert (!checkUserAlreadyExists(email)) : "User already exists, Precondition not satisfied";

			// Create result object
			UserAccount failRegisteration = null;

		// Parse inputs to correct datatypes
		try {
			
			int ageSQL = Integer.parseInt(age);
			failRegisteration = DBFacade.getInstance().createUser( username,  email, password,  ageSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return failRegisteration;
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
	
	public Movies addMovieToDB(String username, String title, String director, String mainActor, String publishingDate) {
		
		
		// pre: check if the movie being added already exists in the database or not
		assert (!movieAlreadyExists(title)) : "Precondition not satisfied";
		
		// Create result object
		Movies okfail = null;

		// Parse inputs to correct datatypes
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date publishingDateSQL = dateFormat.parse(publishingDate);

			okfail = DBFacade.getInstance().addingNewMoviesToDB(username, title, director, mainActor, publishingDateSQL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return okfail;
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
	
     public Movies createRating(String username, String rating, String title, String comment) {
		
		
		// pre: check if the movie being added is already rated by the user 
		assert (!movieAlreadyRated(username)) : "Precondition not satisfied";
		
		// Create result object
		Movies okfail = null;

		// Parse inputs to correct datatypes
		try {
			
			int ratingSQL = Integer.parseInt(rating);
			okfail = DBFacade.getInstance().addingNewRatingToDB(username, ratingSQL,  title,  comment);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return okfail;
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
	
	private boolean movieAlreadyRated(String username) {
		return DBFacade.getInstance().checkMovieAlreadyRated(username);
	}
	
	private boolean checkUserAlreadyExists(String email) {
		return DBFacade.getInstance().checkUserAlreadyExists(email);
	}
	
	
}
