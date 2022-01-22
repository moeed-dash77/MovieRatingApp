package dbadapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class representing a Movie
 * 
 * @author Raza
 *
 */

public class Movies {
	
	private String title;
	private String director;
	private String mainActor;
	private Date publishingDate;
	private int avgRating;
	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}
	private ArrayList<Movies> movies;
	public Movies() {}
	public Movies(String title, String director, String mainActor,  Date publishingDate) {
		this.title = title;
		this.director = director;
		this.mainActor = mainActor;
		this.publishingDate = publishingDate;
		this.movies = new ArrayList<Movies>();
	}
	@Override
	public String toString() {
		return "Movies [title=" + title + ", director=" + director + ", mainActor="
				+ mainActor + ", publishingDate=" + publishingDate + ", rating=" + avgRating 
				+ ", movies=" + movies + "]";
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
	public ArrayList<Movies> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movies> movies) {
		this.movies = movies;
	}

	
	
	
	
}