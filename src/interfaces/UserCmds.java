package interfaces;

import java.util.ArrayList;

import dbadapter.Movies;
import dbadapter.UserAccount;

public interface UserCmds {
	
	public UserAccount create(String username, String email, String password, String age);
	
	public Movies addMovieToDB(String username, String title, String director, String mainActor, String publishingDate);
	
	public Movies createRating(String username, String rating, String title, String comment);
	
	public void deleteRating( String username, String title);
	
	public ArrayList<Movies> getMoviesInDB();
	
}
