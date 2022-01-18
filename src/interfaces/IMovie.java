package interfaces;

//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;


import dbadapter.Movies;
//import dbadapter.UserAccount;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author swe.uni-due.de
 *
 */
public interface IMovie {

	public ArrayList<Movies> getMoviesInDB();
	
	public boolean addingNewMoviesToDB(String title, String director, String mainActor, Date publishingDate);
	
	public boolean addingNewRatingToDB(String username, int rating, String title, String comment);
    
	public boolean checkMovieAlreadyExists(String title);
	
	public void setNotRated(String username, String title);
	
	public boolean checkMovieAlreadyRated(String username,String title);


}