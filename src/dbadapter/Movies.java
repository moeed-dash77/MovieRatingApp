package dbadapter;

//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
//import datatypes.AddressData;


/**
 * Class representing a Movie
 * 
 * @author Raza
 *
 */


public class Movies {
	
	private String username;
	private String title;
	private String director;
	private String mainActor;
	private Date publishingDate;
	private int rating;
	private String comment;
	private int avgRating;
	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}
	private ArrayList<Movies> movies;
	
	public Movies() {}
	
	public Movies(String username, String title, String director, String mainActor,  Date publishingDate) {
		this.username = username;
		this.title = title;
		this.director = director;
		this.mainActor = mainActor;
		this.publishingDate = publishingDate;
		this.movies = new ArrayList<Movies>();
	}
	
	
	public Movies(String username,int rating, String title,  String comment) {
		this.username = username;
		this.rating = rating;
		this.title = title;
		this.comment = comment;
		this.movies = new ArrayList<Movies>();
	}
	
	
			
			
	@Override
	public String toString() {
		return "Movies [username=" + username + ", title=" + title + ", director=" + director + ", mainActor="
				+ mainActor + ", publishingDate=" + publishingDate + ", rating=" + rating + ", comment=" + comment
				+ ", movies=" + movies + "]";
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getMainActor() {
		return mainActor;
	}
	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ArrayList<Movies> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movies> movies) {
		this.movies = movies;
	}
	
	
	
	
	
}
